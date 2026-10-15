# Experiment 9: Data Persistence Using SharedPreferences and SQLite

**Mobile Application Development (MAD) Lab**

## Student Details

- **Name:** Spencer Aaron Fernandes
- **USN:** 25MCAR0123
- **Course:** MCA – General
- **College:** Jain Deemed-to-be University

## 1. Aim

To develop an Android application that demonstrates data persistence using SharedPreferences and SQLite database.

## 2. Objective

- To understand data persistence in Android applications.
- To store and retrieve key-value data using SharedPreferences.
- To create and manage a local SQLite database.
- To perform insert, retrieve, and delete operations on student records.
- To verify that stored data remains available after restarting the application.

## 3. Introduction

Data persistence is the process of storing information so that it remains available even after an application is closed or restarted. Android provides different mechanisms for storing data locally.

This experiment demonstrates two persistence mechanisms: SharedPreferences and SQLite. SharedPreferences is used to save simple student profile information, while SQLite is used to store and manage multiple student records in a structured database.

The application, named **Student Data Manager**, is developed using Java and XML in Android Studio. It features a creative monochromatic user interface using black, white, charcoal, and grey shades.

## 4. Concepts and Technologies Used

### SharedPreferences

SharedPreferences is used to store small amounts of data as key-value pairs. In this application, it stores the student's name and USN.

Operations demonstrated:
- Save profile information.
- Load saved profile information.
- Clear stored profile information.

### SQLite Database

SQLite is a lightweight relational database available on Android devices. It stores structured information in tables and supports SQL operations.

Operations demonstrated:
- Create a student table.
- Insert student records.
- Retrieve and display records.
- Delete selected student records.

### Technologies

- Android Studio
- Java
- XML Layouts
- SharedPreferences API
- SQLite Database
- SQLiteOpenHelper
- Android SDK

## 5. Application Description

The Student Data Manager application contains two main sections.

### 5.1 SharedPreferences Section

This section allows the user to enter a student's name and USN and save them locally. The user can load the saved details or clear the stored profile.

### 5.2 SQLite Database Section

This section allows the user to add student records, view the stored records, and delete individual records. Each record contains a student ID, name, and USN.

### 5.3 UI Design

The application uses a creative monochromatic design with:
- Black, white, charcoal, and grey shades.
- Rounded cards and input fields.
- Consistent typography and spacing.
- Clear button styles and monochrome icons.
- A scrollable layout suitable for Android phone screens.

## 6. Application Flow

1. Launch the application.
2. Enter the student's name and USN in the SharedPreferences section.
3. Save the profile and verify the stored information.
4. Load or clear the saved profile as required.
5. Enter a student's name and USN in the SQLite section.
6. Add the student record to the database.
7. View the list of stored student records.
8. Delete a record using its delete button or student ID.
9. Restart the application and verify data persistence.

## 7. Project Structure

```text
DataPersistence/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/sharedpreferencesandsqlite/
│           │       ├── MainActivity.java
│           │       └── DatabaseHelper.java
│           ├── res/
│           │   ├── drawable/
│           │   │   ├── bg_card.xml
│           │   │   ├── bg_input_field.xml
│           │   │   ├── bg_button_primary.xml
│           │   │   ├── bg_button_secondary.xml
│           │   │   ├── bg_button_danger.xml
│           │   │   ├── bg_display_box.xml
│           │   │   ├── ic_person.xml
│           │   │   ├── ic_badge.xml
│           │   │   ├── ic_database.xml
│           │   │   └── ic_delete.xml
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   └── item_student_record.xml
│           │   └── values/
│           │       ├── colors.xml
│           │       ├── strings.xml
│           │       └── themes.xml
│           └── AndroidManifest.xml
├── README.md
└── screenshots/
    ├── app_home.png
    ├── shared_preferences.png
    ├── sqlite_records.png
    └── delete_record.png
```

*Note: The package name and resource files should match the actual project. Add the screenshots folder and images after capturing your application output.*

## 8. Implementation Details

### MainActivity.java

The main activity manages the user interface and application operations. It handles profile saving, loading and clearing through SharedPreferences and communicates with the database helper to add, display and delete student records.

### DatabaseHelper.java

The database helper extends `SQLiteOpenHelper`. It creates the student table and manages database operations such as inserting records, retrieving all students, and deleting records by ID.

### activity_main.xml

This layout defines the main user interface, including the SharedPreferences section, SQLite database section, input fields, buttons and record display area.

### item_student_record.xml

This layout defines the individual student record card displayed in the SQLite records list, including the student ID, name, USN and delete action.

## 9. Test Cases

### Test Case 1: SharedPreferences — Save and Load Profile

**Objective:** To verify that student profile information can be saved and retrieved.

**Procedure:**
1. Launch the application.
2. Enter a student name and USN.
3. Click **Save Profile**.
4. Verify the success message.
5. Click **Load Profile**.

**Expected Output:** The saved name and USN should be displayed in the profile section.

**Screenshot:** Add `screenshots/shared_preferences.png`.

### Test Case 2: SQLite — Insert and Display Student Records

**Objective:** To verify that student records can be inserted and displayed using SQLite.

**Procedure:**
1. Enter a student name and USN in the SQLite section.
2. Click **Add Student**.
3. Verify the success message.
4. View the list of student records.

**Expected Output:** The newly added student should appear in the records list with an assigned ID, name and USN.

**Screenshot:** Add `screenshots/sqlite_records.png`.

### Test Case 3: SQLite — Delete Student Record

**Objective:** To verify that a selected student record can be deleted from the database.

**Procedure:**
1. Add a student record if none exists.
2. Click the delete icon on the student's record card or enter the student ID in the delete field.
3. Click the corresponding delete button if using the ID field.
4. Verify the success message and refreshed list.

**Expected Output:** The selected student record should be removed from the list and database.

**Screenshot:** Add `screenshots/delete_record.png`.

### Test Case 4: Data Persistence After Restart

**Objective:** To verify that saved data remains available after the application is closed and reopened.

**Procedure:**
1. Save a profile using SharedPreferences.
2. Add one or more student records using SQLite.
3. Close the application completely.
4. Reopen the application.
5. Verify the profile and database records.

**Expected Output:** The saved profile and student records should remain available unless they were explicitly cleared or deleted.

**Screenshot:** Add `screenshots/app_home.png` or capture a separate restart verification screenshot.

## 10. Output

The application should demonstrate the following results:

- Student profile information is saved and retrieved using SharedPreferences.
- Profile information can be cleared.
- Student records are inserted into the SQLite database.
- Stored student records are displayed in the application.
- Individual student records can be deleted.
- Saved information persists after restarting the application.
- The application displays appropriate feedback messages for user actions.

**Screenshots to include:**

1. Main application screen.
2. Saved SharedPreferences profile.
3. SQLite student records list.
4. Deleted record confirmation or updated list.
5. Persistence verification after reopening the application.

## 11. How to Run the Application

1. Open Android Studio.
2. Open the `DataPersistence` project.
3. Wait for Gradle synchronization to finish.
4. Select an Android emulator or connect a physical Android device with USB debugging enabled.
5. Click **Run** to build and launch the application.
6. Test the SharedPreferences and SQLite features using the test cases above.

## 12. Result

The Student Data Manager Android application was developed to demonstrate data persistence using SharedPreferences and SQLite. The application supports saving, retrieving and clearing profile information, as well as inserting, displaying and deleting student records.

## 13. Conclusion

This experiment demonstrates the use of SharedPreferences for lightweight key-value storage and SQLite for structured relational data storage in Android. It provides an understanding of local data persistence and how information can be retained between application sessions.
