# OpenCart Test Automation Framework

Automated UI testing project for the OpenCart demo e-commerce application.

Target application:

https://opencart.abstracta.us

The project was developed as a final QA Automation project and covers the main user flows of the OpenCart web application.

The automation framework is based on Java, Selenium WebDriver and TestNG and follows the Page Object Model approach.

In addition to standard TestNG execution, the project includes a custom desktop Test Launcher that allows tests to be selected and executed through a graphical interface.

The application can also be packaged as a standalone Windows application using `jpackage`.

---

## Project Goals

The main goals of the project are:

- automate the main OpenCart user scenarios;
- organize UI automation using the Page Object Model;
- separate page interaction logic from test logic;
- support different browsers;
- provide readable test execution results;
- provide execution logs and screenshots;
- allow tests to be launched without opening the IDE;
- provide a Windows desktop launcher for easier test execution.

---

# Technologies

The project uses the following technologies:

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Selenium WebDriver 4.40.0 | Browser automation |
| TestNG 7.11.0 | Test execution and assertions |
| Gradle | Build and dependency management |
| WebDriverManager 6.3.4 | Automatic browser driver management |
| Swing | Desktop Test Launcher interface |
| Logback 1.6.3 | Logging |
| jpackage | Windows application packaging |
| WiX Toolset | Windows EXE installer creation |

---

# Test Automation Architecture

The project follows the Page Object Model approach.

The main automation layers are:

```text
Tests
  |
  v
Page Objects
  |
  v
Core / WebDriver
  |
  v
OpenCart Web Application
```

The test classes describe test scenarios and assertions.

Page classes contain locators and actions related to specific OpenCart pages.

Core classes are responsible for browser initialization, common WebDriver operations and test setup.

---

# Project Structure

