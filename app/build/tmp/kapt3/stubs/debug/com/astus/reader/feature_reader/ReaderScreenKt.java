package com.astus.reader.feature_reader;

import android.content.Intent;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.core.content.ContextCompat;
import com.astus.reader.core.tts.TtsPlaybackService;
import com.astus.reader.core.ui.ReaderThemeMode;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\bH\u0003\u001a$\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\bH\u0003\u001a\u001e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013H\u0007\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u001a.\u0010\u0018\u001a\u00020\u0019*\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u00a8\u0006\u001f"}, d2 = {"ReaderGold", "Landroidx/compose/ui/graphics/Color;", "J", "BookmarkPanel", "", "state", "Lcom/astus/reader/feature_reader/ReaderState;", "onJump", "Lkotlin/Function1;", "", "onDelete", "", "ReaderOptionsMenu", "onIntent", "Lcom/astus/reader/feature_reader/ReaderIntent;", "ReaderScreen", "viewModel", "Lcom/astus/reader/feature_reader/ReaderViewModel;", "onBack", "Lkotlin/Function0;", "paletteFor", "Lcom/astus/reader/feature_reader/ReaderPalette;", "mode", "Lcom/astus/reader/core/ui/ReaderThemeMode;", "longPressToSelect", "Landroidx/compose/ui/Modifier;", "durationMillis", "", "moveTolerancePx", "", "onLongPress", "app_debug"})
public final class ReaderScreenKt {
    private static final long ReaderGold = 0L;
    
    private static final com.astus.reader.feature_reader.ReaderPalette paletteFor(com.astus.reader.core.ui.ReaderThemeMode mode) {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ReaderScreen(@org.jetbrains.annotations.NotNull()
    com.astus.reader.feature_reader.ReaderViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    private static final androidx.compose.ui.Modifier longPressToSelect(androidx.compose.ui.Modifier $this$longPressToSelect, long durationMillis, float moveTolerancePx, kotlin.jvm.functions.Function0<kotlin.Unit> onLongPress) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReaderOptionsMenu(com.astus.reader.feature_reader.ReaderState state, kotlin.jvm.functions.Function1<? super com.astus.reader.feature_reader.ReaderIntent, kotlin.Unit> onIntent) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BookmarkPanel(com.astus.reader.feature_reader.ReaderState state, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onJump, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDelete) {
    }
}