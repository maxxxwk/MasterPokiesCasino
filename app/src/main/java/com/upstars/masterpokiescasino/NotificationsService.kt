package com.upstars.masterpokiescasino

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationsService : FirebaseMessagingService() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.title?.let { title ->
            message.notification?.body?.let { body ->
                showNotification(title, body)
            }
        }
        super.onMessageReceived(message)
    }

    private fun showNotification(title: String, body: String) {
        createNotificationChannel()
        notificationManager.notify(
            0,
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.crown)
                .setContentTitle(title)
                .setContentText(body)
                .build()
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
    }

    private companion object {
        const val CHANNEL_ID = "FIREBASE_NOTIFICATION_CHANNEL"
    }
}
