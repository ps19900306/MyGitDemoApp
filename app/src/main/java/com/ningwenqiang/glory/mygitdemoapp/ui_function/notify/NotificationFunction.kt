package com.ningwenqiang.glory.mygitdemoapp.ui_function.notify

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.core.app.TaskStackBuilder
import com.ningwenqiang.glory.mygitdemoapp.R
import com.ningwenqiang.glory.mygitdemoapp.ui_function.anylayer.FullScreenActivity
import com.ningwenqiang.glory.toollibrary.BasicApp
import com.ningwenqiang.glory.toollibrary.BasicApp.Companion.getContextActivity
import com.ningwenqiang.glory.toollibrary.BasicApp.Companion.getString

/**
create by: 86136
create time: 2020/10/24 11:34
Function description:
 */
class NotificationFunction {

    companion object {

        private const val SYSTEM_MINIMUM_ID = 2048
        private const val SYSTEM_JUMP_ID = 2047
        private const val SYSTEM_REPLY_ID = 2046
        private const val PROGRESS_ID = 2045

        const val KEY_TEXT_REPLY = "key_text_reply"
        private const val CONVERSATION_ID = 12422
        const val REPLY_INTENT_ID_KEY = "conversation_id"
        const val REPLY_BROADCAST_KEY = "reply_broadcast_key"

        const val BROADCAST_PERMISSION_DISC =
            "com.cn.customview.permissions.MY_BROADCAST"
        const val BROADCAST_ACTION_DISC =
            "com.nwq.broadcast.action.reply"

        private val CHANNEL_ID by lazy {
            getContextActivity().packageName
        }

        private val notificationManager by lazy {
            getContextActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        //26版本后需要此方法后次啊能使用Notification
        @SuppressLint("WrongConstant")
        fun createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = getString(R.string.channel_description)
                }
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }
        }


        fun showNotification(imgIcon: Int, title: String, content: String) {
            getContextActivity()
            var builder =
                NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                    .setSmallIcon(imgIcon)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            notificationManager.notify(SYSTEM_MINIMUM_ID, builder.build())
        }


        fun showNotification(imgIcon: Int, title: String, content: String, activity: Class<*>) {
            val intent = Intent(getContextActivity(), activity).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            showNotification(imgIcon, title, content, intent)
        }

        fun showNotification(imgIcon: Int, title: String, content: String, intent: Intent) {
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(getContextActivity(), 0, intent, 0)

            val builder = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            notificationManager.notify(SYSTEM_JUMP_ID, builder.build())
        }


        fun showNotificationAction(
            imgIcon: Int, title: String, content: String,
            actionIcon: Int, actionTitle: String, activity: Class<*>
        ) {
            val snoozeIntent = Intent(getContextActivity(), activity).apply {
                // action = ACTION_SNOOZE
                // putExtra(EXTRA_NOTIFICATION_ID, 0)
            }
            showNotificationAction(imgIcon, title, content, actionIcon, actionTitle, snoozeIntent)
        }

        fun showNotificationAction(
            imgIcon: Int, title: String, content: String,
            actionIcon: Int, actionTitle: String, actionIntent: Intent
        ) {
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(getContextActivity(), 0, actionIntent, 0)

            val builder = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(actionIcon, actionTitle, pendingIntent)
            notificationManager.notify(SYSTEM_JUMP_ID, builder.build())
        }

        fun showNotificationReply(
            imgIcon: Int, title: String, content: String,
            actionIcon: Int, actionTitle: String
        ) {

            //注册广播
//            val receiveBroadCast = ReplyBroadcastReceiver()
//            val filter = IntentFilter()
//            filter.addAction(BROADCAST_ACTION_DISC) // 只有持有相同的action的接受者才能接收此广播
//            getContextActivity().registerReceiver(
//                receiveBroadCast,
//                filter,
//                BROADCAST_PERMISSION_DISC,
//                null
//            )


            var replyLabel = "REPLY"
            var remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            var replyPendingIntent: PendingIntent =
                PendingIntent.getBroadcast(
                    getContextActivity(),
                    CONVERSATION_ID,
                    getMessageReplyIntent(CONVERSATION_ID),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

            var action: NotificationCompat.Action =
                NotificationCompat.Action.Builder(actionIcon, actionTitle, replyPendingIntent)
                    .addRemoteInput(remoteInput)
                    .build()

            val newMessageNotification =
                NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                    .setSmallIcon(imgIcon)
                    .setContentTitle(title)
                    .setContentText(content)
                    .addAction(action)
                    .build()

            // Issue the notification.
            with(NotificationManagerCompat.from(getContextActivity())) {
                notificationManager.notify(SYSTEM_REPLY_ID, newMessageNotification)
            }
        }

        private fun getPendingIntent(conversationId: Int): PendingIntent {
            return PendingIntent.getBroadcast(
                getContextActivity(),
                conversationId,
                getMessageReplyIntent(conversationId),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        private fun getPendingIntent1(conversationId: Int): PendingIntent {
            val intent = Intent(BasicApp.context, MediaService::class.java)
            intent.putExtra("x1x1", conversationId)
            return PendingIntent.getService(
                getContextActivity(),
                conversationId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }


        @SuppressLint("WrongConstant")
        private fun getMessageReplyIntent(conversationId: Int): Intent {
            val intent = Intent()
            intent.action = BROADCAST_ACTION_DISC
            intent.component = ComponentName(
                BasicApp.context,
                "com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.ReplyBroadcastReceiver"
            )
            /**intent.addFlags(0x01000000) 如果是跨包发短信需要加这个 */
            return intent
        }


        fun showNotificationProgress(imgIcon: Int, title: String, content: String) {

            val builder = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID).apply {
                setContentTitle(title)
                setContentText(content)
                setSmallIcon(imgIcon)
                setPriority(NotificationCompat.PRIORITY_LOW)
            }

            val PROGRESS_MAX = 100
            val PROGRESS_CURRENT = 50

            NotificationManagerCompat.from(getContextActivity()).apply {
                // Issue the initial notification with zero progress
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
                notify(PROGRESS_ID, builder.build())
                BasicApp.mHandler.startCountDown(
                    100, 200
                ) {
                    if (it > 0) {
                        builder.setProgress(PROGRESS_MAX, 100 - it, false)
                    } else {
                        builder.setContentText("Download complete")
                            .setProgress(0, 0, false)
                    }
                    notify(PROGRESS_ID, builder.build())
                }
            }
        }

        //大图标
        fun showNotificationBig(imgIcon: Int, bigImgIcon: Bitmap, title: String, content: String) {
            var notification = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bigImgIcon)
                )
                .build()
        }

        //大图标 有下拉框
        fun showNotificationBig1(imgIcon: Int, bigImgIcon: Bitmap, title: String, content: String) {
            var notification = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setLargeIcon(bigImgIcon)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bigImgIcon)
                        .bigLargeIcon(null)
                )
                .build()
        }

        //大文本提醒框
        fun showNotificationText(
            imgIcon: Int,
            avatarIcon: Bitmap,
            title: String,
            content: String,
            bigText: String
        ) {
            var notification = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle(title)
                .setContentText(content)
                .setLargeIcon(avatarIcon)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(bigText)
                )
                .build()
            notificationManager.notify(SYSTEM_REPLY_ID, notification)
        }


        //大文本提醒框
        fun showNotificationInboxStyle(
            imgIcon: Int,
            largeIcon: Bitmap,
            title: String,
            content: String,
            lime1: CharSequence,
            lime2: CharSequence
        ) {
            var notification = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                .setSmallIcon(imgIcon)
                .setContentTitle("5 New mails from  $title")
                .setContentText(content)
                .setLargeIcon(largeIcon)
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine(lime1)
                        .addLine(lime2)
                )
                .build()
            notificationManager.notify(SYSTEM_REPLY_ID, notification)
        }

        fun showNotificationActivity() {
            val resultIntent = Intent(getContextActivity(), FullScreenActivity::class.java)
            // Create the TaskStackBuilder
            val resultPendingIntent = TaskStackBuilder.create(getContextActivity()).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(resultIntent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        }


        fun showNotificationActivityMedia(
            imgIcon: Int,
            prevImgIcon: Int,
            pauseImgIcon: Int,
            nextImgIcon: Int,
            albumArtBitmap: Bitmap
        ) {
            var notification = NotificationCompat.Builder(getContextActivity(), CHANNEL_ID)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(imgIcon)
                // Add media control buttons that invoke intents in your media service
                .addAction(prevImgIcon, "Previous", getPendingIntent1(0)) // #0
                .addAction(pauseImgIcon, "Pause", getPendingIntent1(1)) // #1
                .addAction(nextImgIcon, "Next", getPendingIntent1(3)) // #2
                // Apply the media style template
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button \*/)
                     //.setMediaSession(mediaSession.getSessionToken())
                )
                .setContentTitle("Wonderful music")
                .setContentText("My Awesome Band")
                .setLargeIcon(albumArtBitmap)
                .build()
            notificationManager.notify(SYSTEM_REPLY_ID, notification)
        }


        ////////-----------------------------------

    }
}