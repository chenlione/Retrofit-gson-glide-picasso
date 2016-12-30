package com.chenli.retrofit2;

import android.app.Application;

import org.xutils.x;

/**
 * @author cl
 * @创建时间 2016/12/30 16:37
 * @描述 ${todo}
 * @版本 $$Rev$$
 * @更新者 $$Author$$
 */


public class MYApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    }
}
