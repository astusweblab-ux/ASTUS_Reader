package com.astus.reader.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.astus.reader.core.ui.ReaderThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore(name = "astus_reader_settings")

data class ReaderSettings(
    val lastBookId: String? = null,
    val defaultTheme: ReaderThemeMode = ReaderThemeMode.Light,
    val ttsSpeed: Float = 1.0f,
    val ttsPitch: Float = 1.0f,
    val keepScreenOn: Boolean = true,
    val ttsEnginePackage: String? = null
)

@Singleton
class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val LastBookId = stringPreferencesKey("last_book_id")
        val Theme = stringPreferencesKey("theme")
        val TtsSpeed = floatPreferencesKey("tts_speed")
        val TtsPitch = floatPreferencesKey("tts_pitch")
        val KeepScreenOn = booleanPreferencesKey("keep_screen_on")
        val TtsEngine = stringPreferencesKey("tts_engine")
    }

    val settings: Flow<ReaderSettings> = context.settingsDataStore.data.map { preferences ->
        ReaderSettings(
            lastBookId = preferences[Keys.LastBookId],
            defaultTheme = preferences[Keys.Theme]?.let(ReaderThemeMode::valueOf) ?: ReaderThemeMode.Light,
            ttsSpeed = preferences[Keys.TtsSpeed] ?: 1.0f,
            ttsPitch = preferences[Keys.TtsPitch] ?: 1.0f,
            keepScreenOn = preferences[Keys.KeepScreenOn] ?: true,
            ttsEnginePackage = preferences[Keys.TtsEngine]
        )
    }

    suspend fun setLastBook(bookId: String) {
        context.settingsDataStore.edit { it[Keys.LastBookId] = bookId }
    }

    suspend fun setTheme(mode: ReaderThemeMode) {
        context.settingsDataStore.edit { it[Keys.Theme] = mode.name }
    }

    suspend fun setTtsSpeed(speed: Float) {
        context.settingsDataStore.edit { it[Keys.TtsSpeed] = speed }
    }

    suspend fun setTtsPitch(pitch: Float) {
        context.settingsDataStore.edit { it[Keys.TtsPitch] = pitch }
    }

    suspend fun setKeepScreenOn(enabled: Boolean) {
        context.settingsDataStore.edit { it[Keys.KeepScreenOn] = enabled }
    }

    suspend fun setTtsEngine(packageName: String?) {
        context.settingsDataStore.edit { preferences ->
            if (packageName == null) preferences.remove(Keys.TtsEngine) else preferences[Keys.TtsEngine] = packageName
        }
    }
}
