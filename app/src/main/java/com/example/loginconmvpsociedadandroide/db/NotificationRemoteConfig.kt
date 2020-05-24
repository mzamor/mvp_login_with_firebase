package com.example.loginconmvpsociedadandroide.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class NotificationRemoteConfig (
    val title:String,
    val body:String,
    val url:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
