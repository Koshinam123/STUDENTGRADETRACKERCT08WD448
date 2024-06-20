import java.util.ArrayList;
import java.util.HashMap;

public class GradeTracker {
    private ArrayList<Student> students;

    public GradeTracker() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
}
