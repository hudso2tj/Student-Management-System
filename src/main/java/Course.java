//Programmer Names: Ava Crites, Trevor Hudson, Samuel Ng
//Date Completed: 11/16/23
//PA Purpose: To dvelop a university's student management system using objects and classes formed from a UML diagram.
/*My submission of this program indicates that I have neither received nor
given substantial or unauthorized assistance in writing this program.*/

import java.util.ArrayList;

public class Course 
{
    //Class properties
    private String courseName, courseBuilding, courseBldgRoom;
    private int courseCapacity, courseID;
    private ArrayList<Student> enrolledStudents;
    private Instructor courseInstructor;
    private static int nextCourseID = 1; // Assuming the ID starts at 1
    //Constructor
    public Course(String name, String building, String room, int capacity) 
    {
        this.courseName = name;
        this.courseBuilding = building;
        this.courseBldgRoom = room;
        this.courseCapacity = capacity;
        this.courseID = nextCourseID++;
        this.enrolledStudents = new ArrayList<>();
    }
    //Ensures the course hasn't hit capacity 
    public void enrollStudent(Student newStudent) 
    {
        if (enrolledStudents.size() < courseCapacity) 
        {
            enrolledStudents.add(newStudent);
        } 
        else 
        {
            // Handle the case when the course is full
            System.out.println("Course is full. Cannot enroll student.");
        }
    }
    
    public void enrollInstructor(Instructor instructor) 
    {
        this.courseInstructor = instructor;
    }
    //Matches the user student ID input to the object's student ID and deletes it
    public boolean removeStudent(int studentID) 
    {
        for (Student student : enrolledStudents) 
        {
            if (student.getStudentID() == studentID) 
            {
                enrolledStudents.remove(student);
                return true;
            }
        }
        return false;
    }
    
    public Instructor getCourseInstructor()
    {
            return courseInstructor;
    }
       
    public void assignInstructor(Instructor newInstr) 
    {
        this.courseInstructor = newInstr;
    }
    
    public String getCourseBldgRoom()
    {
            return courseBldgRoom;
    }
    
    public void setCourseBldgRoom(String room) 
    {
        this.courseBldgRoom = room;
    }
    
     public String getCourseBuilding()
    {
            return courseBuilding;
    }

    public void setCourseBuilding(String building) 
    {
        this.courseBuilding = building;
    }
    
    public void setCapacity(int newCapacity) 
    {
        this.courseCapacity = newCapacity;
    }
    
    public int getCourseCapacity()
    {
            return courseCapacity;
    }
    
    public String getCourseName()
    {
        return courseName;
    }
    
    public void setCourseName(String name) 
    {
        this.courseName = name;
    }
    //Returns the course info as a string
    public String toString() 
    {
        return "Course ID: " + courseID + ", Name: " + courseName + ", Capacity: " + courseCapacity;
    }
    //Adds each student object to a roster for display purposes
    public String getRoster() 
    {
    StringBuilder roster = new StringBuilder();

    if (enrolledStudents.isEmpty()) 
    {
        roster.append("No students enrolled in this course.");
    } 
    else 
    {
        roster.append("Enrolled Students:\n");
        for (Student student : enrolledStudents) 
        {
            roster.append("Student ID#: ").append(student.getStudentID())
                    .append(" ").append(student.getLastName()).append(", ")
                    .append(student.getFirstName()).append(" Major: ")
                    .append(student.getStudentMajor()).append(" Year: ")
                    .append(student.getStudentYear()).append("\n");
        }
    }
    return roster.toString();
    }

    public int getCourseID() 
    {
        return courseID;
    }
    //Checks to see if there is an instructor in the course selected, if so it deletes it
    public void removeInstructor() 
    {
        if (courseInstructor != null) 
        {
            System.out.println("Removing instructor " + courseInstructor.getTitle() + " from " + courseName);
            courseInstructor = null;
        } 
        else 
        {
            System.out.println("No instructor in the course. Cannot remove an instructor.");
        }
    }    

}



    

