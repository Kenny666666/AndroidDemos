package com.kenny.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button btnEndHelp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEndHelp = (Button) this.findViewById(R.id.btn_end_help);
        btnEndHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止定时器
                mHandler.removeCallbacks(runnable);
                isHelp = false;
                time = 0;
                edit.putBoolean("isHelp",isHelp);
                edit.putInt("time",time);
                edit.commit();
                //后台服务上传最后一时段数据

            }
        });
    }
}
