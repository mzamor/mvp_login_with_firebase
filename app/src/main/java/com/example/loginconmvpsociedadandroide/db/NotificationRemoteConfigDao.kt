package com.example.loginconmvpsociedadandroide.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationRemoteConfigDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotification(notification : NotificationRemoteConfig)

    @Query("SELECT * FROM notifications_table")
    fun getAllNotifications() : LiveData<List<NotificationRemoteConfig>>

    @Query("DELETE FROM notifications_table")
    suspend fun deleteAllNotifications()
}