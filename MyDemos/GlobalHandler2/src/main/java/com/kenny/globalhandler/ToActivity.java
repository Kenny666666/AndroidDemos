package com.kenny.globalhandler;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * description
 * Created by kenny on 2016/4/27.
 * version
 */
public class ToActivity extends BaseActivity {
    int sum = 0;
    public ToActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10000;i++) {
                    sum += i;
                    Log.e("ToActivity:",sum+"");
                }
                Message msg = handler.obtainMessage();
                msg.what = 100;
                msg.sendToTarget();
            }
        }).start();
    }

    @Override
    protected void handler(Message msg) {
        if(msg.what == 100){
            /// 在这里 刷新 你的UI
            Toast.makeText(ToActivity.this,"ccccccccccccccccccccccccc",Toast.LENGTH_SHORT).show();

        }
    }
}
