package edu.sdccd.cisc191.template;

/**
 * Abstract base class for all types of students.
 */
public abstract class Students {
    private String id; // Student ID
    private String lastName; // Student last name
    private String firstName; // Student first name
    private double gpa; // Student grade point average

    /**
     * Constructs a new Student object.
     *
     * @param id        The student ID.
     * @param lastName  The student last name.
     * @param firstName The student first name.
     * @param gpa       The student grade point average.
     */
    public Students(int id, String lastName, String firstName, double gpa) {
        this.id = String.valueOf(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
    }

    /**
     * Abstract method to get the sport played by the student.
     *
     * @return The sport played by the student.
     */
    public abstract String sport();

    /**
     * Gets the student ID.
     *
     * @return The student ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the student ID.
     *
     * @param id The new student ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the student first name.
     *
     * @return The student first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the student first name.
     *
     * @param firstName The new student first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the student last name.
     *
     * @return The student last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the student last name.
     *
     * @param lastName The new student last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the student grade point average.
     *
     * @return The student grade point average.
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Sets the student grade point average.
     *
     * @param gpa The new student grade point average.
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
