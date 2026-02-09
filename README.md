# DailyFinance End-to-End Automation using Selenium & TestNG

## Project Description
This project implements end-to-end automation testing for the **DailyFinance** web application  
(https://dailyfinance.roadtocareer.net/) using **Selenium WebDriver** and **TestNG**.

The automation validates complete user and admin workflows including user registration with email verification, password reset with negative and positive scenarios, item creation, profile update, secure admin login, CSV-based user registration, and exporting user data from the admin dashboard.  
The framework follows **Page Object Model (POM)** architecture and uses **Allure Report** for detailed test reporting.

---

## Tools & Technologies
- Java  
- Selenium WebDriver  
- TestNG  
- Gradle  
- Page Object Model (POM)  
- Allure Report  
- CSV (test data)  
- Git & GitHub  

---

## Test Scenarios Covered
1. Register a new user and verify congratulations email is received  
2. Reset password – negative test cases  
   - Empty email field  
   - Invalid email format  
3. Send reset link using registered email  
4. Retrieve reset email from Gmail and set a new password  
5. Login with new password and verify successful login  
6. Add two items  
   - One with all fields  
   - One with only mandatory fields  
   - Verify both items appear in item list  
7. Update user email from profile  
8. Login validation  
   - Login with updated email (success)  
   - Login with old email (failure)  
9. Admin login using credentials passed securely from terminal  
10. Search updated user email from admin dashboard and verify result  
11. Register three additional users using data from CSV file  
12. Login as admin, retrieve all users from user table, and write them into a text file  

---

## How to Run the Project

### Run all tests
```bash```
gradle clean test

---


## Standard Test Cases (Documentation)
The complete automation flow is documented as **standard test cases** (including required **negative test cases** such as invalid/empty email during password reset).

📄 **Test Case Sheet (Google Sheets):**  
[GOOGLE_SHEET_LINK_HERE](https://docs.google.com/spreadsheets/d/1crx5uVCSWAEFSBBz2zxJ5ETnBEYOKclw5kaJtNqt7wY/edit?usp=sharing)

---

## Allure Report (Screenshots)
Below are screenshots from the Allure report generated after executing the automation suite.

[Allure Report Summary]<img width="1917" height="870" alt="Allure Report" src="https://github.com/user-attachments/assets/9d66b538-997c-4f9d-a068-4e3d4e7e9277" />

[Allure Report Test Details]<img width="1690" height="879" alt="Behaviors" src="https://github.com/user-attachments/assets/22c59dc1-f276-4bda-b22f-258ba28b0cdf" />


> 📌 Note: Allure generated folders (`allure-results`, `allure-report`) are excluded from GitHub using `.gitignore`.

---

## Demo Video
The demo video shows the **complete automation execution**, including:
- User registration and email verification  
- Password reset (negative and positive test cases)  
- Item creation and validation  
- Profile update and login verification  
- Admin login and user search  
- CSV-based multiple user registration  
- Exporting all users from the admin dashboard to a text file  

🎥 **Automation Demo Video (Google Drive):**  
👉 [AUTOMATED DEMO VIDEO_LINK_HERE](https://drive.google.com/file/d/17k_Ewxohxbtc5P1EG7pWr0eT7-O9ahgc/view?usp=sharing)

---

## Author
**Syed Shahanur Mahmud Pranto**  
📧 Email: prantosm33@gmail.com

