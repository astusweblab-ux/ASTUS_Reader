package com.astus.reader.feature_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Мануал") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ManualCard(
                    title = "Быстрый старт",
                    body = "1. Нажмите кнопку импорта в библиотеке.\n2. Выберите один или несколько файлов.\n3. Откройте книгу и читайте текст или запустите озвучивание нижним плеером."
                )
            }
            item {
                ManualCard(
                    title = "Поддерживаемые форматы",
                    body = "TXT, FB2, FB2.ZIP, EPUB, PDF, DOCX, HTML, HTM, XHTML, MD, Markdown и RTF.\n\nPDF поддерживается, если внутри есть извлекаемый текст. PDF со сканами страниц без OCR может импортироваться с ошибкой."
                )
            }
            item {
                ManualCard(
                    title = "Импорт книг",
                    body = "Импорт работает через системный выбор файлов Android. Можно выбрать один файл или сразу несколько.\n\nЕсли формат не поддерживается или файл поврежден, приложение покажет сообщение и не должно закрываться с ошибкой."
                )
            }
            item {
                ManualCard(
                    title = "Чтение",
                    body = "В меню книги можно изменить размер шрифта, межстрочный интервал и тему: светлая, темная или AMOLED.\n\nПозиция чтения сохраняется автоматически."
                )
            }
            item {
                ManualCard(
                    title = "Озвучивание TTS",
                    body = "Нижний плеер управляет озвучиванием: назад, воспроизведение/пауза и вперед.\n\nНажмите на шестеренку в плеере, чтобы изменить скорость и интонацию. Настройки сохраняются после закрытия приложения."
                )
            }
            item {
                ManualCard(
                    title = "Выбор места озвучивания",
                    body = "Во время чтения можно пролистать текст и нажать на нужную строку или фразу. Если книга уже озвучивается, TTS сразу продолжит с выбранного места."
                )
            }
            item {
                ManualCard(
                    title = "Закладки",
                    body = "Кнопка закладки на экране чтения сохраняет текущую позицию. В блоке закладок можно перейти к сохраненному месту или удалить закладку."
                )
            }
            item {
                ManualCard(
                    title = "Экран не гаснет",
                    body = "В меню чтения есть переключатель 'Экран не гаснет'. Когда он включен, экран остается активным во время чтения."
                )
            }
            item {
                ManualCard(
                    title = "Фоновое воспроизведение",
                    body = "При озвучивании создается уведомление Android. Через него можно управлять воспроизведением из шторки и Bluetooth-кнопками, если устройство их передает приложению."
                )
            }
            item {
                ManualCard(
                    title = "Виджет Android",
                    body = "В списке виджетов Android доступен виджет ASTUS Reader. Добавьте его на рабочий стол для быстрого открытия приложения."
                )
            }
            item {
                ManualCard(
                    title = "Ограничения",
                    body = "Обложки берутся из FB2/EPUB, если они есть внутри файла. Для некоторых нестандартных книг обложка может отсутствовать.\n\nDOCX, HTML, MD и RTF импортируются как текст без сложной верстки."
                )
            }
        }
    }
}

@Composable
private fun ManualCard(
    title: String,
    body: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
