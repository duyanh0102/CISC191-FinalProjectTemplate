package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a response object for a student request.
 */
public class StudentResponse implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String sport;

    // This ObjectMapper instance is used to convert the object to and from JSON.
    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Students> students;
    private boolean success;

    /**
     * Converts a StudentResponse object to its JSON representation.
     *
     * @param student The StudentResponse object to convert.
     * @return The JSON representation of the StudentResponse object.
     * @throws Exception if there was an error converting the object to JSON.
     */
    public static String toJSON(StudentResponse student) throws Exception {
        return objectMapper.writeValueAsString(student);
    }

    /**
     * Converts a JSON string to a StudentResponse object.
     *
     * @param input The JSON string to convert.
     * @return The StudentResponse object represented by the JSON string.
     * @throws Exception if there was an error converting the JSON string to an object.
     */
    public static StudentResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, StudentResponse.class);
    }

    /**
     * Constructs an empty StudentResponse object.
     */
    protected StudentResponse() {
    }

    /**
     * Constructs a new StudentResponse object with the specified properties.
     *
     * @param id The ID of the student.
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param age The age of the student.
     * @param sport The sport that the student plays.
     */
    public StudentResponse(Integer id, String firstName, String lastName, Integer age, String sport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sport = sport;
    }

    /**
     * Constructs a new StudentResponse object with the specified list of students and success status.
     *
     * @param students The list of students.
     * @param success The success status of the response.
     */
    public StudentResponse(List<Students> students, boolean success) {
        this.students = students;
        this.success = success;
    }

    /**
     * Returns a string representation of the StudentResponse object.
     *
     * @return A string representation of the StudentResponse object.
     */
    @Override
    public String toString() {
        return String.format(
                "Student[id=%d, firstName='%s', lastName='%s', age=%d, sport='%s']",
                id, firstName, lastName, age, sport);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getSport() {
        return sport;
    }

    public List<Students> getStudents() {
        return students;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
