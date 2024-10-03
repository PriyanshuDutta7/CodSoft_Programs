import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    private ArrayList<Student> students;
    private final String fileName = "students.txt";

    // Constructor to initialize students list and load data from file
    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
        saveToFile();
    }

    // Method to remove a student by roll number
    public void removeStudent(int rollNumber) {
        Student studentToRemove = searchStudent(rollNumber);
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed successfully.");
            saveToFile();
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // Method to search for a student by roll number
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to save student data to a file
    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }

    // Method to load student data from a file
    private void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing student data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file.");
        }
    }

    // Method to edit a student's information
    public void editStudent(int rollNumber, String newName, String newGrade) {
        Student student = searchStudent(rollNumber);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            System.out.println("Student details updated successfully.");
            saveToFile();
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    // Method to start the user interface
    public void startUI() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    addStudent(new Student(name, rollNumber, grade));
                    break;
                case 2:
                    // Remove Student
                    System.out.print("Enter roll number of the student to remove: ");
                    int rollToRemove = scanner.nextInt();
                    removeStudent(rollToRemove);
                    break;
                case 3:
                    // Search Student
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    Student student = searchStudent(rollToSearch);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student with Roll Number " + rollToSearch + " not found.");
                    }
                    break;
                case 4:
                    // Display All Students
                    displayAllStudents();
                    break;
                case 5:
                    // Edit Student
                    System.out.print("Enter roll number to edit: ");
                    int rollToEdit = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine();
                    editStudent(rollToEdit, newName, newGrade);
                    break;
                case 6:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the Student Management System.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.startUI();
    }
}
