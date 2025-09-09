# 🚀 JavaApplication2 - MySQL CRUD Operations

> 💡 A standalone Java application built in NetBeans IDE with MySQL database integration for performing real-world CRUD operations using JDBC.

---

## 📋 Table of Contents
- [🎯 Problem Statement](#-problem-statement)
- [✨ Key Features](#-key-features)
- [🛠️ Tech Stack](#️-tech-stack)
- [📂 Project Structure](#-project-structure)
- [⚙️ Getting Started](#️-getting-started)
- [💾 Database Setup](#-database-setup)
- [🔧 Configuration](#-configuration)
- [▶️ How to Run](#️-how-to-run)
- [🔮 Future Enhancements](#-future-enhancements)
- [👨‍💻 Author](#-author)

---

## 🎯 Problem Statement

The goal was to build a simple Java application that can:

✅ Connect securely to a MySQL database  
✅ Allow a user to store, retrieve, update, and delete records  
✅ Provide a basic interface for users to interact with the data through the console  

---

## ✨ Key Features

The application is implemented in Java using the NetBeans IDE and follows this approach:

### 🔗 Database Integration
Uses the JDBC (Java Database Connectivity) API to connect the Java application with a MySQL database.

### 📊 CRUD Operations
Supports all four fundamental database operations:

| Operation | Symbol | Description |
|-----------|--------|-------------|
| **Create** | ➕ | Add new records to the database |
| **Read** | 📖 | Retrieve existing records |
| **Update** | ✏️ | Modify existing records |
| **Delete** | 🗑️ | Remove records from the database |

### 🏗️ OOP Principles
Built using Object-Oriented principles like Encapsulation and modular methods for better code maintainability and readability.

### ♻️ Reusable Code
The database connection logic is separated into a utility/helper class (DBConnection.java) to be reused across the application.

---

## 🛠️ Tech Stack

| Component | Technology | Purpose |
|-----------|------------|---------|
| 💻 **Language** | Java | Core programming language |
| 🗄️ **Database** | MySQL | Data storage and management |
| 🛠️ **IDE** | Apache NetBeans | Development environment |
| 🔌 **API/Driver** | JDBC (MySQL Connector/J) | Database connectivity |
| 📝 **Version Control** | Git & GitHub | Source code management |

---

## 📂 Project Structure

The project follows a standard NetBeans Java application structure:

```
JavaApplication2/
│
├── 📁 nbproject/                    # NetBeans project configuration files
│
├── 📁 src/
│   └── 📁 javaapplication2/
│       ├── 🔗 DBConnection.java     # Handles the MySQL database connection
│       ├── 🏠 Main.java            # Main entry point of the application
│       ├── 📊 Model.java           # Represents the data model (POJO classes)
│       └── 🗄️ UserDAO.java         # Contains the CRUD functions (Data Access Object)
│
├── 📁 build/                       # Auto-generated compiled .class files
│
└── 📋 README.md                    # This documentation file
```

---

## ⚙️ Getting Started

Follow these steps to set up and run the project on your local machine.

### 🔧 Prerequisites

✅ **Java Development Kit (JDK) 8 or higher**  
✅ **Apache NetBeans IDE**  
✅ **MySQL Server installed and running**  
✅ **MySQL JDBC Connector (.jar file)**  

---

## 💾 Database Setup

### 📝 1. Create Database and Table

First, create the database and the necessary table in MySQL:

```sql
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
```

---

## 🔧 Configuration

### 📝 2. Update Connection Details

Open the `DBConnection.java` file and update the MySQL credentials to match your local setup:

```java
// src/javaapplication2/DBConnection.java

// ...
String url = "jdbc:mysql://localhost:3306/java_app";
String user = "root";
String password = "your_mysql_password"; // <-- CHANGE THIS
// ...
```

---

## ▶️ How to Run

### 📥 3. Clone and Setup

**Step 1:** Clone the repository
```bash
git clone https://github.com/Ajithkumarreddy123/JavaApplication2.git
```

**Step 2:** Open the project in your NetBeans IDE

**Step 3:** Add the MySQL JDBC Connector
- ➡️ Right-click on the "Libraries" folder in the project tree
- ➡️ Select "Add JAR/Folder..."
- ➡️ Navigate to and select the `mysql-connector-java-x.x.x.jar` file you downloaded

**Step 4:** Build the project
- ➡️ Right-click project > "Clean and Build"

**Step 5:** Run the application
- ➡️ Right-click `Main.java` > "Run File"

---

## 🔮 Future Enhancements

| Enhancement | Status | Description |
|-------------|--------|-------------|
| 🖥️ **Add a GUI** | 🔄 Planned | Implement a user-friendly graphical interface using Java Swing or JavaFX |
| 🔐 **Secure Credentials** | 🔄 Planned | Use environment variables or configuration file for database credentials |
| 🔍 **Advanced Queries** | 🔄 Planned | Extend functionalities with complex SQL queries involving JOINs, indexing, and transactions |

---

## 👨‍💻 Author

**🏷️ Name:** Bhumireddy Ajith Kumar Reddy  
**📍 Location:** Vijayawada, India  
**📧 Email:** ajithbhumireddy30@gmail.com  

---

### 📊 Project Stats

- 🏗️ **Architecture:** Object-Oriented Programming (OOP)
- 🔧 **Pattern:** Data Access Object (DAO) Pattern
- 💾 **Database:** Relational Database Management
- 🔄 **Operations:** Full CRUD Implementation
- 📱 **Interface:** Console-based User Interaction

---

> 💡 **Note:** This project is perfect for beginners learning Java database connectivity and CRUD operations. Feel free to fork, modify, and enhance according to your needs!

**⭐ If you find this project helpful, please give it a star!**