```text
PrVersion1
│
├── src
│   ├── main
│   │   └── java
│   │       └── opencart
│   │           │
│   │           ├── core
│   │           │   ├── ApplicationManager.java
│   │           │   ├── BaseHelper.java
│   │           │   └── BasePage.java
│   │           │
│   │           ├── launcher
│   │           │   └── TestLauncher.java
│   │           │
│   │           └── pages
│   │               ├── AccountPage.java
│   │               ├── CartPage.java
│   │               ├── CategoryPage.java
│   │               ├── CheckoutPage.java
│   │               ├── ContactUsPage.java
│   │               ├── CurrencyPage.java
│   │               ├── DownloadsPage.java
│   │               ├── HomePage.java
│   │               ├── LoginPage.java
│   │               ├── OrderHistoryPage.java
│   │               ├── ProductComparePage.java
│   │               ├── ProductPage.java
│   │               ├── RegisterPage.java
│   │               ├── ReturnPage.java
│   │               ├── SearchResultsPage.java
│   │               ├── ShoppingCartPage.java
│   │               └── WishListPage.java
│   │
│   └── test
│       └── java
│           └── opencart
│               │
│               ├── core
│               │   ├── TestBase.java
│               │   └── KarynaTestBase.java
│               │
│               └── tests
│                   ├── AccountHistoryTests.java
│                   ├── AddressValidationTests.java
│                   ├── CalculatorTect.java
│                   ├── CategoryDisplayAndSortTests.java
│                   ├── CategoryNavigationTests.java
│                   ├── ContactUsTests.java
│                   ├── CurrencyTests.java
│                   ├── CustomizableProductOptionsTest.java
│                   ├── DiscountCodesTest.java
│                   ├── GuestCheckoutTests.java
│                   ├── LoginTests.java
│                   ├── ProductComparisonTests.java
│                   ├── ProductReviewTest.java
│                   ├── RegisteredCheckoutTests.java
│                   ├── RegistrationTests.java
│                   ├── RegisterTests.java
│                   ├── ReturnRequestTests.java
│                   ├── ReturningCustomerCheckoutTests.java
│                   ├── SearchTests.java
│                   ├── ShippingPaymentTests.java
│                   ├── ShoppingCartOperationsTest.java
│                   ├── TermsAndConditionsTest.java
│                   ├── UserAuthenticationTest.java
│                   └── WishListTests.java
│
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

---

# Core Components

## ApplicationManager

`ApplicationManager` is responsible for WebDriver initialization.

It supports browser selection and starts the required WebDriver instance.

Supported browsers:

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

WebDriverManager is used to automatically resolve and configure the required browser drivers.

---

## BasePage

`BasePage` contains common functionality used by different page objects.

Common operations include:

- element clicking;
- text input;
- JavaScript clicking;
- waiting for elements;
- checking element presence;
- WebDriverWait operations.

This helps avoid repeating the same Selenium code across different page objects.

---

## TestBase

`TestBase` provides common setup and cleanup logic for automated tests.

Before each test, the required browser is initialized.

After test execution, the WebDriver session is closed.

The browser can be passed through a system property:

```text
browser
```

Example:

```powershell
-Dbrowser=chrome
```

---

# Page Object Model

Each major OpenCart page is represented by a separate Page Object.

Examples:

```text
HomePage
RegisterPage
LoginPage
CategoryPage
ProductPage
ShoppingCartPage
CheckoutPage
WishListPage
ContactUsPage
ReturnPage
```

Page Objects contain:

- Selenium locators;
- actions available on the page;
- navigation methods;
- methods for retrieving page information.

Test classes use these page methods instead of directly interacting with page elements whenever possible.

---

# Automated Test Coverage

The project covers the main functional areas of OpenCart.

## Registration

The automation verifies:

- successful user registration;
- registration with an already existing email;
- password mismatch validation;
- required field validation;
- newsletter-related registration behavior.

Because OpenCart does not allow the same email address to be registered multiple times, positive registration scenarios require unique user data.

---

## Authentication

Authentication tests cover:

- login with valid credentials;
- login with invalid credentials;
- invalid password handling;
- access to the customer account after successful authentication.

---

## Currency

Currency functionality verifies that:

- the user can change the displayed currency;
- product prices change after switching currency;
- supported currencies can be selected;
- unsupported currency values are rejected by the automation logic.

---

## Search

Search automation covers:

- searching for an existing product;
- searching for a non-existing product;
- validation of the "no products found" message;
- search by product description;
- search inside a selected category;
- search including subcategories.

---

## Category Navigation

Category tests verify:

- opening catalog categories from the main menu;
- correct category URL;
- expected products inside categories;
- navigation through different product groups.

Examples include:

```text
Desktops
Laptops & Notebooks
Components
Tablets
Phones & PDAs
Cameras
```

---

## Product Display and Sorting

The automation verifies:

- Grid View;
- List View;
- switching between display modes;
- sorting products by price;
- sorting products by name;
- sorting products by rating.

---

## Product Options

Product configuration tests cover different option types available on OpenCart products.

Supported scenarios include:

- Select;
- Radio;
- Checkbox;
- Text;
- Textarea;
- File Upload;
- Date;
- Time;
- Date & Time.

---

## Product Reviews

Review tests verify:

- submitting a valid product review;
- review text validation;
- OpenCart review confirmation behavior.

---

## Product Comparison

Product comparison automation covers:

- adding products to comparison;
- opening the comparison page;
- verifying compared products;
- checking product specification information.

---

## Wish List

Wish List automation verifies:

- behavior for unauthenticated users;
- login requirement for guest users;
- adding products to the Wish List;
- opening the Wish List;
- checking that the selected product is present;
- removing products from the Wish List.

---

## Shopping Cart

Shopping Cart tests cover:

- adding products to the cart;
- changing product quantity;
- removing products;
- verifying cart state.

---

## Coupons and Gift Certificates

Discount functionality covers:

- coupon code validation;
- invalid coupon behavior;
- gift certificate validation;
- invalid gift certificate behavior.

---

## Shipping and Taxes

Shipping automation verifies:

- shipping calculation;
- country selection;
- region selection;
- postcode input;
- shipping quote calculation;
- Flat Shipping Rate availability.

---

# Checkout Automation

Several checkout scenarios are included in the project.

## Guest Checkout

The Guest Checkout flow covers:

- adding a product to cart;
- opening Checkout;
- selecting Guest Checkout;
- entering customer information;
- entering billing address;
- selecting country and region;
- selecting payment options;
- accepting Terms & Conditions;
- confirming the order.

---

## Returning Customer Checkout

Returning Customer Checkout verifies:

- checkout login;
- existing customer credentials;
- access to Billing Details after authentication.

---

## Registered Customer Checkout

Registered customer checkout covers the checkout process for an already authenticated OpenCart account.

The scenario uses the customer's existing account and address information.

---

## Billing and Delivery Address

Address validation tests cover:

- empty billing address validation;
- valid billing address;
- empty delivery address validation;
- valid delivery address;
- transition from billing details to delivery details.

---

## Shipping and Payment

Shipping and payment tests verify:

- Flat Shipping Rate;
- shipping method selection;
- payment method selection;
- Cash on Delivery / supported payment options;
- transition to the order confirmation step.

---

## Terms & Conditions

Tests verify:

- checkout behavior without accepting Terms & Conditions;
- successful continuation after accepting Terms & Conditions.

---

# Contact Us

Contact form automation covers:

- opening the Contact Us page;
- entering customer information;
- entering an enquiry;
- submitting the contact form.

---

# Product Returns

Return functionality verifies:

- opening the Product Returns page;
- required field validation;
- submitting a valid product return request;
- successful return request confirmation.

---

# Customer Account

Account-related automation covers:

- My Account page;
- Order History;
- order details;
- Downloads;
- navigation between account sections.

---

# Cross-Browser Testing

The framework supports execution in:

```text
Chrome
Firefox
Edge
```

The browser is selected dynamically by `ApplicationManager`.

The desktop Test Launcher also allows the required browser to be selected before execution.

More than one browser can be selected from the launcher.

When multiple browsers are selected, the selected test groups are executed for each browser.

---

# Desktop Test Launcher

The project includes a custom Swing-based graphical Test Launcher.

Main class:

```text
opencart.launcher.TestLauncher
```

The launcher was created to allow test execution without manually selecting TestNG classes from the IDE.

---

## Launcher Features

The launcher provides:

- browser selection;
- test group selection;
- Select All option;
- Clear option;
- execution of selected tests;
- execution in multiple browsers;
- screenshot option;
- real-time execution output;
- test result summary;
- execution log export.

---

## Available Test Groups

The launcher contains groups for the main OpenCart functionality:

```text
Registration
Negative Registration
Authentication
Currency
Search
Categories
Grid / List / Sorting
Product Details
Product Options
Product Reviews
Product Comparison
Wishlist
Shopping Cart
Coupons / Gift Certificates
Shipping & Taxes
Guest Checkout
Returning Checkout
Billing / Delivery
Shipping / Payment
Terms & Conditions
Contact Us
Product Returns
Order History / Downloads
Responsive / Cross-browser
```

Some launcher items may represent several TestNG classes that belong to the same functional area.

---

# Running the Project

## Requirements

For development and direct Gradle execution:

- Windows
- Java 21
- Internet connection
- supported browser
- Gradle Wrapper included in the project

The project contains:

```text
gradlew
gradlew.bat
```

Therefore Gradle does not need to be installed globally.

---

# Compile the Project

From the project root:

```powershell
.\gradlew clean testClasses
```

This command compiles the application and test source code.

---

# Run Standard Tests

All TestNG tests can be executed using:

```powershell
.\gradlew test
```

---

# Run the Desktop Launcher

Use:

```powershell
.\gradlew launcher
```

The OpenCart Automation Test Launcher window will open.

Select:

1. one or more browsers;
2. the required test groups;
3. screenshot option if required.

Then click:

```text
Run Selected
```

---

# Running in Different Browsers

Browser execution can also be configured using the `browser` system property.

Example:

```powershell
.\gradlew test -Dbrowser=chrome
```

Possible values:

```text
chrome
firefox
edge
```

---

# Test Execution Results

After execution, TestNG generates test results.

Results are stored under:

```text
build/test-output/
```

When tests are started through the launcher, reports can be separated by browser and execution time.

Example:

```text
build/test-output/chrome/
build/test-output/firefox/
build/test-output/edge/
```

---

# Launcher Logs

The desktop launcher creates execution logs.

Logs are stored in:

```text
build/launcher-logs/
```

Example:

```text
opencart-test-log-20260928-181436-006.txt
```

The log contains:

- browser information;
- started tests;
- passed tests;
- failed tests;
- skipped tests;
- error messages;
- final execution summary.

The launcher also allows the current log to be exported manually.

---

# Screenshots

Screenshots can be enabled directly from the Test Launcher.

When enabled, screenshots are created for failed tests.

Screenshots are stored in:

```text
build/screenshots/
```

They can be separated by browser.

Example:

```text
build/screenshots/chrome/
build/screenshots/firefox/
build/screenshots/edge/
```

Screenshots help identify the state of the application at the moment a test fails.

---

# Browser Driver Management

The project uses WebDriverManager.

Dependency:

```gradle
implementation("io.github.bonigarcia:webdrivermanager:6.3.4")
```

WebDriverManager automatically detects the installed browser and resolves the required WebDriver.

This means ChromeDriver, GeckoDriver or EdgeDriver do not need to be manually included in the project.

---

# Dependencies

The main project dependencies are defined in `build.gradle`.

```gradle
dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.40.0")
    implementation("org.testng:testng:7.11.0")
    implementation("io.github.bonigarcia:webdrivermanager:6.3.4")
    implementation("ch.qos.logback:logback-classic:1.6.3")
}
```

---

# Building the Executable JAR

The project can be packaged into a runnable JAR containing the launcher and required dependencies.

Build the JAR:

```powershell
.\gradlew launcherJar
```

Generated file:

```text
build/libs/OpenCartAutomation.jar
```

Run the JAR:

```powershell
java -jar build/libs/OpenCartAutomation.jar
```

The Test Launcher should start normally.

---

# Windows EXE Build

The project can also be packaged as a standalone Windows application.

The packaging process uses:

```text
jpackage
```

The JDK runtime is included in the packaged application.

This means Java does not need to be installed separately on the target computer.

---

## EXE Build Requirements

To create the Windows installer, the build computer requires:

- JDK 21 with `jpackage`;
- WiX Toolset;
- successfully built `OpenCartAutomation.jar`.

Check jpackage:

```powershell
jpackage --version
```

Example:

```text
21.0.12
```

---

## Build Windows Installer

Run:

```powershell
.\gradlew buildExe
```

The generated installer is placed in:

```text
build/installer/
```

Example:

```text
OpenCartAutomation-1.0.0.exe
```

---

# Running the Packaged Application

After installing the Windows package, the application can be launched without:

- IntelliJ IDEA;
- Gradle;
- separate Java installation;
- manual Selenium dependency installation.

The Java runtime and application dependencies are included in the package.

A supported web browser must still be installed on the computer.

---

# Supported Browsers

The project is designed for:

```text
Google Chrome
Mozilla Firefox
Microsoft Edge
```

The actual browser must be installed on the system where tests are executed.

WebDriverManager handles the corresponding WebDriver automatically.

---

# Test Data

Some OpenCart scenarios require test data such as:

- customer name;
- email address;
- password;
- telephone number;
- address;
- city;
- postcode;
- country;
- region.

Certain OpenCart operations create persistent data on the demo server.

For example, an email used for successful registration cannot normally be registered again.

For this reason, registration automation should use unique email data for repeated positive registration runs.

Authentication scenarios can continue using an existing predefined test account.

---

# Important Notes

The project uses a public OpenCart demo environment.

Because the application state is shared and can change, test execution may occasionally be affected by:

- existing user accounts;
- previously created test data;
- product availability;
- stock quantity;
- server response time;
- changes on the demo website.

Such behavior should be considered when analysing failed tests.

---

# Typical Workflow

Recommended execution workflow:

```text
1. Compile the project
2. Start the Test Launcher
3. Select a browser
4. Select required test groups
5. Enable screenshots if needed
6. Run tests
7. Review the execution summary
8. Check logs for failed tests
9. Check screenshots if a failure occurred
10. Review TestNG reports if additional details are required
```

---

# Build Commands Summary

Compile:

```powershell
.\gradlew clean testClasses
```

Run all tests:

```powershell
.\gradlew test
```

Start launcher:

```powershell
.\gradlew launcher
```

Build executable JAR:

```powershell
.\gradlew launcherJar
```

Run executable JAR:

```powershell
java -jar build/libs/OpenCartAutomation.jar
```

Build Windows installer:

```powershell
.\gradlew buildExe
```

---

# Project Purpose

This project demonstrates practical UI test automation for an e-commerce application using Java and Selenium.

It combines:

- functional UI automation;
- Page Object Model;
- automated browser configuration;
- TestNG test management;
- cross-browser execution;
- logging;
- screenshots;
- graphical test selection;
- standalone desktop packaging.

The result is an automation project that can be executed both from the development environment and through a standalone desktop launcher.
