/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.newdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;


public class WelcomeActivity extends AppCompatActivity {
    protected static final String tag = "WelcomeActivity";
    String flag;
    String id;
    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        final SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        final boolean b = sharedPreferences.getBoolean("isFirst", true);
        //1，第几次进入当前应用（1，导航页  2....mainActivity）
        //handler延时发送消息
        new Handler() {
            public void handleMessage(android.os.Message msg) {
                //3秒过后回去执行的逻辑
                if (b) {
                    Intent intent = new Intent(WelcomeActivity.this, MyActivity.class);
                    startActivity(intent);
                    //第一次进入，进入导航页,将当前isFirst做修改
                    sharedPreferences.edit().putBoolean("isFirst", false).commit();
//                    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
//                    startActivity(intent);
                } else {
                    //第二次或以后,进入主页面

                    Intent intent = new Intent(WelcomeActivity.this, MyActivity.class);
                    startActivity(intent);

                }
                finish();
            }

            ;
        }.sendEmptyMessageDelayed(0, 4000);
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
