package com.bw.movie.util;

import android.app.Application;
import android.content.Context;

/**
 * author: Xuexiandong
 * data: 2019/9/27 17:17:24
 * function：
 */
public class App extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext=this;
    }
}
