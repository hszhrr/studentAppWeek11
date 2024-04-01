package com.ubayadev.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubayadev.studentapp.R
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ubayadev.studentapp.util.createNotifChannel

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null

        fun showNotif(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            // com.ubayadev.studentapp

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)

            if(ActivityCompat.checkSelfPermission
                    (instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                return
            }

            manager.notify(1801, notificationBuilder.build())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotifChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, "Notification channel for " + "creating new student")
    }
}