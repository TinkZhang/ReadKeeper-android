package com.github.tinkzhang.readkeeper

import android.app.Application
import android.util.Log
import timber.log.Timber

class ReadApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object: Timber.Tree() {
                override fun isLoggable(tag: String?, priority: Int): Boolean {
                    if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                        return false;
                    }
                    return true;
                }
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (!isLoggable(tag, priority)) {
                        return;
                    }
                    super.log(priority, tag, message, t);
                }
            })
        }
    }
}