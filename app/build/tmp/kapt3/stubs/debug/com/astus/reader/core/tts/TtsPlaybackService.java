package com.astus.reader.core.tts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.app.NotificationCompat;
import androidx.media.app.NotificationCompat.MediaStyle;
import com.astus.reader.MainActivity;
import com.astus.reader.R;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\"\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/astus/reader/core/tts/TtsPlaybackService;", "Landroid/app/Service;", "()V", "bookTitle", "", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "buildNotification", "Landroid/app/Notification;", "isPlaying", "", "createNotificationChannel", "", "getColorCompat", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "openAppIntent", "Landroid/app/PendingIntent;", "refreshPlayback", "serviceIntent", "action", "Companion", "app_debug"})
public final class TtsPlaybackService extends android.app.Service {
    private android.support.v4.media.session.MediaSessionCompat mediaSession;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String bookTitle = "ASTUS Reader";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "com.astus.reader.tts.START";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_PLAY = "com.astus.reader.tts.PLAY";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_PAUSE = "com.astus.reader.tts.PAUSE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_PREVIOUS = "com.astus.reader.tts.PREVIOUS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_NEXT = "com.astus.reader.tts.NEXT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "com.astus.reader.tts.STOP";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TITLE = "extra_title";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "astus_reader_tts";
    private static final int NOTIFICATION_ID = 4217;
    @org.jetbrains.annotations.NotNull()
    public static final com.astus.reader.core.tts.TtsPlaybackService.Companion Companion = null;
    
    public TtsPlaybackService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    private final void refreshPlayback(boolean isPlaying) {
    }
    
    private final android.app.Notification buildNotification(boolean isPlaying) {
        return null;
    }
    
    private final android.app.PendingIntent openAppIntent() {
        return null;
    }
    
    private final android.app.PendingIntent serviceIntent(java.lang.String action) {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final int getColorCompat() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/astus/reader/core/tts/TtsPlaybackService$Companion;", "", "()V", "ACTION_NEXT", "", "ACTION_PAUSE", "ACTION_PLAY", "ACTION_PREVIOUS", "ACTION_START", "ACTION_STOP", "CHANNEL_ID", "EXTRA_TITLE", "NOTIFICATION_ID", "", "startIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "title", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent startIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String title) {
            return null;
        }
    }
}