package com.example.loginconmvpsociedadandroide.db
import androidx.lifecycle.LiveData

class NotificationRemoteConfigRepository(private val notificationDao: NotificationRemoteConfigDao) {
    private var allNotifications = notificationDao.getAllNotifications()


    fun getAllNotifications():LiveData<List<NotificationRemoteConfig>>{
        return allNotifications
    }

    suspend fun insert(notification : NotificationRemoteConfig){
        notificationDao.addNotification(notification)
    }

    suspend fun deleteAll(){
        notificationDao.deleteAllNotifications()
    }
}