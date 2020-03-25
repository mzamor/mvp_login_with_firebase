package com.example.loginconmvpsociedadandroide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginconmvpsociedadandroide.db.NotificationRemoteConfig
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter() : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private var notifications : List<NotificationRemoteConfig> = emptyList<NotificationRemoteConfig>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NotificationViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
    )
    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    fun setNotifications(notifications: List<NotificationRemoteConfig>) {
        this.notifications = notifications
        notifyDataSetChanged()
    }
    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.tv_title_notification
        private val body = view.tv_body_notification
        fun bind(notification: NotificationRemoteConfig) {
            title.text = notification.title
            body.text = notification.body
        }
    }
}