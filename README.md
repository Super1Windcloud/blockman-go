# BlackMango

BlackMango is an Android app prototype inspired by Blockman Go. It is built with Kotlin, Jetpack Compose, Material 3, and SceneView for 3D model rendering.

## Project

- Package: `com.superwindcloud.blackmango`
- Minimum SDK: 26
- Target SDK: 36
- UI: Jetpack Compose + Material 3
- 3D rendering: `io.github.sceneview:sceneview:4.15.1`

## Features

- Home, rooms, games, and shop style screens.
- Shop tab wardrobe layout with category and item cards.
- Shop tab 3D character preview loaded from `models/blockman_go_player_model_textured.glb`.
- Character preview supports horizontal-only rotation.
- Character preview does not render a fake foot shadow under the model.

## Models

Root-level files in `models/` are synced into the app assets during `preBuild`:

```powershell
.\gradlew.bat :app:preBuild
```

The generated assets are placed under:

```text
app/build/generated/modelAssets/models
```

## Build

Compile the debug Kotlin sources:

```powershell
.\gradlew.bat :app:compileDebugKotlin
```

Build a debug APK:

```powershell
.\gradlew.bat :app:assembleDebug
```
