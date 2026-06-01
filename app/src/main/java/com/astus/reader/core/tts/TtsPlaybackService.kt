package com.astus.reader.core.tts

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle
import com.astus.reader.MainActivity
import com.astus.reader.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TtsPlaybackService : Service() {
    @Inject
    lateinit var ttsController: TtsController

    private lateinit var mediaSession: MediaSessionCompat
    private var bookTitle: String = "ASTUS Reader"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        mediaSession = MediaSessionCompat(this, "ASTUSReaderTts").apply {
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    ttsController.resume()
                    refreshPlayback(true)
                }

                override fun onPause() {
                    ttsController.pause()
                    refreshPlayback(false)
                }

                override fun onSkipToNext() {
                    ttsController.next()
                    refreshPlayback(true)
                }

                override fun onSkipToPrevious() {
                    ttsController.previous()
                    refreshPlayback(true)
                }
            })
            isActive = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bookTitle = intent?.getStringExtra(EXTRA_TITLE) ?: bookTitle
        when (intent?.action) {
            ACTION_STOP -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                return START_NOT_STICKY
            }
            ACTION_PAUSE -> {
                ttsController.pause()
                refreshPlayback(false)
            }
            ACTION_PREVIOUS -> {
                ttsController.previous()
                refreshPlayback(true)
            }
            ACTION_NEXT -> {
                ttsController.next()
                refreshPlayback(true)
            }
            ACTION_PLAY -> {
                ttsController.resume()
                refreshPlayback(true)
            }
            ACTION_START -> refreshPlayback(true)
            else -> refreshPlayback(true)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        mediaSession.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun refreshPlayback(isPlaying: Boolean) {
        val state = if (isPlaying) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
        mediaSession.setPlaybackState(
            PlaybackStateCompat.Builder()
                .setActions(
                    PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                )
                .setState(state, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN, 1f)
                .build()
        )
        startForeground(NOTIFICATION_ID, buildNotification(isPlaying))
    }

    private fun buildNotification(isPlaying: Boolean) =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_reader)
            .setColor(getColorCompat())
            .setContentTitle(bookTitle)
            .setContentText(if (isPlaying) "Озвучивание книги" else "Озвучивание приостановлено")
            .setContentIntent(openAppIntent())
            .setOnlyAlertOnce(true)
            .setOngoing(isPlaying)
            .addAction(R.drawable.ic_stat_reader, "Назад", serviceIntent(ACTION_PREVIOUS))
            .addAction(
                R.drawable.ic_stat_reader,
                if (isPlaying) "Пауза" else "Старт",
                serviceIntent(if (isPlaying) ACTION_PAUSE else ACTION_PLAY)
            )
            .addAction(R.drawable.ic_stat_reader, "Вперед", serviceIntent(ACTION_NEXT))
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .build()

    private fun openAppIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun serviceIntent(action: String): PendingIntent {
        val intent = Intent(this, TtsPlaybackService::class.java).setAction(action)
        return PendingIntent.getService(this, action.hashCode(), intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun createNotificationChannel() {
        val manager = getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.tts_channel_name),
            NotificationManager.IMPORTANCE_LOW
        )
        manager.createNotificationChannel(channel)
    }

    private fun getColorCompat(): Int = getColor(R.color.notification_color)

    companion object {
        const val ACTION_START = "com.astus.reader.tts.START"
        const val ACTION_PLAY = "com.astus.reader.tts.PLAY"
        const val ACTION_PAUSE = "com.astus.reader.tts.PAUSE"
        const val ACTION_PREVIOUS = "com.astus.reader.tts.PREVIOUS"
        const val ACTION_NEXT = "com.astus.reader.tts.NEXT"
        const val ACTION_STOP = "com.astus.reader.tts.STOP"
        const val EXTRA_TITLE = "extra_title"
        private const val CHANNEL_ID = "astus_reader_tts"
        private const val NOTIFICATION_ID = 4217

        fun startIntent(context: Context, title: String): Intent =
            Intent(context, TtsPlaybackService::class.java)
                .setAction(ACTION_START)
                .putExtra(EXTRA_TITLE, title)
    }
}
