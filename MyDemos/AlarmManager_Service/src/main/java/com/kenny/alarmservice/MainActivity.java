package com.kenny.alarmservice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kenny.alarmservice.utils.ServiceUtil;

/**
 * Created by coder80 on 2014/10/31.
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener{
	private TextView mTextView;
	private Button mBtnStart;
	private Button mBtnStop;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		mTextView = (TextView) findViewById(R.id.show);
		mBtnStart = (Button) findViewById(R.id.button1);
		mBtnStop = (Button) findViewById(R.id.button2);
		mBtnStart.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		case R.id.button1:
			ServiceUtil.invokeTimerPOIService(mContext);
			break;
		case R.id.button2:
			ServiceUtil.cancleAlarmManager(mContext);
			break;
		}
		
	}
}
