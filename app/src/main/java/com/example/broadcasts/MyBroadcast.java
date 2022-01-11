package com.example.broadcasts;
import android.content.Context;
import android.content.Intent;

public class MyBroadcast {
    public MyBroadcast(int btn, Context context){
        Intent intent = new Intent();
        intent.setAction(MainActivity.action);
        intent.putExtra(MainActivity.gotit,"我收到了");
        context.sendBroadcast(intent);
    }


}
