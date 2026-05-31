package com.astus.reader.core.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u000267B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J2\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\u0004J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0018\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0018\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J \u0010$\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0010\u0010\'\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J\u001a\u0010(\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002J$\u0010*\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u00042\b\u0010,\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0002J\f\u0010/\u001a\u000200*\u00020\u0004H\u0002J\f\u00101\u001a\u00020\u0011*\u000202H\u0002J\f\u00103\u001a\u00020\u0011*\u000204H\u0002J\f\u00105\u001a\u00020\u0004*\u00020\u0004H\u0002\u00a8\u00068"}, d2 = {"Lcom/astus/reader/core/util/BookParser;", "", "()V", "cleanWhitespace", "", "text", "displayName", "contentResolver", "Landroid/content/ContentResolver;", "uri", "Landroid/net/Uri;", "extensionFromMime", "mime", "extractEpubCover", "Lcom/astus/reader/core/util/BookParser$CoverImage;", "files", "", "", "opfPath", "opf", "extractFb2Cover", "root", "Lorg/w3c/dom/Element;", "extractFb2CoverFromText", "parse", "Lcom/astus/reader/core/util/ParsedBook;", "context", "Landroid/content/Context;", "id", "parseDocx", "Lcom/astus/reader/core/util/BookParser$ParsedText;", "bytes", "fallbackName", "parseEpub", "parseFb2", "parseMarkdown", "parsePdf", "parseRtf", "parseZipBook", "readText", "resolveEpubPath", "href", "saveCover", "bookId", "cover", "titleFromName", "name", "isImagePath", "", "readBytes", "Ljava/io/InputStream;", "readCurrentEntry", "Ljava/util/zip/ZipInputStream;", "stripTags", "CoverImage", "ParsedText", "app_debug"})
public final class BookParser {
    @org.jetbrains.annotations.NotNull()
    public static final com.astus.reader.core.util.BookParser INSTANCE = null;
    
    private BookParser() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.util.ParsedBook parse(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    private final java.lang.String displayName(android.content.ContentResolver contentResolver, android.net.Uri uri) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.ParsedText parseFb2(byte[] bytes, java.lang.String fallbackName) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.ParsedText parseZipBook(byte[] bytes, java.lang.String fallbackName) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.ParsedText parsePdf(android.content.Context context, byte[] bytes, java.lang.String fallbackName) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.ParsedText parseDocx(byte[] bytes, java.lang.String fallbackName) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.ParsedText parseEpub(byte[] bytes, java.lang.String fallbackName) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.CoverImage extractFb2Cover(org.w3c.dom.Element root) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.CoverImage extractFb2CoverFromText(java.lang.String text) {
        return null;
    }
    
    private final com.astus.reader.core.util.BookParser.CoverImage extractEpubCover(java.util.Map<java.lang.String, byte[]> files, java.lang.String opfPath, java.lang.String opf) {
        return null;
    }
    
    private final byte[] readCurrentEntry(java.util.zip.ZipInputStream $this$readCurrentEntry) {
        return null;
    }
    
    private final java.lang.String readText(byte[] bytes) {
        return null;
    }
    
    private final java.lang.String parseMarkdown(byte[] bytes) {
        return null;
    }
    
    private final java.lang.String parseRtf(byte[] bytes) {
        return null;
    }
    
    private final byte[] readBytes(java.io.InputStream $this$readBytes) {
        return null;
    }
    
    private final java.lang.String titleFromName(java.lang.String name) {
        return null;
    }
    
    private final java.lang.String saveCover(android.content.Context context, java.lang.String bookId, com.astus.reader.core.util.BookParser.CoverImage cover) {
        return null;
    }
    
    private final java.lang.String resolveEpubPath(java.lang.String opfPath, java.lang.String href) {
        return null;
    }
    
    private final boolean isImagePath(java.lang.String $this$isImagePath) {
        return false;
    }
    
    private final java.lang.String extensionFromMime(java.lang.String mime) {
        return null;
    }
    
    private final java.lang.String stripTags(java.lang.String $this$stripTags) {
        return null;
    }
    
    private final java.lang.String cleanWhitespace(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/astus/reader/core/util/BookParser$CoverImage;", "", "bytes", "", "extension", "", "([BLjava/lang/String;)V", "getBytes", "()[B", "getExtension", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    static final class CoverImage {
        @org.jetbrains.annotations.NotNull()
        private final byte[] bytes = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String extension = null;
        
        public CoverImage(@org.jetbrains.annotations.NotNull()
        byte[] bytes, @org.jetbrains.annotations.NotNull()
        java.lang.String extension) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] getBytes() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getExtension() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final byte[] component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.astus.reader.core.util.BookParser.CoverImage copy(@org.jetbrains.annotations.NotNull()
        byte[] bytes, @org.jetbrains.annotations.NotNull()
        java.lang.String extension) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/astus/reader/core/util/BookParser$ParsedText;", "", "title", "", "author", "content", "cover", "Lcom/astus/reader/core/util/BookParser$CoverImage;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/astus/reader/core/util/BookParser$CoverImage;)V", "getAuthor", "()Ljava/lang/String;", "getContent", "getCover", "()Lcom/astus/reader/core/util/BookParser$CoverImage;", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    static final class ParsedText {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String author = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String content = null;
        @org.jetbrains.annotations.Nullable()
        private final com.astus.reader.core.util.BookParser.CoverImage cover = null;
        
        public ParsedText(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String author, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.Nullable()
        com.astus.reader.core.util.BookParser.CoverImage cover) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAuthor() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContent() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.astus.reader.core.util.BookParser.CoverImage getCover() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.astus.reader.core.util.BookParser.CoverImage component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.astus.reader.core.util.BookParser.ParsedText copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String author, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.Nullable()
        com.astus.reader.core.util.BookParser.CoverImage cover) {
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
}