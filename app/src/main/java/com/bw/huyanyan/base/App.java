package com.bw.huyanyan.base;

import android.app.Application;
import android.content.Context;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:13:28
 *@Description:App
 * */public class App extends Application {
     private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
    private static Context getAppContext(){
        return context;
    }
}
