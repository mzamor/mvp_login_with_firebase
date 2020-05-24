package com.example.loginconmvpsociedadandroide.db

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class NotificationRemoteConfigViewModel( application: Application) : AndroidViewModel(application){
    private val notificationRepository: NotificationRemoteConfigRepository
    val allNotifications: LiveData<List<NotificationRemoteConfig>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)


    init {
        val notificationsDao = NotificationRemoteConfigDatabase.getDatabase(application,scope)?.getNotificationRemoteConfigDao()
        notificationRepository = NotificationRemoteConfigRepository(notificationsDao!!)
        allNotifications = notificationRepository.getAllNotifications()
    }

    fun insert(notification: NotificationRemoteConfig) = scope.launch(Dispatchers.IO) {
        notificationRepository.insert(notification)
    }

    fun insertAll(notifications : List<NotificationRemoteConfig>) = scope.launch(Dispatchers.IO) {
        for(noti in notifications)
            notificationRepository.insert(noti)
    }

    fun deleteAllNotes() = scope.launch(Dispatchers.IO) {
        notificationRepository.deleteAll()
    }

    fun allNotifications(): LiveData<List<NotificationRemoteConfig>> {
        return allNotifications
    }
}