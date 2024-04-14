//Programmer Names: Ava Crites, Trevor Hudson, Samuel Ng
//Date Completed: 11/16/23
//PA Purpose: To dvelop a university's student management system using objects and classes formed from a UML diagram.
/*My submission of this program indicates that I have neither received nor
given substantial or unauthorized assistance in writing this program.*/

public class Instructor 
{
    //Class properties
    private String name, prefix, officeLocation, department, email;
    private int instructorID;
    private static int nextInstructID = 100;
    //Constructor
    public Instructor(String name, String prefix, String office, String dept, String email) 
    {
        this.name = name;
        this.prefix = prefix;
        this.officeLocation = office;
        this.department = dept;
        this.email = email;
        this.instructorID = nextInstructID++;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setPrefix(String prefix) 
    {
        this.prefix = prefix;
    }

    public void setDepartment(String dept) 
    {
        this.department = dept;
    }
    //Input validation for email inputs
    public void setEmail(String email) 
    {
        if (email.contains("@")) 
        {
            this.email = email;
        } 
        else 
        {
            System.out.println("Invalid email. Email must contain a '@'");
        }
    }

    public void setOfficeLocation(String office) 
    {
        this.officeLocation = office;
    }

    public String getTitle() 
    {
        return prefix + " " + name;
    }
    //Returns the Instructor's info as a string
    public String toString() 
    {
        return "Instructor ID: " + instructorID + ", Name: " + getTitle() + ", Office: " + officeLocation + ", Department: " + department + ", Email: " + email;
    }
}


