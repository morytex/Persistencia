package com.example.moryta.persistencia;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by logonrm on 14/06/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
