package com.kenny.globalhandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    int sum = 0;
    private Button btn_test;
    private Button btn_test1;
    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <10000;i++) {
                            sum += i;
                            Log.e("MainActivity:",sum+"");
                        }
                        handler.sendEmptyMessage(10);
                    }
                }).start();
            }
        });

        btn_test1 = (Button) findViewById(R.id.btn_test1);
        btn_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <10000;i++) {
                            sum += i;
                            Log.e("MainActivity:",sum+"");
                        }
                        handler.sendEmptyMessage(9);
                    }
                }).start();
            }
        });

    }

    @Override
    protected void handler(Message msg) {
        Log.e("xxxx","xxxxxxxxxxxxxxxxxxxxxxxx");
        switch (msg.what){
            case 9:
                Toast.makeText(MainActivity.this,"ddddddddddddddddddddddddd",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,ToActivity.class));
                break;
            case 10:
                Toast.makeText(MainActivity.this,"yyyyyyyyyyyyyyyyyyyyyyyy",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
