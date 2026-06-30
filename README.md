# Password Wizard

Author: Kartik Jain
Version: 4.0

A password generator program that asks the user various questions and generates a password using this information. I/O is through pop-up windows (GUI).

The password generated in each execution is different, ensuring that someone else will not immediately be able to find out your password just by entering the same details as you.

## Project Structure

```
PasswordWizard/
├── src/
│   └── javaPasswordv4.java      # Source code for the program
├── PasswordWizard.jar      # The program itself, zipped as an executable
└── README.md      # This file
```

## Setup Instructions

- To run the JAR file, end user must have a Java Runtime Environment (JRE) installed as a minimum requirement. You can install the latest JRE from https://www.java.com/en/download/manual.jsp
- To edit, compile and execute the source code (.java file), you must have Java Development Kit (JDK) installed and configured in your system path.
- If you don't have a JDK installed, you can install the latest version from Oracle's website.

### How to run the JAR application

Either double click the application in File Explorer/Finder, or run the following command in the terminal at the location at which it is stored:
```bash
java -jar PasswordWizard.jar
```

### How to compile and run the source code

```bash
cd src
javac javaPasswordv4.java
java javaPasswordv4
```

## Dependencies

- Uses Java Swing, which is part of the standard JDK/JRE.
- No 3rd party libraries required.
