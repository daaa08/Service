package com.example.da08.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.button1)).setOnClickListener(this);
        (findViewById(R.id.button2)).setOnClickListener(this);
        (findViewById(R.id.button3)).setOnClickListener(this);
        (findViewById(R.id.button4)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()){
            case R.id.button1 :
                startService(intent);
                break;
            case R.id.button2 :
                stopService(intent);
                break;
            case R.id.button3 :
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.button4 :
                unbindService(connection);
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        // 서비스와 연결되는 순간 호출이 됨
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.d("MainActivity", "onServiceConnected");
            MyService.MyBinder iBinder = (MyService.MyBinder)binder;
            MyService service = iBinder.getService(); // service에 있는 기능 사용
            service.print("success connection");
        }

        // 일반적인 상황에서는 거의 호출되지 않음(onDestroy에서는 호출 안 됨) - 서비스가 끊기거나 연결이 중단되면 호출됨
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MainActivity", "onServiceDisconnected");

        }
    };
}
