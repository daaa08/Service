package com.example.da08.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    public MyService() {
        Log.i("MyService", "constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i("MyService", "onBind");
        return new MyBinder();
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void print(String string){
        Log.i("MyService", string);
    }

   public class MyBinder extends Binder {
       public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("MyService", "onRebind");
    }
}
