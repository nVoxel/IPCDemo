package com.voxeldev.ipcdemo.server.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.IBinder
import com.voxeldev.ipcdemo.server.ui.MainActivity

class DemoService : Service() {

    private var sharedPreferences : SharedPreferences? = null

    private val binder = object : IDemoService.Stub() {
        override fun getMessage(): String {
            return sharedPreferences?.getString(
                MainActivity.defaultMessageKey, MainActivity.defaultMessage
            ) ?: MainActivity.defaultMessage
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        sharedPreferences = getSharedPreferences(
            MainActivity.sharedPreferencesName, Context.MODE_PRIVATE
        )
        return binder
    }

    override fun onDestroy() {
        sharedPreferences = null
        super.onDestroy()
    }
}