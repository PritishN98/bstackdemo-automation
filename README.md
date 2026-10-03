# Automation Testing Framework for bstackdemo.com

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium%20WebDriver-4.45.0-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.12.0-FF7F00?logo=testng&logoColor=white)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.2-blue)](https://www.extentreports.com/)

---

## 📌 Project Overview
This project is an industry-grade, scalable **Automation Testing Framework** designed for the **bstackdemo.com** e-commerce web platform ([https://bstackdemo.com/](https://bstackdemo.com/)).

It implements the **Page Object Model (POM)** architectural design pattern using **Java**, **Selenium WebDriver**, and **TestNG**. It features cross-browser compatibility, ThreadLocal WebDriver management, centralized explicit synchronization via `WaitUtils`, automated **Failure Screenshots**, and rich interactive **ExtentReports (HTML)** reporting.

---

## 🏗️ Framework Architecture & Project Structure

```text
bstackdemo-automation/
├── pom.xml                                  <-- Maven build dependencies & plugins
├── testng.xml                               <-- Master TestNG Suite (All TC_001 to TC_008)
├── testng-smoke.xml                         <-- Smoke Test Suite (TC_001, TC_004, TC_007)
├── README.md                                <-- GitHub project documentation
├── .gitignore                               <-- Configured for Java, Maven, Eclipse, and Reports
├── .project & .classpath                    <-- Native Eclipse IDE metadata for 1-click import
├── .settings/                               <-- JDT compiler (Java 17) & m2e preferences
├── Screenshots/                             <-- Timestamped failure screenshots
├── TestReport/                              <-- ExtentReports HTML execution reports
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/                       <-- Page Object Model classes
│   │   │   │   ├── LoginPage.java           <-- Sign-in page elements, login actions, errors
│   │   │   │   ├── ProductPage.java         <-- Product catalog, vendor filters, add-to-cart
│   │   │   │   ├── CartPage.java            <-- Sliding cart drawer, count, price, removal
│   │   │   │   └── CheckoutPage.java        <-- Shipping address form & order confirmation
│   │   │   └── utils/                       <-- Framework utilities
│   │   │       ├── ConfigReader.java        <-- Reads configuration from config.properties
│   │   │       ├── WebDriverFactory.java    <-- ThreadLocal WebDriver management (Chrome/Edge/Firefox)
│   │   │       ├── WaitUtils.java           <-- Centralized explicit wait helpers (WebDriverWait)
│   │   │       ├── ScreenshotUtil.java      <-- Captures failure screenshots automatically
│   │   │       └── ExtentManager.java       <-- Configures ExtentReports HTML instance
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java            <-- Setup (@BeforeMethod) & Teardown (@AfterMethod)
│       │   ├── listeners/
│       │   │   └── TestListener.java        <-- TestNG listener attaching logs & screenshots to Extent
│       │   └── tests/                       <-- TestNG Test Classes
│       │       ├── LoginTest.java           <-- TC_001, TC_002, TC_003: Login validations
│       │       ├── AddToCartTest.java       <-- TC_004, TC_005, TC_006: Cart operations
│       │       └── CheckoutTest.java        <-- TC_007, TC_008: Checkout & negative flows
│       └── resources/
│           └── config.properties            <-- Base URL, credentials, browser, timeouts
```

---

## 🧪 Test Scenarios & Coverage Matrix

| Test ID | Class | Module | Type | Description & Expected Outcome |
| :--- | :--- | :--- | :--- | :--- |
| **TC_001** | [`LoginTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/LoginTest.java) | Login | **Smoke** | Login with valid credentials (`demouser` / `testingisfun99`). Asserts successful login and username display. |
| **TC_002** | [`LoginTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/LoginTest.java) | Login | **Negative** | Login with invalid credentials (`demouser` / `wrongpass123`). Asserts error message "Invalid Password" is displayed. |
| **TC_003** | [`LoginTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/LoginTest.java) | Login | **Negative** | Login with empty username and password. Asserts login is prevented and remains on sign-in page. |
| **TC_004** | [`AddToCartTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/AddToCartTest.java) | Cart | **Smoke** | Add single item to cart. Asserts sliding cart drawer opens and cart badge count is 1. |
| **TC_005** | [`AddToCartTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/AddToCartTest.java) | Cart | **Functional** | Add multiple items to cart sequentially. Asserts cart quantity badge and item list reflect total count (3 items). |
| **TC_006** | [`AddToCartTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/AddToCartTest.java) | Cart | **Functional** | Remove item from cart. Asserts item is deleted and cart count is updated. |
| **TC_007** | [`CheckoutTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/CheckoutTest.java) | Checkout | **Smoke** | Complete checkout flow with valid shipping details. Asserts order placement confirmation banner is displayed. |
| **TC_008** | [`CheckoutTest.java`](file:///C:/Users/nagar/eclipse-workspace/bstackdemo-automation/src/test/java/tests/CheckoutTest.java) | Checkout | **Negative** | Attempt checkout flow without adding items. Asserts empty cart message and that checkout cannot proceed. |

---

## ⚙️ Prerequisites & Environment Setup
* **JDK:** Java SE 17 or higher
* **IDE:** Eclipse IDE (with TestNG & m2e plugins installed)
* **Browser:** Google Chrome (default), Microsoft Edge, or Mozilla Firefox
* **Build Tool:** Apache Maven 3.8+

---

## 🚀 How to Import and Run in Eclipse IDE

### 1. Import Project into Eclipse
1. Open **Eclipse IDE**.
2. Click **File** $\rightarrow$ **Import...**
3. Expand **Maven**, select **Existing Maven Projects**, and click **Next**.
4. In **Root Directory**, browse to:
   ```text
   C:\Users\<your-user>\eclipse-workspace\bstackdemo-automation
   ```
5. Check the `pom.xml` checkbox and click **Finish**.
6. Eclipse will build the workspace and resolve dependencies automatically.

### 2. Run the Full Test Suite (`testng.xml`)
1. In the Eclipse **Package Explorer**, expand `bstackdemo-automation`.
2. Right-click on **`testng.xml`**.
3. Select **Run As** $\rightarrow$ **TestNG Suite**.
4. The tests will launch Chrome, execute all 8 scenarios, and report results in the TestNG view.

### 3. Run Individual Test Classes
1. Expand `src/test/java/tests`.
2. Right-click on any test class (e.g., `LoginTest.java`, `AddToCartTest.java`, or `CheckoutTest.java`).
3. Select **Run As** $\rightarrow$ **TestNG Test**.

### 4. Run via Maven (Command Line or Eclipse)
* In Eclipse: Right-click project $\rightarrow$ **Run As** $\rightarrow$ **Maven test**.
* Or from the command line in the project root:
  ```bash
  mvn clean test
  ```
* To run only the smoke suite:
  ```bash
  mvn test -Dsurefire.suiteXmlFiles=testng-smoke.xml
  ```

---

## 📊 Test Reporting & Screenshots

### 1. ExtentReports (Rich Interactive HTML)
* Generated automatically under:
  ```text
  bstackdemo-automation/TestReport/BStackDemo_Report_<timestamp>.html
  ```
* Open the HTML file in any browser to inspect visual pass/fail graphs, execution timelines, step details, and embedded failure screenshots.

### 2. Automated Failure Screenshots
* Any failing assertion triggers `ScreenshotUtil` automatically.
* Screenshots are saved to:
  ```text
  bstackdemo-automation/Screenshots/<testName>_<timestamp>.png
  ```
* Automatically embedded directly into the ExtentReport dashboard.

### 3. TestNG Built-in Reports
* Generated after execution under:
  ```text
  bstackdemo-automation/test-output/index.html
  bstackdemo-automation/test-output/emailable-report.html
  ```

---

## 💡 Key Framework Design Highlights
* **Page Object Model (POM):** Clean separation of UI elements, actions, and test assertions.
* **ThreadLocal WebDriver:** Supports safe multi-threaded parallel execution across browsers.
* **WaitUtils Synchronization:** Comprehensive explicit wait methods prevent synchronization issues on dynamic React elements.
* **Externalized Configuration:** `config.properties` manages URLs, credentials, timeouts, and browser selection cleanly.
* **Robust Negative Testing:** Evaluates authentication failures, input validation errors, and empty cart checkout prevention.
