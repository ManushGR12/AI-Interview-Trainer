# AI Interview Trainer 🎯

An AI-powered Android application that helps freshers and students prepare for job interviews using Gemini AI, Firebase, ML Kit face detection, and Android Speech Recognition.

---

## 🚀 Quick Setup (5 steps)

### 1. Open in Android Studio
File → Open → Select the `AIInterviewTrainer` folder

### 2. Set your Android SDK path in `local.properties`
```properties
sdk.dir=/Users/YOUR_NAME/Library/Android/sdk    # Mac
sdk.dir=C:\\Users\\YOUR_NAME\\AppData\\Local\\Android\\Sdk   # Windows
GEMINI_API_KEY=AIzaSyCDHice3DSofQAuqNQ4Qg88k40Pi96M50U
```

### 3. Add `google-services.json`
Already placed in `app/google-services.json` ✅

### 4. Enable Firebase Services
Go to [Firebase Console](https://console.firebase.google.com) → Project `aiinterviewtrainer`:
- Authentication → Enable **Email/Password** + **Google**
- Firestore Database → Create database (Start in test mode)

### 5. Enable Google Sign-In
In Firebase Console → Authentication → Sign-in method → Google → Enable it → Add your SHA-1 fingerprint

Get SHA-1 from Android Studio terminal:
```bash
./gradlew signingReport
```

### 6. Build & Run
Click ▶️ Run in Android Studio

---

## 📱 App Flow

```
Splash → Register/Login → Profile Setup → Resume Upload
       → AI Analysis → Role Selection → Dashboard
       → Session Setup → [Face Verify (Mock only)]
       → Questions → Voice Answer → AI Feedback
       → Session Summary → Progress & Analytics
```

---

## 🏗️ Architecture

```
├── data/
│   ├── model/          → User, Session, Question, FeedbackResult
│   ├── repository/     → Auth, User, Session, Gemini repositories
│   └── local/          → FileStorageManager (photos + resumes)
├── di/                 → Hilt dependency injection
├── ui/
│   ├── theme/          → Colors, Typography, Theme
│   ├── navigation/     → NavGraph with all 16 routes
│   ├── components/     → Reusable Compose components
│   └── [screens]/      → 16 screens with ViewModels
└── util/
    ├── FaceAnalyzer    → CameraX + ML Kit face detection
    ├── SpeechManager   → Android SpeechRecognizer
    └── Extensions      → Kotlin extension functions
```

---

## 🔥 Firebase Data (Free Tier)

| Collection | Fields stored |
|---|---|
| `users/{uid}` | name, email, college, graduationYear, phone, selectedRoles |
| `sessions/{id}` | userId, role, type, scores, feedback, duration, date |

> ⚠️ Profile photos and resumes are stored **only on device** in internal storage.

---

## 🤖 Gemini AI Features

| Feature | Prompt |
|---|---|
| Resume Analysis | Extracts skills → suggests roles |
| Question Generation | Role + difficulty → 5-20 questions |
| Answer Evaluation | Transcribed answer → score + feedback |
| Session Summary | Overall performance paragraph |

---

## 🛡️ Malpractice Detection (Mock Mode)

- **No face for 3+ seconds** → Warning overlay
- **Multiple faces detected** → Immediate alert
- All detection is **on-device** via ML Kit (free, no server)

---

## 📦 Key Dependencies

| Library | Purpose |
|---|---|
| Jetpack Compose | UI Framework |
| Firebase Auth + Firestore | Authentication + Data |
| Gemini AI (`gemini-1.5-flash`) | AI features |
| ML Kit Face Detection | Malpractice monitoring |
| CameraX | Camera preview |
| Android SpeechRecognizer | Voice-to-text |
| Hilt | Dependency Injection |
| Accompanist Permissions | Runtime permissions |

---

## 🐛 Troubleshooting

**Google Sign-In fails?**
→ Add SHA-1 fingerprint to Firebase project settings

**Gemini returns errors?**
→ Check your API key in `local.properties`

**Camera not working in emulator?**
→ Use a real device for camera/face detection features

**Build fails with Hilt error?**
→ Ensure `kapt` is applied in `app/build.gradle.kts`
