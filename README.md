## Service (Started/Bound)

- View단에서는 보이지 않으나 계속해서 실행되고있음 (ex. mp3 player...)
- 일반 Activity에서는 Service를 상속해서 사용가능 하나, Service Activity를 생성하면 기본적으로 상속되어 있음
- 일반적인 Service Life Cycle
: onCreate() → onStartCommand() → Service Running → onDestroy()
- Activity 이동시 각 행동에 맞게 설정을 해줘야 함
```java
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
```
- Bind 는 Service에 연결하는 역할을 함
- onBind를 구현해야만 bindService() 로 service 에 bind 할 수 있음
- connection 이 맺어지면 ServiceConnection 의 onServiceConnected() 를 호출하면서 IBinder 를 전달
```java
// Service
@Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", "onBind");

        return new MyBinder();
    }

// MainActivity
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
```
