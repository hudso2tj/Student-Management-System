//Programmer Names: Ava Crites, Trevor Hudson, Samuel Ng
//Date Completed: 11/16/23
//PA Purpose: To dvelop a university's student management system using objects and classes formed from a UML diagram.
/*My submission of this program indicates that I have neither received nor
given substantial or unauthorized assistance in writing this program.*/

import java.util.Scanner;
import java.util.ArrayList;

public class Student 
{
   
    private String firstName, lastName, studentYear, studentMajor, studentEmail;
    private double GPA;
    private int studentID;
    private static int nextStudentID = 1000;
    
    public Student(String name, String year, String major, double GPA, String email) 
    {
        setFullName(name);
        setStudentYear(year);
        setStudentMajor(major);
        setGPA(GPA);
        setStudentEmail(email);
        this.studentID = nextStudentID++;
    }
        
    
    public void setFullName(String fullName) 
    {
        String[] names = fullName.split(" ");
        
        if (names.length >= 2) 
        {
            this.firstName = names[0];
            this.lastName = names[1];
        } 
        else 
        {
            System.out.println("Invalid full name format");
        }
    }
    
    public String getFirstName() 
    {
        return firstName; 
    }
    
    public String getLastName() 
    {
        return lastName; 
    }

    public void setGPA(double GPA) 
    {
        if (GPA >= 0.0 && GPA <= 5.0) 
        {
            this.GPA = GPA;
        } 
        else 
        {
            System.out.println("Invalid GPA. Must be between 0.0 and 5.0");
        }
    }
    
    public double getGPA() 
    {
        return GPA;
    }
    
    public int getStudentID() 
    {
        return studentID;
    }
    
    public void setStudentEmail(String studentEmail) 
    {
        if (studentEmail.contains("@")) 
        {
            this.studentEmail = studentEmail;
        }
        else 
        {
            System.out.println("Invalid email. Email must contain a '@' ");
        }
    }
    
    public String getStudentYear() 
    {
        return studentYear;
    }
    
    public void setStudentYear(String year) 
    {
        int numYear = convertYear(year);
        
        while (numYear <= 0 && numYear >4) {
            System.out.println("Invalid input. Please enter a number between 1 and 4. ");
            Scanner scanner = new Scanner(System.in);
            year = scanner.nextLine();
            numYear = convertYear(year);
        }
        
        switch(numYear) {
            case 1:
                this.studentYear = "Freshman";
                break;
            case 2: 
                this.studentYear = "Sophomore";
                break; 
            case 3:
                this.studentYear = "Junior";
                break;
            case 4: 
                this.studentYear = "Senior";
                break;
        }
    }
    //Converts the int year into the respective title: freshman, sophomore, etc
    private int convertYear(String yearInput) 
    {   
        int numYear = 0; 
        String lowerYear = yearInput.toLowerCase();
        
        switch(lowerYear) 
        {
            case "1": 
            case "freshman":
                numYear = 1;
                break;
            case "2": 
            case "sophomore":
                numYear = 2;
                break;
            case "3":
            case "junior":
                numYear = 3; 
                break; 
            case "4":
            case "senior":
                numYear = 4;
                break; 
        }
            return numYear;
             
    }
        
    public String getStudentMajor() 
    {
        return studentMajor;
    }
    
    public void setStudentMajor(String major) 
    {
        this.studentMajor = major;
    }
    //Returns the split full name as a string
    public String getName() 
    {
        return firstName + " " + lastName;  
    } 
}   



