JavaApplication2 - MySQL CRUD Operations
A standalone Java application built in the NetBeans IDE with integration to a MySQL Database. This project demonstrates Java fundamentals (OOP, collections, methods) and database connectivity (JDBC) for performing real-world CRUD (Create, Read, Update, Delete) operations.

📌 Problem Statement
The goal was to build a simple Java application that can:

Connect securely to a MySQL database.

Allow a user to store, retrieve, update, and delete records.

Provide a basic interface for users to interact with the data through the console.

✨ Key Features
The application is implemented in Java using the NetBeans IDE and follows this approach:

Database Integration: Uses the JDBC (Java Database Connectivity) API to connect the Java application with a MySQL database.

CRUD Operations: Supports all four fundamental database operations:

Create: Add new records to the database.

Read: Retrieve existing records.

Update: Modify existing records.

Delete: Remove records from the database.

OOP Principles: Built using Object-Oriented principles like Encapsulation and modular methods for better code maintainability and readability.

Reusable Code: The database connection logic is separated into a utility/helper class (DBConnection.java) to be reused across the application.

🛠️ Tech Stack
Language: Java

Database: MySQL

IDE: Apache NetBeans

API/Driver: JDBC (MySQL Connector/J)

Version Control: Git & GitHub

📂 Project Structure
The project follows a standard NetBeans Java application structure:

JavaApplication2/
|-- nbproject/      # NetBeans project configuration files
|-- src/
|   `-- javaapplication2/
|       |-- DBConnection.java # Handles the MySQL database connection
|       |-- Main.java         # Main entry point of the application
|       |-- Model.java        # Represents the data model (POJO classes)
|       `-- UserDAO.java      # Contains the CRUD functions (Data Access Object)
|-- build/            # Auto-generated compiled .class files
`-- README.md         # This documentation file

⚙️ Getting Started
Follow these steps to set up and run the project on your local machine.

Prerequisites
Java Development Kit (JDK) 8 or higher.

Apache NetBeans IDE.

MySQL Server installed and running.

MySQL JDBC Connector (.jar file).

1. Database Setup
First, create the database and the necessary table in MySQL.

-- Create a new database named 'java_app'
CREATE DATABASE java_app;

-- Switch to the new database
USE java_app;

-- Create a 'users' table to store user data
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

2. Update Connection Details
Open the DBConnection.java file and update the MySQL credentials to match your local setup:

// src/javaapplication2/DBConnection.java

// ...
String url = "jdbc:mysql://localhost:3306/java_app";
String user = "root";
String password = "your_mysql_password"; // <-- CHANGE THIS
// ...

3. How to Run
Clone the repository:

git clone [https://github.com/Ajithkumarreddy123/JavaApplication2.git](https://github.com/Ajithkumarreddy123/JavaApplication2.git)

Open the project in your NetBeans IDE.

Add the MySQL JDBC Connector:

Right-click on the "Libraries" folder in the project tree.

Select "Add JAR/Folder...".

Navigate to and select the mysql-connector-java-x.x.x.jar file you downloaded.

Clean and Build the project (Right-click project > "Clean and Build").

Run the application (Right-click Main.java > "Run File").

🚀 Future Enhancements
Add a GUI: Implement a more user-friendly graphical interface using Java Swing or JavaFX.

Secure Credentials: Use environment variables or a configuration file to store database credentials instead of hardcoding them.

Advanced Queries: Extend functionalities with more complex SQL queries involving JOINs, indexing, and transactions.

👤 Author
Bhumireddy Ajith Kumar Reddy

Location: Vijayawada, India

Email: ajithbhumireddy30@gmail.com
