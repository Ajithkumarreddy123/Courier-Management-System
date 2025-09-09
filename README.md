#☕ JavaApplication2 - MySQL CRUD Operations

This project demonstrates a Java standalone application built in NetBeans IDE with integration to a MySQL Database.
It performs CRUD operations (Create, Read, Update, Delete) using JDBC and follows OOP principles for clean, modular design.

#🚀 Features

🔌 Connects Java with MySQL using JDBC

✍️ Create new records

📖 Read existing records

✏️ Update records

❌ Delete records

🛠️ Clean OOP design (Encapsulation + DAO pattern)

#🧱 Architecture

[Java Application] ---> [JDBC Driver] ---> [MySQL Database]

#🛠️ Tech Stack

Language: Java

Database: MySQL

IDE: Apache NetBeans

Driver: JDBC (MySQL Connector/J)

Version Control: Git & GitHub

#📂 Project Structure
JavaApplication2/
│── nbproject/                # NetBeans project configuration
│── src/
│   └── javaapplication2/
│       ├── DBConnection.java # Handles MySQL database connection
│       ├── Main.java         # Entry point of the application
│       ├── Model.java        # Data model (POJO classes)
│       └── UserDAO.java      # CRUD functions (DAO pattern)
│── build/                    # Auto-generated .class files
└── README.md                 # Documentation

#⚙️ Setup Instructions
✅ Prerequisites

Java JDK 8 or higher

Apache NetBeans IDE

MySQL Server running

MySQL JDBC Connector (mysql-connector-java-x.x.x.jar)

#🗄️ Database Setup
CREATE DATABASE java_app;
USE java_app;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


Update your MySQL credentials in DBConnection.java:

String url = "jdbc:mysql://localhost:3306/java_app";
String user = "root";
String password = "your_mysql_password"; // <-- change this

▶️ How to Run

#Clone the repository:

git clone https://github.com/Ajithkumarreddy123/JavaApplication2.git


Open the project in NetBeans IDE

Add the MySQL JDBC Connector:

Right-click Libraries → Add JAR/Folder... → Select mysql-connector-java-x.x.x.jar

Clean and Build the project

Run the application (Right-click Main.java → Run File)

#🚀 Future Enhancements

🖥️ Add GUI using Java Swing/JavaFX

🔐 Secure DB credentials with environment variables/config file

📊 Add advanced SQL queries (JOINs, indexing, transactions)

#👤 Author

Bhumireddy Ajith Kumar Reddy
📍 Vijayawada, India
📧 ajithbhumireddy30@gmail.com

🔗 GitHub
 | LinkedIn
