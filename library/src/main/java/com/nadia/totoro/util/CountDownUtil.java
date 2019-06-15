package com.nadia.totoro.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时工具
 * <p>
 * author: Created by 闹闹 on 2017-06-13
 * version: 1.0.0
 */
public class CountDownUtil {

    private int time;
    private Timer timer;
    private TextView btnSure;
    private String btnText;

    public CountDownUtil(int time, TextView btnSure, String btnText) {
        super();
        this.time = time;
        this.btnSure = btnSure;
        this.btnText = btnText;
    }

    public void RunTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task, 100, 1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (time > 0) {
                        btnSure.setEnabled(false);
                        btnSure.setText(time + "秒后重新发送");
                        btnSure.setTextSize(11);
                    } else {
                        timer.cancel();
                        btnSure.setText(btnText);
                        btnSure.setEnabled(true);
                        btnSure.setTextSize(11);
                    }
                    break;
            }
        }
    };
}
