package com.astus.reader.feature_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onOpenManual: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Настройки") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("ASTUS Reader", style = MaterialTheme.typography.headlineMedium)
            ListItem(
                modifier = Modifier.clickable(onClick = onOpenManual),
                leadingContent = { Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = null) },
                headlineContent = { Text("Мануал") },
                supportingContent = { Text("Как пользоваться приложением, импортом, TTS, темами, закладками и виджетом.") }
            )
            ListItem(
                leadingContent = { Icon(Icons.Default.Book, contentDescription = null) },
                headlineContent = { Text("Форматы") },
                supportingContent = { Text("TXT, FB2, FB2.ZIP, EPUB, PDF, DOCX, HTML, MD и RTF.") }
            )
            ListItem(
                leadingContent = { Icon(Icons.Default.RecordVoiceOver, contentDescription = null) },
                headlineContent = { Text("TTS") },
                supportingContent = { Text("Используется установленный Android TextToSpeech: Google, Samsung, RHVoice и другие движки.") }
            )
            ListItem(
                leadingContent = { Icon(Icons.Default.Save, contentDescription = null) },
                headlineContent = { Text("Прогресс") },
                supportingContent = { Text("Последняя книга, позиция чтения, закладки и позиция озвучивания сохраняются локально.") }
            )
        }
    }
}
