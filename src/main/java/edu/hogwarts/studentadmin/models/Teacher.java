package edu.hogwarts.studentadmin.models;
import java.util.Date;

public class Teacher {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private House house;
    private boolean headOfHouse;
    private EmpType employment;
    private Date employmentStart;
    private Date getEmploymentEnd;

    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(boolean headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public EmpType getEmployment() {
        return employment;
    }

    public void setEmployment(EmpType employment) {
        this.employment = employment;
    }

    public Date getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(Date employmentStart) {
        this.employmentStart = employmentStart;
    }

    public Date getGetEmploymentEnd() {
        return getEmploymentEnd;
    }

    public void setGetEmploymentEnd(Date getEmploymentEnd) {
        this.getEmploymentEnd = getEmploymentEnd;
    }

    // Constructors

    public Teacher(String firstName, String middleName, String lastName, Date dateOfBirth, House house, boolean headOfHouse, EmpType employment, Date employmentStart, Date getEmploymentEnd) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.headOfHouse = headOfHouse;
        this.employment = employment;
        this.employmentStart = employmentStart;
        this.getEmploymentEnd = getEmploymentEnd;
    }

    public Teacher(String firstName, String lastName, Date dateOfBirth, House house) {
        this(firstName, "", lastName, dateOfBirth, house, false, null, null, null);
    }
}
