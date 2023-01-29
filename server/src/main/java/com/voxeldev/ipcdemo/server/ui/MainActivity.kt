package com.voxeldev.ipcdemo.server.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.voxeldev.ipcdemo.server.R
import com.voxeldev.ipcdemo.server.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
            .getString(defaultMessageKey, defaultMessage) ?: defaultMessage

        with (ActivityMainBinding.inflate(layoutInflater)) {
            setContentView(root)

            textinputedittextMessageMain.setText(message)
            textinputedittextMessageMain.addTextChangedListener(
                onTextChanged = { _, _, _, _ ->
                    textinputlayoutMessageMain.isErrorEnabled = false
                }
            )

            buttonSaveMessageMain.setOnClickListener {
                val text = textinputedittextMessageMain.text?.toString()
                if (text.isNullOrBlank()) {
                    textinputlayoutMessageMain.error = getString(R.string.message_error)
                    return@setOnClickListener
                }

                getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
                    .edit()
                    .putString(defaultMessageKey, text)
                    .apply()

                Toast.makeText(
                    this@MainActivity,
                    R.string.message_saved,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val sharedPreferencesName = "Server"
        const val defaultMessageKey = "message"
        const val defaultMessage = "Who are you, AIDL?"
    }
}