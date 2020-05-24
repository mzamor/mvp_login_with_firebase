package com.example.loginconmvpsociedadandroide.db

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.common.reflect.TypeToken
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [NotificationRemoteConfig::class],
    version = 1
)

abstract class NotificationRemoteConfigDatabase : RoomDatabase() {
    abstract fun getNotificationRemoteConfigDao(): NotificationRemoteConfigDao
    var remoteConfig = FirebaseRemoteConfig.getInstance()


      private class NotificationDatabaseCallback(
        private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notificationDao = database.getNotificationRemoteConfigDao()
                    // Delete all content here.
                    /*     notificationDao.deleteAllNotifications()
                     var notification =
                           NotificationRemoteConfig(1, "", "", "")
                       notificationDao.addNotification(notification)*/
                }
            }
        }
      }

    companion object {
        @Volatile
        private var INSTANCE: NotificationRemoteConfigDatabase? = null
        var remoteConfig = FirebaseRemoteConfig.getInstance()
        var notifications : List<NotificationRemoteConfig>? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope

        ): NotificationRemoteConfigDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotificationRemoteConfigDatabase::class.java,
                    "notification_database"
                ).addCallback(NotificationDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


