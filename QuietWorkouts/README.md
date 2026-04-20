# Quiet Workouts

Sends a notification every 1-3 hours with a random bodyweight workout, only when on WiFi and unplugged. Workouts are low-impact so they won't bother downstairs neighbors.

## Build with GitHub Actions

Pushing to `main` builds the APK automatically. No local toolchain needed.

1. Create a repo and upload the project files, including `.github/workflows/build.yml`.
2. Commit to `main`. The workflow runs automatically.
3. Open the Actions tab, click the latest run, and download the `QuietWorkouts-debug-apk` artifact at the bottom.
4. Unzip, install the APK. You'll need to allow install-from-unknown-sources for your browser or file manager.

Manual rebuilds: Actions tab -> Build APK -> Run workflow.

## Local build

Open the project in Android Studio (Hedgehog or newer) and run Build -> Build APK(s). Or once the Gradle wrapper is generated:

```
./gradlew assembleDebug
```

Output at `app/build/outputs/apk/debug/app-debug.apk`.

## Config

- Interval: change `MIN_MINUTES` / `MAX_MINUTES` in `WorkoutScheduler.kt`.
- Workout list: edit `Workouts.kt`.
- Network: uses `NetworkType.UNMETERED` (WiFi). Swap to `CONNECTED` if your home WiFi is metered.
- SSID filter: not implemented. Requires `ACCESS_FINE_LOCATION` on Android 10+, so it's left out. Add a `WifiManager.connectionInfo.ssid` check in `WorkoutWorker.doWork()` if you want it.

## Files

```
app/src/main/
  AndroidManifest.xml
  java/com/example/quietworkouts/
    MainActivity.kt
    WorkoutScheduler.kt
    WorkoutWorker.kt
    BootReceiver.kt
    Workouts.kt
  res/layout/activity_main.xml
.github/workflows/build.yml
```
