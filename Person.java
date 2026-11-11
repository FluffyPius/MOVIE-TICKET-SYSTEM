// OUR PARENT CLASS
public class Person {

    // Attributes shared by different types of people
    private String personID;
    private String personName;

    // Constructor
    public Person(String personID, String personName) {
        this.personID = personID;
        this.personName = personName;
    }

    // Getters
    public String getPersonID() {
        return personID;
    }

    public String getPersonName() {
        return personName;
    }

    // Setters
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    // Displays person information
    public void displayPersonDetails() {
        System.out.println();
        System.out.println("Person ID: " + personID);
        System.out.println("Name: " + personName);
    }
}
