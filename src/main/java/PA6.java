//Programmer Names: Ava Crites, Trevor Hudson, Samuel Ng
//Date Completed: 11/16/23
//PA Purpose: To develop a university's student management system using objects and classes formed from a UML diagram.
/*My submission of this program indicates that I have neither received nor
given substantial or unauthorized assistance in writing this program.*/

//Importing java libraries to utilize ArrayLists and Scanner
import java.util.ArrayList;
import java.util.Scanner;

public class PA6 
{
    //Initialization of each ArrayList
    public static ArrayList<Student> studentArray = new ArrayList<>();
    public static ArrayList<Course> courseArray = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) 
    {
      
        String menuSelection;
        //Ensures the menu is redisplayed 
        do {
            System.out.println("\nStudent Management System");
            System.out.println("Please make a menu choice below:");
            System.out.println("--------------------------------- ");
            System.out.println("1: Create a course");
            System.out.println("2: Add student to Course");
            System.out.println("3: Remove student from a Course");
            System.out.println("4: Add Instructor to a Course");
            System.out.println("5: Remove instructor from a Course");
            System.out.println("6: Print Roster for a Course");
            System.out.println("7: Quit");
            System.out.print("Choice: ");
            menuSelection = input.nextLine();

            switch (menuSelection) 
            {
                case "1":
                    createCourse();
                    break;
                case "2":
                    addStudent(courseArray, instructorArray);
                    break;
                case "3":
                    removeStudent();
                    break;
                case "4":
                    addInstructor();
                    break;
                case "5":
                    removeInstructor();
                    break;
                case "6":
                    printRoster();
                    break;
                case "7":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid menu option. Please try again.");
            }

        } while (!menuSelection.equals("7"));
        
        input.close();
    }

    //Creates a course object using user input
    public static void createCourse() 
    {
        System.out.print("Creating Class... ");
        System.out.print("\nEnter Course Name: ");
        String courseName = input.nextLine();

        System.out.print("Enter Building: ");
        String courseBuilding = input.nextLine();

        System.out.print("Enter Room Number: ");
        String courseRoom = input.nextLine();

        System.out.print("Enter Room Capacity: ");
        int courseCapacity;
        //Try-catch allows us to simplify our input validation and limit lines of code. Sourced by ChatGPT
        try {
            courseCapacity = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for course capacity. Please enter a number.");
            return;
        }

        Course newCourse = new Course(courseName, courseBuilding, courseRoom, courseCapacity);
        courseArray.add(newCourse);
}

  
    //Deletes a student object based on user's selection from roster
    public static void removeStudent() 
    {
        System.out.println("Please choose a class: ");
        //Prints the list of courses in the system
        for (int i = 0; i < courseArray.size(); i++) 
        {
        Course course = courseArray.get(i);
        System.out.println("Course #" + i + " Course: " + course.getCourseName() +
                " Location: " + course.getCourseBuilding() + " Room: " + course.getCourseBldgRoom() +
                " Capacity: " + course.getCourseCapacity() + " Instructor of Record: " + (course.getCourseInstructor() != null ? course.getCourseInstructor().getTitle() : "None"));
        }
       
        int courseChoice;
        while (true) 
        {
            System.out.print("Choose Class #: ");
            if (input.hasNextInt()) 
            {
                courseChoice = input.nextInt();
         
                if (courseChoice >= 0 && courseChoice < courseArray.size()) 
                {
                    break;
                } 
                else 
                {
                    System.out.println("Invalid class number. ");
                } 
            } 
            else 
            {
                System.out.println("Invalid class number. ");
             
            }
        }
        
        Course selectedCourse = courseArray.get(courseChoice);
        System.out.println(selectedCourse.getRoster());
        
        int studentID;
        while (true) 
        {
            System.out.print("Type ID of Student to Remove: ");
            if (input.hasNextInt()) 
            {
                studentID = input.nextInt();
                input.nextLine();
                boolean studentRemove = selectedCourse.removeStudent(studentID);
                if (studentRemove) 
                {
                    System.out.println("Student Successfully Removed! ");
                } 
                else 
                {
                    System.out.println("Student ID does not exist.");
                }
                break;
            } 
            else 
            {
                System.out.println("Invalid input. Please enter a valid Student ID. ");
                input.nextLine();
            }
        }  
       
    }
    
    //Creates an Instructor object based on user input
    public static void addInstructor() 
    {
        //User input
        System.out.println("\nEnter Instructor Information");
        System.out.print("Name: ");
        String name = input.nextLine(); 

        System.out.print("Prefix: ");
        String prefix = input.nextLine();

        System.out.print("Office: ");
        String office = input.nextLine();

        System.out.print("Department: ");
        String department = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();
        
        //Creation of the new Instructor object
        Instructor newInstructor = new Instructor(name, prefix, office, department, email);
        //Adds the new instructor to the ArrayList of instructors
        instructorArray.add(newInstructor);
        
        System.out.print("Choose a Course for this Instructor: ");
        //Displays all available courses that an instructor may be added to
        for (int i = 0; i < courseArray.size(); i++) 
        {
            Course currentCourse = courseArray.get(i);
            String instructorIf = "None";
            if (currentCourse.getCourseInstructor() != null) 
            {
                instructorIf = currentCourse.getCourseInstructor().getTitle();
            }
            System.out.println("\nCourse #" + i + " Course: " + currentCourse.getCourseName() +
                    " Location: " + currentCourse.getCourseBuilding() + " Room: " + currentCourse.getCourseBldgRoom() +
                    " Capacity: " + currentCourse.getCourseCapacity() + " Instructor of Record: " + instructorIf);
        }
       
        Scanner scanner = new Scanner(System.in);
        System.out.print("-------------------");
        System.out.print("\nChoose Class #: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine();
        //Ensures the user inputs a valid course ID
        if (courseChoice >= 0 && courseChoice <= courseArray.size()) 
        {
            Course selectedCourse = courseArray.get(courseChoice);
            selectedCourse.enrollInstructor(newInstructor);
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }

    }


    //Generates a list of students that have been added to a course specified by the user
    public static void printRoster() 
    {
        //Prints all courses that have been added
        System.out.println("Please choose a class: ");
        for (int i = 0; i < courseArray.size(); i++) 
        {
            Course course = courseArray.get(i);
            System.out.println("Course #" + i + " Course: " + course.getCourseName() + " Location: " + course.getCourseBuilding() + " Room: " + course.getCourseBldgRoom() + " Capacity: " + course.getCourseCapacity() + " Instructor of Record: " + (course.getCourseInstructor() != null ? course.getCourseInstructor().getTitle() : "None"));
        }
        
        System.out.print("Choose Class #: ");
        int courseIndex;
        //Try-catch allows us to simplify our input validation and limit lines of code. Sourced by ChatGPT
        try 
        {
            courseIndex = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) 
        {
            System.out.println("Invalid input. Please enter a valid course number.");
            return;
        }
        //Checks if the course array is empty and provides an error message if true
        if (courseArray.isEmpty()) 
        {
            System.out.println("No courses available to print a roster.");
            return;
        }
        
        if (courseIndex < 0 || courseIndex >= courseArray.size()) 
        {
            System.out.println("Invalid course number.");
            return;
        }

        Course selectedCourse = courseArray.get(courseIndex);
        System.out.println("-------------------");
        String roster = selectedCourse.getRoster();
        System.out.println(roster);

    }
 
    //Creates a new student object 
    public static void addStudent(ArrayList<Course> courseArray, ArrayList<Instructor> instructorArray) 
    {
        Scanner scanner = new Scanner(System.in);

        // Doesn't allow the user to create a student if a course doesn't exist
        if (courseArray.isEmpty()) 
        {
            System.out.println("Must add a course before adding a student.");
            return;
        }

        // User input
        System.out.print("Enter Student Information: ");
        System.out.print("Name (firstname lastname): ");
        String fullName = scanner.nextLine();

        System.out.print("Major: ");
        String major = scanner.nextLine();

        int yearInput;
        // Year input validation
        do 
        {
            System.out.print("Year: ");
            while (!scanner.hasNextInt()) 
            {
                System.out.println("Invalid input. Please enter a valid year (1-4).");
                scanner.next(); 
            }
            yearInput = scanner.nextInt();
            scanner.nextLine(); 
            if (yearInput < 1 || yearInput > 4) 
            {
                System.out.println("Invalid year. Please enter a year between 1 and 4.");
            }
        } 
        while (yearInput < 1 || yearInput > 4);

        double GPA;
        // GPA input validation
        do {
            System.out.print("GPA: ");
            while (!scanner.hasNextDouble()) 
            {
                System.out.println("Invalid input. Please enter a valid GPA.");
                scanner.next(); // Consume the invalid input
            }
            GPA = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            if (GPA < 0.0 || GPA > 5.0) 
            {
                System.out.println("Invalid GPA. Please enter a GPA between 0.0 and 5.0.");
            }
        } 
        while (GPA < 0.0 || GPA > 5.0);

        String email;
        // Email input validation
        do 
        {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!email.contains("@")) 
            {
                System.out.println("Invalid email. Please include the '@' symbol in your email.");
            }
        } while (!email.contains("@"));

        Student newStudent = new Student(fullName, Integer.toString(yearInput), major, GPA, email);
        PA6.studentArray.add(newStudent);

        System.out.println("Please choose a Class:");
        System.out.println();
        //Prints every course available to add a student to
        for (int i = 0; i < courseArray.size(); i++) 
        {
            Course currentCourse = courseArray.get(i);
            String instructorIf = "None";
            if (currentCourse.getCourseInstructor() != null) 
            {
                instructorIf = currentCourse.getCourseInstructor().getTitle();
            }
            System.out.println("Course #" + i + " Course: " + currentCourse.getCourseName() +
                    " Location: " + currentCourse.getCourseBuilding() + " Room: " + currentCourse.getCourseBldgRoom() +
                    " Capacity: " + currentCourse.getCourseCapacity() + " Instructor of Record: " + instructorIf);
        }

        System.out.println("------------------");
        System.out.print("Choose Class #: ");
        System.out.println();
        int courseChoice = scanner.nextInt();
        scanner.nextLine();

        if (courseChoice >= 0 && courseChoice < courseArray.size()) 
        {
            Course selectedCourse = courseArray.get(courseChoice);
            selectedCourse.enrollStudent(newStudent);
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }
}

    //Removes an instructor object from the course
    public static void removeInstructor() 
    {
        if (courseArray.isEmpty()) 
        {
            System.out.println("No courses available. Cannot remove an instructor.");
            return;
        }

        boolean hasInstructors = false;

        System.out.println("Please choose a class: ");
        //Displays each course in the system
        for (int i = 0; i < courseArray.size(); i++) 
        {
            Course course = courseArray.get(i);
            System.out.println("Course #" + i + " Course: " + course.getCourseName() +
                    " Location: " + course.getCourseBuilding() + " Room: " + course.getCourseBldgRoom() +
                    " Capacity: " + course.getCourseCapacity() +
                    " Instructor of Record: " + (course.getCourseInstructor() != null ? course.getCourseInstructor().getTitle() : "None"));

            if (course.getCourseInstructor() != null) 
            {
                hasInstructors = true;
            }
        }

        if (!hasInstructors) 
        {
            System.out.println("No instructors in any course. Can't remove an instructor.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose Course #: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine();

        if (courseChoice >= 0 && courseChoice < courseArray.size()) 
        {
            Course selectedCourse = courseArray.get(courseChoice);

            if (selectedCourse.getCourseInstructor() != null) 
            {
                System.out.println("Removing instructor from " + selectedCourse.getCourseName());
                selectedCourse.removeInstructor();
            } 
            else 
            {
                System.out.println("No instructor in the selected course. Cannot remove an instructor.");
            }
        } 
        else 
        {
            System.out.println("Invalid course number.");
        }
    }

}
