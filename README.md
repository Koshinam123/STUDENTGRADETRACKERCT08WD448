# STUDENTGRADETRACKERCT08WD448  Koshinam Akhila ID:CT08WD448
The Student Grade Tracker is a Java-based application designed to help users manage and track student grades. The application consists of three primary components:

    Student.java - A class representing a student.
    GradeTracker.java - A class to manage the collection of students and their grades.
    GradeTrackerGUI.java - A graphical user interface for interacting with the Grade Tracker.

Table of Contents

    Getting Started
    Prerequisites
    Installation
    Usage
    Class Descriptions
    Contributing
    License

Getting Started

To get started with the Student Grade Tracker, follow the instructions below to set up and run the application on your local machine.
Prerequisites

    Java Development Kit (JDK) 8 or higher
    A Java Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or NetBeans

Installation

    Clone the repository:

    sh

git clone https://github.com/your-username/student-grade-tracker.git

Navigate to the project directory:

sh

    cd student-grade-tracker

    Open the project in your preferred IDE.

Usage

    Compile the Java files:

    In your IDE, compile the three Java files: Student.java, GradeTracker.java, and GradeTrackerGUI.java.

    Run the application:

    Execute the GradeTrackerGUI class to start the graphical user interface.

Class Descriptions
Student.java

The Student class represents a student with the following attributes:

    name (String): The name of the student.
    grades (List<Double>): A list of grades for the student.

Methods:

    Student(String name): Constructor to create a new student with a given name.
    void addGrade(double grade): Adds a grade to the student's grade list.
    double getAverageGrade(): Calculates and returns the average grade of the student.
    String getName(): Returns the name of the student.

GradeTracker.java

The GradeTracker class manages a collection of students.

Attributes:

    students (List<Student>): A list of students.

Methods:

    void addStudent(Student student): Adds a student to the grade tracker.
    Student getStudent(String name): Retrieves a student by name.
    List<Student> getAllStudents(): Returns a list of all students.
    double getClassAverage(): Calculates and returns the average grade of the entire class.

GradeTrackerGUI.java

The GradeTrackerGUI class provides a graphical user interface for the Grade Tracker application.

Features:

    Add a new student.
    Add grades for existing students.
    View the average grade of individual students.
    View the class average.

Methods:

    GradeTrackerGUI(): Constructor to set up the GUI components.
    void addStudentAction(): Handles the action of adding a new student.
    void addGradeAction(): Handles the action of adding a grade to a student.
    void viewStudentAverageAction(): Displays the average grade of a selected student.
    void viewClassAverageAction(): Displays the average grade of the entire class.

Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. For major changes, please open an issue first to discuss what you would like to change.

    Fork the project
    Create your feature branch (git checkout -b feature/AmazingFeature)
    Commit your changes (git commit -m 'Add some AmazingFeature')
    Push to the branch (git push origin feature/AmazingFeature
