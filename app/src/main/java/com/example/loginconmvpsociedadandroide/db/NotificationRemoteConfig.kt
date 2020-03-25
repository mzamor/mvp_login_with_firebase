package com.example.loginconmvpsociedadandroide.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class NotificationRemoteConfig (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val body:String,
    val url:String
)
