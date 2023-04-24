package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class StudentRequest implements Serializable {
    private Integer id;
    private static final long serialVersionUID = 1L;
    public static final String GET_ALL = "GET_ALL";
    public static final String GET_BY_ID = "GET_BY_ID";
    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    private String requestType;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Students student;
    private int studentId;

    /**
     * Converts a StudentRequest object to a JSON string representation.
     *
     * @param student the StudentRequest object to convert
     * @return a JSON string representation of the StudentRequest object
     * @throws Exception if there is an error during the conversion process
     */
    public static String toJSON(StudentRequest student) throws Exception {
        return objectMapper.writeValueAsString(student);
    }

    /**
     * Converts a JSON string representation to a StudentRequest object.
     *
     * @param input the JSON string representation to convert
     * @return a StudentRequest object
     * @throws Exception if there is an error during the conversion process
     */
    public static StudentRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, StudentRequest.class);
    }

    protected StudentRequest() {}

    public StudentRequest(String requestType, Students student) {
        this.requestType = requestType;
        this.student = student;
    }

    public StudentRequest(String requestType, int studentId) {
        this.requestType = requestType;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
