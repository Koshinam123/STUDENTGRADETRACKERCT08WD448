import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GradeTrackerGUI extends JFrame {
    private Map<String, Map<String, Double>> students;
    private JTextField nameField;
    private JTextField[] gradeFields;
    private JTextArea outputArea;

    private static final String[] subjects = {
            "JAVA", "Operating Systems", "Data Structures and Algorithms",
            "Design and Analysis of Algorithms", "C Language", "IT Workshop",
            "Database Management Systems", "Machine Learning and Deep Learning",
            "Artificial Intelligence", "Computer Organization and Architecture",
            "Discrete Mathematics", "Python"
    };

    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font DETAILS_FONT = new Font("Arial", Font.PLAIN, 16);

    public GradeTrackerGUI() {
        students = new HashMap<>();

        setTitle("Student Grade Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(subjects.length + 3, 2));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        gradeFields = new JTextField[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            inputPanel.add(new JLabel(subjects[i] + ":"));
            gradeFields[i] = new JTextField();
            inputPanel.add(gradeFields[i]);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());
        inputPanel.add(resetButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the student's name.");
                return;
            }

            Map<String, Double> grades = new HashMap<>();
            for (int i = 0; i < subjects.length; i++) {
                String gradeText = gradeFields[i].getText().trim();
                if (gradeText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all grades.");
                    return;
                }
                try {
                    double gradeValue = Double.parseDouble(gradeText);
                    if (gradeValue < 0 || gradeValue > 100) {
                        throw new NumberFormatException();
                    }
                    grades.put(subjects[i], gradeValue);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid grade value for " + subjects[i] + " (0-100).");
                    return;
                }
            }

            students.put(name, grades);

            if (hasFailingGrade(grades)) {
                StringBuilder failedSubjects = new StringBuilder();
                for (Map.Entry<String, Double> entry : grades.entrySet()) {
                    if (entry.getValue() < 50) {
                        failedSubjects.append(entry.getKey()).append(", ");
                    }
                }
                if (failedSubjects.length() > 0) {
                    failedSubjects.setLength(failedSubjects.length() - 2);  // Remove the last comma and space
                }
                outputArea.setText(name + " has failed in the following subjects: " + failedSubjects.toString());
            } else {
                double averageGradeValue = calculateAverageGradeValue(grades);
                String gradeDescription = getGradeDescription(averageGradeValue);
                showGradeDetails(name, grades, averageGradeValue, gradeDescription);
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            for (JTextField gradeField : gradeFields) {
                gradeField.setText("");
            }
            outputArea.setText("");
        }
    }

    private void showGradeDetails(String name, Map<String, Double> grades, double averageGradeValue, String gradeDescription) {
        JFrame detailsFrame = new JFrame("Grade Details for " + name);
        detailsFrame.setSize(600, 500);
        detailsFrame.setLayout(new BorderLayout());
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setResizable(true);
        detailsFrame.getContentPane().setBackground(new Color(255, 228, 196));  // Bisque background

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Table for displaying subjects, marks, and grade values
        String[] columnNames = {"Subject Name", "Marks", "Grade Value"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        DecimalFormat df = new DecimalFormat("#.##");

        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            String subject = entry.getKey();
            double marks = entry.getValue();
            double gradeValue = getGradeValue(marks);
            String[] rowData = {subject, df.format(marks), df.format(gradeValue)};
            tableModel.addRow(rowData);
        }

        JTable gradeTable = new JTable(tableModel);
        gradeTable.setFont(LABEL_FONT);
        gradeTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(gradeTable);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        // Labels for average grade value and overall grade
        JPanel labelPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        labelPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JLabel avgGradeValueLabel = new JLabel("Average Grade Value:");
        avgGradeValueLabel.setFont(LABEL_FONT);
        labelPanel.add(avgGradeValueLabel);

        JLabel avgGradeValueValueLabel = new JLabel(df.format(averageGradeValue));
        avgGradeValueValueLabel.setFont(LABEL_FONT);
        labelPanel.add(avgGradeValueValueLabel);

        JLabel overallGradeLabel = new JLabel("Overall Grade:");
        overallGradeLabel.setFont(LABEL_FONT);
        labelPanel.add(overallGradeLabel);

        JLabel overallGradeValueLabel = new JLabel(gradeDescription);
        overallGradeValueLabel.setFont(LABEL_FONT);
        labelPanel.add(overallGradeValueLabel);

        detailsPanel.add(labelPanel, BorderLayout.SOUTH);

        detailsFrame.add(detailsPanel);
        detailsFrame.setVisible(true);
    }

    private double getGradeValue(double marks) {
        if (marks >= 90) {
            return 10.0;
        } else if (marks >= 80) {
            return 9.0;
        } else if (marks >= 70) {
            return 8.0;
        } else if (marks >= 60) {
            return 7.0;
        } else if (marks >= 50) {
            return 6.0;
        } else {
            return 5.0;
        }
    }

    private String getGradeDescription(double averageGradeValue) {
        if (averageGradeValue >= 9.1) {
            return "Excellent - EX";
        } else if (averageGradeValue >= 8.1) {
            return "Very Good - A";
        } else if (averageGradeValue >= 7.1) {
            return "Good - B";
        } else if (averageGradeValue >= 6.1) {
            return "Average - C";
        } else if (averageGradeValue >= 5.1) {
            return "Pass - D";
        } else {
            return "Fail - F";
        }
    }

    private double calculateAverageGradeValue(Map<String, Double> grades) {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double grade : grades.values()) {
            sum += getGradeValue(grade); // Use getGradeValue to calculate average based on grade values
        }
        return sum / grades.size();
    }

    private boolean hasFailingGrade(Map<String, Double> grades) {
        for (double grade : grades.values()) {
            if (grade < 50) {  // Grade value below 50 is considered failing
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradeTrackerGUI gui = new GradeTrackerGUI();
            gui.getContentPane().setBackground(new Color(173, 216, 230));  // Light blue background
            gui.setVisible(true);
        });
    }
}
