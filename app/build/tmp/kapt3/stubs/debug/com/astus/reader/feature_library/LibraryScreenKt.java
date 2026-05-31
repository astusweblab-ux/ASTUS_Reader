package com.astus.reader.feature_library;

import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import com.astus.reader.domain.model.Book;
import com.astus.reader.feature_import.ImportFormats;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003\u001a\u001e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a<\u0010\t\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a \u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a@\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u00a8\u0006\u0017"}, d2 = {"BookCover", "", "book", "Lcom/astus/reader/domain/model/Book;", "modifier", "Landroidx/compose/ui/Modifier;", "BookRow", "onClick", "Lkotlin/Function0;", "ContinueReadingCard", "isImporting", "", "onOpenBook", "Lkotlin/Function1;", "", "onImport", "DeveloperCredit", "onOpenSite", "LibraryScreen", "viewModel", "Lcom/astus/reader/feature_library/LibraryViewModel;", "onOpenSettings", "onOpenManual", "app_debug"})
public final class LibraryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LibraryScreen(@org.jetbrains.annotations.NotNull()
    com.astus.reader.feature_library.LibraryViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOpenBook, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSettings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenManual) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeveloperCredit(androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSite) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ContinueReadingCard(com.astus.reader.domain.model.Book book, boolean isImporting, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOpenBook, kotlin.jvm.functions.Function0<kotlin.Unit> onImport) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BookRow(com.astus.reader.domain.model.Book book, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BookCover(com.astus.reader.domain.model.Book book, androidx.compose.ui.Modifier modifier) {
    }
}