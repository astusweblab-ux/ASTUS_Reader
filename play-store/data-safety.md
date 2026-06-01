# Google Play Data Safety Draft

Suggested answers for Play Console:

- Data collected: No
- Data shared with third parties: No
- Data encrypted in transit: Not applicable, no app server data transfer
- Users can request data deletion: Not applicable for server data; users can delete app data on device
- Android cloud backup: Disabled for app data

Local-only data used by the app:

- imported book files and extracted text;
- bookmarks;
- reading progress;
- TTS progress;
- reader and playback settings.

Notes:

- The app opens user-selected documents through Android Storage Access Framework.
- Text-to-speech is performed by the TTS engine selected by the user on the device.
- Android cloud backup is disabled by the app manifest.
