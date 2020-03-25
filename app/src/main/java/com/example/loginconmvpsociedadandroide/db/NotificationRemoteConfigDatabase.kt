package com.example.loginconmvpsociedadandroide.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
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
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notificationDao = database.getNotificationRemoteConfigDao()
                    // Delete all content here.
                    notificationDao.deleteAllNotifications()
                    var notification = NotificationRemoteConfig(1,"testTitle","testBody","testUrl")
                    notificationDao.addNotification(notification)


    /*        remoteConfig.fetchAndActivate()
                        .addOnCompleteListener(Activity()) { task ->
                            if (task.isSuccessful) {
                                Log.e(
                                    "notificationSuccess",
                                    "Config params updated: ${remoteConfig.getString("push_notifications")}"
                                )
                                val gson = Gson()
                                val listPersonType =
                                    object : TypeToken<List<NotificationRemoteConfig>>() {}.type
                                var notifications: List<NotificationRemoteConfig> = gson.fromJson(
                                    remoteConfig.getString("push_notifications"),
                                    listPersonType
                                )

                            } else {
                                Log.d("notificationFail", "fail")
                            }
                        }*/
                }
            }
    } }

    companion object {
        @Volatile
        private var INSTANCE: NotificationRemoteConfigDatabase? = null
        var remoteConfig = FirebaseRemoteConfig.getInstance()


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


