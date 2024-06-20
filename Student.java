import java.util.HashMap;
import java.util.*;

public class Student {
    private String name;
    private HashMap<String, Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int grade) {
        grades.put(subject, grade);
    }

    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    public double calculateAverageGradeValue() {
        int totalGradeValue = 0;
        for (int grade : grades.values()) {
            totalGradeValue += getGradeValue(grade);
        }
        return totalGradeValue / (double) grades.size();
    }

    public boolean hasFailingGrade() {
        for (int grade : grades.values()) {
            if (grade < 50) {
                return true;
            }
        }
        return false;
    }

    public static int getGradeValue(int grade) {
        if (grade >= 91) return 10;
        else if (grade >= 81) return 9;
        else if (grade >= 71) return 8;
        else if (grade >= 61) return 7;
        else if (grade >= 50) return 6;
        else return 5;
    }

    public static String getGradeDescription(double averageGradeValue) {
        if (averageGradeValue >= 9.1) return "Excellent";
        else if (averageGradeValue >= 8.1) return "Very Good";
        else if (averageGradeValue >= 7.1) return "Good";
        else if (averageGradeValue >= 6.1) return "Normal";
        else if (averageGradeValue >= 5.1) return "Pass";
        else return "Fail";
    }

    public String getName() {
        return name;
    }
}
