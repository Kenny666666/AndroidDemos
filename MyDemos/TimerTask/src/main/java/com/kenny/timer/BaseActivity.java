package com.kenny.timer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author kenny
 * @time 2016/5/9
 */
public class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sp;
    protected SharedPreferences.Editor edit;
    protected boolean isHelp;
    protected int time;
    private int hours;

    //管理UI线程的handler
    protected Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getSharedPreferences("config",0);

        getServerHelp();

        isHelp = sp.getBoolean("isHelp",false);
        //存在未结束求助
        if (isHelp){
            hours = sp.getInt("hours",1);
//            time = hours * 3600 * 1000;
            time = sp.getInt("time",0);
            //开始
            mHandler.postDelayed(runnable, 1000);//每1秒执行一次runnable.
        }
    }

    /**
     * 模拟从服务器获取是否有未结束的求助
     */
    private void getServerHelp() {
        try{
            Thread.sleep(3000);

            edit = sp.edit();
            edit.putBoolean("isHelp",true);
            edit.putInt("time",5);
            edit.putInt("hours",1);
            edit.commit();

        }catch (Exception e){

        }
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.e("BaseActivity","发广播...");
            //发送广播
            sendUploadBrodcast();
            //要做的事情
            mHandler.postDelayed(this, 1000);
        }
    };

    private void sendUploadBrodcast() {
        Intent intent = new Intent();
        intent.setAction("upload_edm_receiver");
        sendBroadcast(intent);
    }
}
