# Selenium Java E2E for AutomationExercise.com

This is a **personal project** created to **practice automation testing** contains end-to-end (E2E) tests for [http://automationexercise.com](http://automationexercise.com) using [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java).

## 🧰 Tech Stack

- [Selenium Java 4.36.0 ](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [Dotenv Java](https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java) for managing environment variables (  ```VALID_EMAIL, VALID_PASSWORD```)
- GitHub Actions for CI/CD

---
## 🛠 Setup & Configuration

To run E2E tests locally, follow these steps:
### 1. Install Project

### 2. Create a .env File
```
VALID_LOGIN_EMAIL=
VALID_LOGIN_PASSWORD=
VALID_LOGIN_NAME_FIRST=

REGISTER_TITLE=
REGISTER_NAME_FIRST=
REGISTER_NAME_LAST=
REGISTER_PASSWORD=
REGISTER_BIRTH_DAY=
REGISTER_BIRTH_MONTH=
REGISTER_BIRTH_YEAR=
REGISTER_ADDRESS=
REGISTER_ADDRESS2=
REGISTER_COUNTRY=
REGISTER_STATE=
REGISTER_CITY=
REGISTER_ZIPCODE=
REGISTER_COMPANY_NAME=
REGISTER_MOBILE_NUMBER=

CARD_NUMBER=
CARD_CVC=
CARD_EXPIRY_MONTH=
CARD_EXPIRY_YEAR=

```

### 3. Run tests using the following command:
```mvn clean test``` - This will run all tests non paralel with help of testng.xml

### 4. Example of tests runing
https://github.com/user-attachments/assets/0ce99c06-32d7-41a6-bdaa-b8658c0ec302


