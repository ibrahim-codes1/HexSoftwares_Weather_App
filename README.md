# 🌦️ HexSoftwares Weather App

A simple Android Weather Application developed in **Java** using **Android Studio**. The application allows users to search for any city and displays the current weather information using the **Open-Meteo API**.

---

## 📱 Features

- 🔍 Search weather by city name
- 🌡️ Display current temperature
- 🌬️ Display wind speed
- ☁️ Display weather condition
- 📍 Display location name
- ⏳ Loading indicator while fetching data
- ❌ Error handling for invalid city names

---

## 🛠️ Technologies Used

- Java
- Android Studio
- XML
- Retrofit
- Gson Converter
- Open-Meteo API

---

## 📂 Project Structure

```
WeatherApp/
│
├── app/
│   ├── java/
│   │   ├── api/
│   │   │   ├── GeoApi.java
│   │   │   └── WeatherApi.java
│   │   │
│   │   ├── model/
│   │   │   ├── GeoResponse.java
│   │   │   └── WeatherResponse.java
│   │   │
│   │   └── MainActivity.java
│   │
│   └── res/
│       └── layout/
│           └── activity_main.xml
│
├── build.gradle
├── AndroidManifest.xml
└── README.md
```

---

## ⚙️ Installation

1. Clone the repository

```bash
git clone https://github.com/ibrahim-codes1/HexSoftwares_Weather_App.git
```

2. Open the project in **Android Studio**.

3. Sync Gradle.

4. Connect an Android device or start an emulator.

5. Click **Run**.

---

## 📸 Application Workflow

1. Enter a city name.
2. Press **Get Weather**.
3. The app searches for the city coordinates.
4. Weather data is fetched from Open-Meteo.
5. Current weather information is displayed on the screen.

---

## 🌐 API Used

**Open-Meteo API**

- Geocoding API
- Weather Forecast API

No API key is required.

---

## 🎯 Project Objective

This project was developed as part of the **HexSoftwares Android Development Internship**.

The objective of the project is to:

- Build a weather application in Android.
- Consume REST APIs using Retrofit.
- Parse JSON responses using Gson.
- Display real-time weather information in a user-friendly interface.

---

## 👨‍💻 Developer

**Muhammad Ibrahim**

Android Developer

GitHub:
https://github.com/ibrahim-codes1

---

## 📄 License

This project is developed for educational and internship purposes under the HexSoftwares Internship Program.
