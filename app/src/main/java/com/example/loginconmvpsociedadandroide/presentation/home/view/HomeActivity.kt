package com.example.loginconmvpsociedadandroide.presentation.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginconmvpsociedadandroide.LoginWithMvpApp
import com.example.loginconmvpsociedadandroide.NotificationAdapter
import com.example.loginconmvpsociedadandroide.R
import com.example.loginconmvpsociedadandroide.db.NotificationRemoteConfig
import com.example.loginconmvpsociedadandroide.db.NotificationRemoteConfigViewModel
import com.example.loginconmvpsociedadandroide.presentation.home.HomeContract
import com.example.loginconmvpsociedadandroide.presentation.home.presenter.HomePresenter
import com.example.loginconmvpsociedadandroide.presentation.login.view.LoginActivity
import com.google.common.reflect.TypeToken
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main2.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {
    @Inject
    lateinit var presenter: HomePresenter


    private var notiViewModel: NotificationRemoteConfigViewModel? = null
    lateinit var notiAdapter: NotificationAdapter
    lateinit var notifications: List<NotificationRemoteConfig>
    var remoteConfig = FirebaseRemoteConfig.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LoginWithMvpApp).getAppComponent()?.inject(this)
        presenter.attachView(this)

        setContentView(R.layout.activity_main2)
        getRemoteConfig()

        bt_logout.setOnClickListener {
            presenter.logOut()
        }
    }

    fun getRemoteConfig() {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(
                        "notificationSuccess",
                        "Config params updated: ${remoteConfig.getString("push_notifications")}"
                    )
                    val gson = Gson()
                    val listPersonType =
                        object : TypeToken<List<NotificationRemoteConfig>>() {}.type
                    notifications =
                        gson.fromJson(remoteConfig.getString("push_notifications"), listPersonType)
                    setAdapter()
                } else {
                    Log.d("notificationFail", "fail")
                }
            }
    }

    fun setAdapter() {
        notiAdapter = NotificationAdapter()
        rv_notifications.layoutManager = LinearLayoutManager(this)
        rv_notifications.adapter = notiAdapter
        saveNotifications()
        rv_notifications.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    fun saveNotifications() {
        notiViewModel =
            ViewModelProviders.of(this).get(NotificationRemoteConfigViewModel::class.java)
        notiViewModel?.deleteAllNotes()
        notiViewModel?.insertAll(notifications)
        notiViewModel?.allNotifications?.observe(this, Observer { noti ->
            noti?.let { notiAdapter.setNotifications(it) }
        })
    }

    override fun navigateToLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }
}
