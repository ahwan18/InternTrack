# InternTrack

InternTrack is a Kotlin Android application that helps students and early-career developers track internship applications, recruitment status, deadlines, and application progress in one place.

## Problem

Internship applications are easy to lose track of when they are managed across email, spreadsheets, notes, and chat messages. InternTrack centralizes application data so users can understand what they applied for, what stage each application is in, and which deadlines need attention.

## What I Built

- CRUD flow for internship applications
- Application detail and edit screens
- Status filtering for application progress
- Dashboard summary for total applications and outcomes
- Interview rate and offer rate calculation
- Deadline tracking with due-soon indicator
- Local persistence with Room Database
- Confirmation dialog for destructive actions

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Room Database
- Coroutines
- Flow and StateFlow
- ViewModel
- Navigation Compose
- MVVM Architecture

## Architecture

The app follows a simple MVVM structure:

```text
UI Layer
-> ViewModel
-> Repository
-> Room Database
```

## Main Screens

- Dashboard and application list
- Add application screen
- Application detail screen
- Edit application screen

## Screenshots

### Dashboard

![Dashboard](screenshots/dashboard.png)

### Add Application

![Add Application](screenshots/add-application.png)

### Application Detail

![Application Detail](screenshots/detail.png)

## Role

Android developer. I designed and built the app flow, local database structure, Compose UI, dashboard logic, and navigation between screens.

## What I Learned

- Building native Android UI using Jetpack Compose
- Managing UI state with StateFlow
- Persisting local data using Room Database
- Structuring an Android app with MVVM
- Implementing CRUD operations with user confirmation
- Creating dashboard analytics from local application data

## Future Improvements

- Add DatePicker for deadline selection
- Add search by company or position
- Add notification reminders
- Add export to CSV
- Add dark mode polish
- Add UI tests
