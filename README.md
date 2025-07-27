  📧 Gmail Account Creation Using Java + Selenium + TestNG

This project automates the **Gmail account creation process** using **Selenium WebDriver**, **TestNG**, and **Excel-based Data-Driven Framework**. It reads multiple sets of user registration data from an Excel file and automates form submission for each set using Java and Apache POI.

## 📌 Project Highlights

- Automated form filling for Gmail signup
- TestNG framework with `@DataProvider` for data-driven testing
- Reads input data from Excel (`.xlsx`) using Apache POI
- Automatic browser driver setup using WebDriverManager
- Maven-based Java project for easy build & dependency management

## 🔧 Technologies Used

| Technology        | Purpose                                |
|------------------|----------------------------------------|
| Java              | Programming language                   |
| Selenium WebDriver| Web browser automation                 |
| TestNG            | Testing framework & test management    |
| Apache POI        | Read Excel files for test data         |
| Maven             | Project build & dependency management  |
| WebDriverManager  | Automatically manage browser drivers   |

## 📁 Project Structure

GmailAccountCreation/
├── src/
│ ├── main/java/
│ │ └── utils/
│ │ └── ExcelUtils.java # Reads Excel data
│ ├── test/java/
│ │ └── testcases/
│ │ └── GmailAccountTest.java # Main test class
├── testdata/
│ └── GmailTestData.xlsx # Excel test data file
├── pom.xml # Maven dependencies
├── .gitignore # Git ignore rules
├── README.md # Project documentation

## 🧪 Framework Overview (Data-Driven Testing)

This project follows a **Data-Driven Framework**, where the test inputs (First Name, Last Name, Username, Password) are read from an Excel file and passed to the test method using TestNG `@DataProvider`.

✔ Each row = One test execution  
✔ Useful for testing multiple scenarios (positive & negative)

## 📄 Sample Excel Data (`GmailTestData.xlsx`)

| First Name | Last Name | Username     | Password     | confirm
|------------|-----------|--------------|--------------|
| Karthik    | Bandi     | karthik.b18  | Karthik@123  |karthik@123
| Arjun      | Dev       | arjun.dev01  | Arjun@456    |Arjun@456

---

## 🚀 How to Run

1. **Clone the Repository**
```bash
git clone https://github.com/yourusername/gmail-account-creation-java-selenium-testng.git
Open the Project in Eclipse or IntelliJ

Add Excel File
Place GmailTestData.xlsx inside the testdata/ folder.

Run the Test
Run the test file:
src/test/java/testcases/GmailAccountTest.java
Check the Console Output or Reports

