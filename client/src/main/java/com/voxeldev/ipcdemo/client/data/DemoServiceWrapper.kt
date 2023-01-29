package com.voxeldev.ipcdemo.client.data

import IDemoService
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.voxeldev.ipcdemo.client.R

class DemoServiceWrapper(private val activity: Activity) {

    private var isServiceConnected = false

    private var iDemoService: IDemoService? = null

    private val demoServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iDemoService = IDemoService.Stub.asInterface(service)
            isServiceConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            iDemoService = null
            isServiceConnected = false
        }

    }

    fun bindService() {
        Intent("com.voxeldev.ipcdemo.DEMO_SERVICE").also { intent ->
            intent.setPackage("com.voxeldev.ipcdemo.server")
            activity.bindService(intent, demoServiceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun unbindService() {
        if (iDemoService != null || isServiceConnected) {
            activity.unbindService(demoServiceConnection)
        }

        iDemoService = null
        isServiceConnected = false
    }

    fun getMessage(): String? {
        return if (isServiceConnected) {
            iDemoService?.message
        } else {
            null
        }
    }
}