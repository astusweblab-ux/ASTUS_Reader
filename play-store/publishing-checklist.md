# Google Play Publishing Checklist

## Release Artifact

- Build format: Android App Bundle (`.aab`)
- Package name: `com.astus.reader`
- Version code: `2`
- Version name: `0.1.1`
- Minimum SDK: 26
- Target SDK: 36
- Android cloud backup: disabled for app data

## Store Listing

- App name: ASTUS Reader
- Short description: see `play-store/ru-RU/short-description.txt`
- Full description: see `play-store/ru-RU/full-description.txt`
- Privacy policy: `PRIVACY_POLICY.md`
- Website: https://astuslab.com.ua/

## Required Play Console Items

- Create app in Play Console.
- Opt in to Play App Signing.
- Upload signed Android App Bundle.
- Complete app content declarations:
  - Privacy policy
  - Data safety
  - Ads
  - App access
  - Target audience and content
  - Content rating
- Verify the release uses `@mipmap/app_icon` generated from `builds/Copi2.png`.
- Add phone screenshots.
- Add app icon and feature graphic.
- Choose release track: internal testing, closed testing, open testing, or production.
