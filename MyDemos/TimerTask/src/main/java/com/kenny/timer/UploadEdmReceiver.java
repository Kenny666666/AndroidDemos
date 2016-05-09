package com.kenny.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author kenny
 * @time 2016/5/9
 */
public class UploadEdmReceiver extends BroadcastReceiver {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("UploadEdmReceiver","收到广播");
        String action = intent.getAction();
        if ("upload_edm_receiver".equals(action)){
            sp = context.getSharedPreferences("config",0);
            int time = sp.getInt("time",0);
            if (time != 0){
                time--;
                Log.e("UploadEdmReceiver","time="+time);
                editor = sp.edit();
                editor.putInt("time",time);
                editor.commit();
            }else {
                //time==0,获取之前未成功上传的求助数据，以及此次求助数据，启动后台服务上传edm
                //...

                //重置定时器时间
                time = 5;
                Log.e("UploadEdmReceiver","定时上传time="+time);
                editor = sp.edit();
                editor.putInt("time",time);
                editor.commit();
            }
        }
    }
}
