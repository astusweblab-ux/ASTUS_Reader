# ASTUS Reader

[![Android build](https://github.com/astusweblab-ux/ASTUS_Reader/actions/workflows/android-build.yml/badge.svg)](https://github.com/astusweblab-ux/ASTUS_Reader/actions/workflows/android-build.yml)

ASTUS Reader is a modern Android reading app with text reading, Android TextToSpeech playback, bookmarks, reading progress, themes, and a home screen widget.

The project is built with Kotlin, Jetpack Compose, Material 3, Room, DataStore, Hilt, Coroutines, and StateFlow.

## Features

- Library screen with search, book title, author, format, and cover preview.
- Import via Android Storage Access Framework.
- Multi-file import.
- Reader screen with font size, line spacing, and theme settings.
- Themes: Light, Dark, and AMOLED.
- Reading progress autosave.
- Bookmarks: add, delete, and jump.
- Android TextToSpeech playback.
- TTS speed and pitch controls with persistent settings.
- Long-press a sentence for 1 second to choose where TTS should continue from.
- Haptic feedback when a new TTS position is selected.
- Foreground playback service with Android notification controls.
- Android app widget for quick launch.
- In-app manual section.
- Splash/loading screen with ASTUS Reader artwork.

## Supported Formats

ASTUS Reader currently imports:

- TXT
- FB2
- FB2.ZIP
- EPUB
- PDF
- DOCX
- HTML / HTM / XHTML
- Markdown / MD
- RTF

Notes:

- PDF import requires extractable text. Scanned PDFs without OCR may not import as readable text.
- DOCX, HTML, Markdown, and RTF are imported as readable text without preserving complex layout.
- Covers are extracted from FB2/EPUB when the source file contains a standard embedded cover.
- Unsupported or damaged files should show a user-facing message instead of crashing the app.

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Clean Architecture-style package layout
- MVI-style ViewModel state and intents
- Room Database
- DataStore Preferences
- Hilt
- Kotlin Coroutines
- StateFlow
- Navigation Compose
- Coil
- Android TextToSpeech API
- Foreground Service
- Android App Widget
- PDFBox Android for PDF text extraction

## Requirements

- Android Studio
- JDK 17
- Android SDK 36
- Gradle Wrapper included

Android configuration:

- `minSdk = 26`
- `targetSdk = 36`
- `compileSdk = 36`

## Build

From the project root:

```bash
./gradlew :app:assembleDebug
```

On Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

The generated debug APK will be available at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Project Structure

```text
app/src/main/java/com/astus/reader
|-- core
|   |-- database
|   |-- datastore
|   |-- di
|   |-- tts
|   |-- ui
|   |-- util
|-- data
|-- domain
|-- feature_import
|-- feature_library
|-- feature_reader
|-- feature_settings
|-- feature_tts
|-- widget
```

## Usage

1. Open the app.
2. Press the import button on the library screen.
3. Select one or several supported book files.
4. Open a book from the library.
5. Use the bottom player to start or pause TTS playback.
6. Press the player settings button to adjust speed and pitch.
7. Long-press a sentence for 1 second to continue TTS from that sentence.
8. Use the reader menu to change theme, font size, line spacing, TTS engine, and screen-on behavior.

## Privacy

ASTUS Reader stores books, progress, bookmarks, and TTS settings locally on the device. The app does not require an external server for reading or playback.

## Developer

Developed by ASTUS LAB WEB STUDIO.

Website: https://astuslab.com.ua/

## License

This project is distributed under the MIT License. See [LICENSE](LICENSE).
