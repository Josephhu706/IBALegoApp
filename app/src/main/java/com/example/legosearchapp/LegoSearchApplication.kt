package com.example.legosearchapp

import android.app.Application
import android.content.Context

class LegoSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
    companion object {
        var context: Context? = null
            internal set
    }
}