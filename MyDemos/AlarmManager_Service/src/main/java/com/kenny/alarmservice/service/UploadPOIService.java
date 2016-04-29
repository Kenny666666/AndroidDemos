package com.kenny.alarmservice.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.kenny.alarmservice.MessageActivity;
import com.kenny.alarmservice.R;


/**
 * Created by coder80 on 2014/10/31.
 */
public class UploadPOIService extends Service implements Runnable{
    private String TAG = UploadPOIService.class.getSimpleName();
	/** Notification管理 */
	public NotificationManager mNotificationManager;
	/** Notification构造器 */
	NotificationCompat.Builder mBuilder;
    @Override
    public void onCreate() {
		Log.i(TAG, "UploadPOIService onCreate here.... ");
        super.onCreate();
		initNotifiManager();
        uploadPOIInfo();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "UploadPOIService onDestroy here.... ");
    }

    private void uploadPOIInfo() {
    	//simulation HTTP request to server 
    	new Thread(this).start();
    }
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Log.i(TAG, "正在上传文件，大概需要5秒...");
			Thread.sleep(5*1000);
			Log.i(TAG, "上传成功，服务停止");
			showNotification();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//停止服务
		stopSelf();
	}

	private void initNotifiManager() {
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
	}

	private void showNotification() {
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setAutoCancel(true)//点击后让通知将消失
				.setContentTitle("测试标题")
				.setContentText("点击跳转")
				.setTicker("点我");

		//点击的意图ACTION是跳转到Intent
		Intent resultIntent = new Intent(this, MessageActivity.class);
		resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		mNotificationManager.notify(0, mBuilder.build());
	}

	/**
	 * @获取默认的pendingIntent,为了防止2.3及以下版本报错
	 * @flags属性:
	 * 在顶部常驻:Notification.FLAG_ONGOING_EVENT
	 * 点击去除： Notification.FLAG_AUTO_CANCEL
	 */
	public PendingIntent getDefalutIntent(int flags){
		PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
		return pendingIntent;
	}
}
