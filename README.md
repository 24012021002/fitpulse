<img width="335" height="772" alt="Screenshot 2026-09-29 011516" src="https://github.com/user-attachments/assets/e702abc1-58fc-4d76-8b6d-18fbefbc86ca" />
<img width="348" height="782" alt="Screenshot 2026-09-29 011505" src="https://github.com/user-attachments/assets/5da14fe6-9dfe-4b1a-b242-2dde66650dc6" />
<img width="352" height="778" alt="Screenshot 2026-09-29 011457" src="https://github.com/user-attachments/assets/5d29c256-a7f0-474f-96a6-c898183b814f" />
<img width="352" height="797" alt="Screenshot 2026-09-29 011441" src="https://github.com/user-attachments/assets/07820bbe-a9b5-4c51-aae6-a7ecb0bb2700" />
<img width="351" height="782" alt="Screenshot 2026-09-29 011413" src="https://github.com/user-attachments/assets/02d414ea-bf00-47a9-8e82-9283e178046b" />
<img width="356" height="792" alt="Screenshot 2026-09-29 011358" src="https://github.com/user-attachments/assets/390aa968-3fa4-47ff-944a-9d0e9d08d676" />
<img width="361" height="792" alt="Screenshot 2026-09-29 011342" src="https://github.com/user-attachments/assets/2c33f8b4-aa8e-4c8c-aa5b-3f1af3563599" />

# FitPulse 🏋️‍♂️

> A lightweight, personal fitness and health companion app built natively for Android using Kotlin.

Hi there! 👋 I created **FitPulse** because I wanted a clean, fast, and privacy-focused fitness tracker that doesn't overwhelm you with ads, subscriptions, or complicated menus. Whether you're tracking your daily water goal, calculating your BMI, or figuring out your daily maintenance calories, FitPulse puts all your essential health numbers in one straightforward place.

---

## 💡 Why I Built This

Most fitness apps today require mandatory cloud sync, complicated sign-ups, or paid subscriptions just to view basic stats. I built FitPulse to:
1. Provide quick, accurate fitness calculations completely offline.
2. Keep user data private and stored locally on the device.
3. Sharpen my native Android development skills using modern Kotlin, Material 3, and SQLite.

---

## 📱 What the App Does

- 🔐 **Secure User Auth & Account System**
  - Built-in registration and login system powered by local SQLite (`fitpulse.db`).
  - Added **SHA-256 cryptographic password hashing** so plain-text credentials are never saved on the device.
  - Remembers login state using `SharedPreferences`.

- ⚖️ **BMI Calculator**
  - Calculates your Body Mass Index (BMI) instantly from height and weight.
  - Visual classification: *Underweight*, *Normal*, *Overweight*, or *Obese*.

- 🔥 **Daily Calorie & Maintenance Estimator**
  - Calculates daily maintenance calories based on your body weight, height, and age so you know how much fuel your body needs.

- 💧 **Daily Water Goal**
  - Estimates your daily hydration requirement (in liters) based on your body weight to keep you accountable.

- 🥗 **Smart Nutrition & Macro Targets**
  - Uses the **Mifflin-St Jeor formula** to calculate Basal Metabolic Rate (BMR).
  - Automatically calculates recommended daily breakdowns for **Protein**, **Carbohydrates**, and **Fats**.

- 🏋️ **Workout & Fitness Guides**
  - Dedicated modules for **Muscle Building** and **Weight Loss** routines and lifestyle recommendations.

- 👤 **Personalized User Profile**
  - Edit and update your personal stats (name, age, height, weight, gender) at any time.
  - Dynamically updates home dashboard greetings and recommendations.

---

## 🛠️ Built With

- **Language:** Kotlin
- **Platform:** Android (Min SDK: 24 / Target SDK: 37)
- **UI:** Android XML with Material Design Components (`MaterialCardView`, Material Buttons, ConstraintLayout)
- **Database:** SQLite (`SQLiteOpenHelper`) with SHA-256 password hashing
- **Data Persistence:** Android `SharedPreferences`
- **Build Tool:** Gradle (Kotlin DSL)

---

## 🧠 What I Learned & Challenges I Solved

- **Local Data Security:** Implementing password hashing using Java's `MessageDigest` (SHA-256) inside `DatabaseHelper` rather than saving cleartext passwords.
- **Edge-to-Edge Design:** Handling `WindowInsetsCompat` across different Android versions to give the UI an immersive, edge-to-edge look.
- **Calculations & Formulations:** Implementing health algorithms like the Mifflin-St Jeor equation accurately in Kotlin code.

---

## 📂 Project Architecture

```text
app/src/main/
├── java/com/example/fitpulse/
│   ├── MainActivity.kt            # Main dashboard & activity launcher
│   ├── LoginActivity.kt           # Sign-in activity
│   ├── RegisterActivity.kt        # User registration
│   ├── DatabaseHelper.kt          # SQLite database & SHA-256 hashing
│   ├── ProfileActivity.kt         # Profile viewing & updating
│   ├── BmiActivity.kt             # BMI calculator logic
│   ├── KcalActivity.kt            # Maintenance calorie estimator
│   ├── WaterActivity.kt           # Hydration tracker
│   ├── NutritionActivity.kt       # Macro breakdown & BMR calculator
│   ├── MuscleBuildingActivity.kt  # Muscle building guides
│   ├── WeightLossActivity.kt      # Weight loss tips
│   └── User.kt                    # Data model
├── res/
│   ├── layout/                    # XML layouts for screens
│   ├── drawable/                  # Custom gradients, cards & icons
│   └── values/                    # Colors, typography & themes
└── AndroidManifest.xml
```

---

## 🚀 How to Run It on Your Machine

1. **Clone this repo:**
   ```bash
   git clone https://github.com/your-username/fitpulse.git
   ```

2. **Open the project:**
   - Launch **Android Studio**.
   - Click **Open** and select the cloned `fitpulse` folder.

3. **Sync & Build:**
   - Allow Gradle to sync dependencies automatically.

4. **Run:**
   - Connect an Android device with USB debugging enabled, or start an Android Emulator.
   - Click the green **Run ▶** button in Android Studio.

---

## 🔮 What's Next? (Roadmap)

- [ ] Daily step counter integration (Pedometer sensor)
- [ ] Visual progress charts and weekly health trends
- [ ] Export personal health logs to PDF/CSV
- [ ] Dark theme toggle

---

## 📜 License

This project is licensed under the MIT License - feel free to fork, learn, and build upon it!
