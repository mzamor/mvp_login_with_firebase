package com.example.loginconmvpsociedadandroide.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.loginconmvpsociedadandroide.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    var remoteConfig = FirebaseRemoteConfig.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)


        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)



        bt_notifications.setOnClickListener{
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("notificationSuccess", "Config params updated: ${remoteConfig.getString("push_notifications")}")
                    } else {
                        Log.d("notificationFail", "fail")
                    }
                }
        }
    }
}
