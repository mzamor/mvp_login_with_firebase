package com.example.loginconmvpsociedadandroide.presentation.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginconmvpsociedadandroide.NotificationAdapter
import com.example.loginconmvpsociedadandroide.R
import com.example.loginconmvpsociedadandroide.db.NotificationRemoteConfig
import com.example.loginconmvpsociedadandroide.db.NotificationRemoteConfigViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    var remoteConfig = FirebaseRemoteConfig.getInstance()
    private var notiViewModel: NotificationRemoteConfigViewModel? = null
    private var notiAdapter : NotificationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        notiAdapter = NotificationAdapter()
        rv_notifications.layoutManager = LinearLayoutManager(this)
        rv_notifications.adapter = notiAdapter

        notiViewModel =
            ViewModelProviders.of(this).get(NotificationRemoteConfigViewModel::class.java)
        notiViewModel?.allNotifications()?.observe(this, Observer { contacts ->
            notiAdapter?.setNotifications(contacts!!)
        })

        rv_notifications.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        var notiAdapter = NotificationAdapter()
        rv_notifications.layoutManager = LinearLayoutManager(this)
        rv_notifications.adapter = notiAdapter
        rv_notifications.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        notiViewModel =
            ViewModelProviders.of(this).get(NotificationRemoteConfigViewModel::class.java)
        notiViewModel?.allNotifications?.observe(this, Observer { words ->
            words?.let { notiAdapter.setNotifications(it) }
        })
    }

/*        bt_notifications.setOnClickListener {
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.e(
                            "notificationSuccess",
                            "Config params updated: ${remoteConfig.getString("push_notifications")}"
                        )
                        val gson = Gson()
                        val listPersonType = object : TypeToken<List<NotificationRemoteConfig>>() {}.type
                        var notifications: List<NotificationRemoteConfig> = gson.fromJson(remoteConfig.getString("push_notifications"), listPersonType)
                        var notiAdapter:NotificationAdapter = NotificationAdapter(notifications)
                        rv_notifications.layoutManager = LinearLayoutManager(this)
                        rv_notifications.adapter = notiAdapter
                        rv_notifications.addItemDecoration(
                            DividerItemDecoration(
                                this,
                                LinearLayoutManager.VERTICAL
                            )
                        )

                    } else {
                        Log.d("notificationFail", "fail")
                    }
                }
        }*/
}
