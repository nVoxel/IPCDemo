package com.voxeldev.ipcdemo.client.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.voxeldev.ipcdemo.client.R
import com.voxeldev.ipcdemo.client.data.DemoServiceWrapper
import com.voxeldev.ipcdemo.client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val demoServiceWrapper by lazy {
        DemoServiceWrapper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with (ActivityMainBinding.inflate(layoutInflater)) {
            setContentView(root)

            buttonBindServiceMain.setOnClickListener {
                demoServiceWrapper.bindService()

                buttonUnbindServiceMain.isEnabled = true
                buttonGetMessageMain.isEnabled = true
            }

            buttonUnbindServiceMain.setOnClickListener {
                demoServiceWrapper.unbindService()

                buttonUnbindServiceMain.isEnabled = false
                buttonGetMessageMain.isEnabled = false
            }

            buttonGetMessageMain.setOnClickListener {
                val message = demoServiceWrapper.getMessage()

                if (message != null) {
                    Toast.makeText(
                        this@MainActivity, message, Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity, R.string.service_not_connected, Toast.LENGTH_SHORT
                    ).show()

                    buttonUnbindServiceMain.isEnabled = false
                    buttonGetMessageMain.isEnabled = false
                }
            }
        }
    }

    override fun onDestroy() {
        demoServiceWrapper.unbindService()
        super.onDestroy()
    }
}