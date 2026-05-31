package com.astus.reader.core.datastore;

import android.content.Context;
import com.astus.reader.core.ui.ReaderThemeMode;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JI\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015\u00a8\u0006#"}, d2 = {"Lcom/astus/reader/core/datastore/ReaderSettings;", "", "lastBookId", "", "defaultTheme", "Lcom/astus/reader/core/ui/ReaderThemeMode;", "ttsSpeed", "", "ttsPitch", "keepScreenOn", "", "ttsEnginePackage", "(Ljava/lang/String;Lcom/astus/reader/core/ui/ReaderThemeMode;FFZLjava/lang/String;)V", "getDefaultTheme", "()Lcom/astus/reader/core/ui/ReaderThemeMode;", "getKeepScreenOn", "()Z", "getLastBookId", "()Ljava/lang/String;", "getTtsEnginePackage", "getTtsPitch", "()F", "getTtsSpeed", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ReaderSettings {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastBookId = null;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.ui.ReaderThemeMode defaultTheme = null;
    private final float ttsSpeed = 0.0F;
    private final float ttsPitch = 0.0F;
    private final boolean keepScreenOn = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ttsEnginePackage = null;
    
    public ReaderSettings(@org.jetbrains.annotations.Nullable()
    java.lang.String lastBookId, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.ui.ReaderThemeMode defaultTheme, float ttsSpeed, float ttsPitch, boolean keepScreenOn, @org.jetbrains.annotations.Nullable()
    java.lang.String ttsEnginePackage) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastBookId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.ui.ReaderThemeMode getDefaultTheme() {
        return null;
    }
    
    public final float getTtsSpeed() {
        return 0.0F;
    }
    
    public final float getTtsPitch() {
        return 0.0F;
    }
    
    public final boolean getKeepScreenOn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTtsEnginePackage() {
        return null;
    }
    
    public ReaderSettings() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.ui.ReaderThemeMode component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.datastore.ReaderSettings copy(@org.jetbrains.annotations.Nullable()
    java.lang.String lastBookId, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.ui.ReaderThemeMode defaultTheme, float ttsSpeed, float ttsPitch, boolean keepScreenOn, @org.jetbrains.annotations.Nullable()
    java.lang.String ttsEnginePackage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}