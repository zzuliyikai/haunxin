package com.example.administrator.huanxin;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    //初始化
        EaseUI.getInstance().init(this, null);
    //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);



    }
}
