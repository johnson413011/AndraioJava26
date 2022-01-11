package com.example.broadcasts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    //private MyBroadcastReceiver mMyReceiver; //自訂一個繼承 BroadcastReceiver 的類別
    private BcResult broadcast = new BcResult();
    public static final String action = "read";
    public static final String gotit = "got";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //註冊 BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(broadcast, intentFilter);  //註冊廣播接收器


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyBroadcast(R.id.button, getBaseContext());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcast);  //撤銷廣播接收器
    }

    private class BcResult extends  BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            assert action != null;
            String msg = intent.getStringExtra(gotit);
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setMessage(msg);
            ad.setPositiveButton("關閉", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }
    }
}