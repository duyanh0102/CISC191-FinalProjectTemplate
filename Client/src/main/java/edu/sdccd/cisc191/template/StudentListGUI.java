package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a graphical user interface (GUI) for a student list application.
 * Users can add, remove, and view student records through a table view and input fields.
 */
public class StudentListGUI extends Application  {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public StudentListGUI(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }
    // Constants for maximum number of students and number of fields per student
    private final int MAX_STUDENTS = 100;
    private final int NUM_FIELDS = 5;

    // Labels for input fields
    private final String[] FIELD_LABELS = {"ID", "First Name", "Last Name", "Sport", "GPA"};

    // Two-dimensional array to hold student records
    private String[][] students = new String[MAX_STUDENTS][NUM_FIELDS];
    private HashMap<Integer, Map<String, String>> studentsMap = new HashMap<Integer, Map<String, String>>();

    // Number of currently stored students
    private int numStudents = 0;

    // Main pane for GUI layout
    private BorderPane mainPane;

    // Input pane for adding and removing students
    private GridPane inputPane;

    // Table view for displaying student records
    private TableView<Students> table;

    // Observable list of student records for populating table view
    private ObservableList<Students> studentData = FXCollections.observableArrayList();

    /**
     * Launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Sets up the GUI layout, input fields, and table view with student records.
     *
     * @param primaryStage the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student List");

        // Create main pane and input pane
        mainPane = new BorderPane();
        inputPane = new GridPane();

        // Create table view
        table = new TableView<>();

        // Set up input pane layout
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        inputPane.setPadding(new Insets(10, 10, 10, 10));

        // Add input fields to input pane
        for (int i = 0; i < NUM_FIELDS; i++) {
            Label label = new Label(FIELD_LABELS[i] + ":");
            inputPane.add(label, 0, i);
            TextField field = new TextField();
            inputPane.add(field, 1, i);
        }

        // Add buttons to input pane
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addStudent());
        inputPane.add(addButton, 0, NUM_FIELDS);

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearFields());
        inputPane.add(clearButton, 1, NUM_FIELDS);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeStudent());
        inputPane.add(removeButton, 2, NUM_FIELDS);

        // Add file I/O buttons to input pane
        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadStudents());
        inputPane.add(loadButton, 3, NUM_FIELDS);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveStudents());
        inputPane.add(saveButton, 4, NUM_FIELDS);

        // Set up table view columns
        TableColumn<Students, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Students, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Students, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Students, String> sportCol = new TableColumn<>("Sport");
        sportCol.setCellValueFactory(new PropertyValueFactory<>("sport"));

        TableColumn<Students, Double> gpaCol = new TableColumn<>("GPA");
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        // Add columns to table view
        table.getColumns().add(idCol);
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(sportCol);
        table.getColumns().add(gpaCol);

        // Set table view data source
        table.setItems(studentData);

        // Add input pane and table view to main pane
        mainPane.setLeft(inputPane);
        mainPane.setCenter(table);

        // Create scene with main pane
        Scene scene = new Scene(mainPane, 800, 600);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void saveStudents() {
        // Create file chooser and show save dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Student Data File");
        File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());

        if (file != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                // Write student data to file
                out.writeObject(studentData);
                out.flush();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student data saved to file.");
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving student data to file.");
                alert.showAndWait();
            }
        }
    }


    private void loadStudents() {
        // Create file chooser and show open dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Student Data File");
        File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if (file != null) {
            // Clear existing student data
            studentData.clear();
            numStudents = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Read file line by line and parse each line into a Students object
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length == NUM_FIELDS) {
                        athleteStudent student = new athleteStudent(fields[0], fields[1], fields[2], fields[3], Double.parseDouble(fields[4]));
                        studentData.add(student);
                        numStudents++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**

     Adds a new student record to the table view and studentData list if the maximum number of students has not been reached.

     Retrieves input field data from the input pane and creates a new student object based on the Sport field value.

     Clears input fields after adding a student record.

     Displays an error message if the maximum number of students has been reached.
     */
    private void addStudent() {
        if (numStudents < MAX_STUDENTS) {
            // Create new student record
            Map<String, String> student = new HashMap<>();
            for (int i = 0; i < NUM_FIELDS; i++) {
                TextField field = (TextField) inputPane.getChildren().get(i * 2 + 1);
                student.put("field" + i, field.getText());
                int id = 0;
                studentsMap.put(id, student);
            }
            // Create student object based on Sport field value
            if (student.get("field3").equals("none")) {
                studentData.add(new normalStudent(student.get("field0"), student.get("field1"), student.get("field2"), Double.parseDouble(student.get("field4"))));
            } else {
                studentData.add(new athleteStudent(student.get("field0"), student.get("field1"), student.get("field2"), student.get("field3"), Double.parseDouble(student.get("field4"))));
            }

            numStudents++;
            clearFields();
        } else {
            // Display error message if maximum number of students has been reached
            Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum number of students reached.");
            alert.showAndWait();
        }
    }
    public Map<String, String> searchStudent(String id) {
        // Search for the Student object in the HashMap using the ID as the key
        Map<String, String> student = studentsMap.get(id);
        // Return the Student object
        return student;
    }


    /**
             * Removes a selected student record from the table view and data source.
             */
    private void removeStudent() {
        // Get selected student record
        Students selectedStudent = table.getSelectionModel().getSelectedItem();

        // Remove student record from students map
        studentsMap.remove(selectedStudent.getId());

        // Remove student record from student data list
        studentData.remove(selectedStudent);
    }


    /**
     * Clears input fields.
     */
    private void clearFields() {
        for (int i = 0; i < NUM_FIELDS; i++) {
            TextField field = (TextField) inputPane.getChildren().get(2 * i + 1);
            field.setText("");
        }
    }
}

