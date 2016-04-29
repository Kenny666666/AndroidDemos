package com.kenny.globalhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

/**
 * description 全局handler写法2
 * Created by kenny on 2016/4/27.
 * version
 */
public abstract class BaseActivity extends Activity {
    /**
     * 全局handler
     */
    protected UIHandler handler = new UIHandler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化handler
        setHandler();

    }

    /**
     * handler初始化
     */
    private void setHandler(){
        handler.setHandler(new UIHandler.IHandler() {
            @Override
            public void handleMessages(Message msg) {
                handler(msg);
            }
        });
    }

    /**
     * 子类重写此方法处理handler消息
     * @param msg
     */
    protected abstract void handler(Message msg);
}
