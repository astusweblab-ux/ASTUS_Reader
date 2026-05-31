# Contributing

Contributions are welcome.

## Local Setup

1. Install Android Studio and JDK 17.
2. Clone the repository.
3. Open the project in Android Studio.
4. Let Gradle sync dependencies.
5. Run:

```bash
./gradlew :app:assembleDebug
```

## Pull Requests

- Keep changes focused.
- Prefer existing project structure and naming.
- Run `./gradlew :app:assembleDebug` before opening a pull request.
- Describe what changed and why.
- Include screenshots for UI changes when possible.

## Code Style

- Kotlin for app code.
- Jetpack Compose for UI.
- Material 3 components where practical.
- Keep feature code inside the relevant `feature_*` package.

