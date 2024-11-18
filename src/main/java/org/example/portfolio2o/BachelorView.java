package org.example.portfolio2o;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.List;

public class BachelorView {
    private Database database;
    private GridPane gridPane;
    private ComboBox<String> programComboBox, courseComboBox, projectComboBox, electiveComboBox;
    private Button selectProgramButton, selectCourseButton, selectProjectButton, selectElectiveButton;
    private ListView<String> programListView, courseListView, projectListView, electiveListView;
    private Label ectsLabelProgram, ectsLabelCourse, ectsLabelProject, ectsLabelElective;

    public BachelorView(Database database) {
        this.database = database;
        initializeComponents();
        initializeHandlers();
    }

    private void initializeComponents() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label programLabel = new Label("Program");
        Label courseLabel = new Label("Course");
        Label projectLabel = new Label("Project");
        Label electiveLabel = new Label("Elective");

        programComboBox = new ComboBox<>();
        courseComboBox = new ComboBox<>();
        projectComboBox = new ComboBox<>();
        electiveComboBox = new ComboBox<>();

        selectProgramButton = new Button("Select");
        selectCourseButton = new Button("Select");
        selectProjectButton = new Button("Select");
        selectElectiveButton = new Button("Select");

        programListView = new ListView<>();
        courseListView = new ListView<>();
        projectListView = new ListView<>();
        electiveListView = new ListView<>();

        ectsLabelProgram = new Label("ECTS: 0");
        ectsLabelCourse = new Label("ECTS: 0");
        ectsLabelProject = new Label("ECTS: 0");
        ectsLabelElective = new Label("ECTS: 0");

        populateComboBoxes();

        gridPane.add(programLabel, 0, 0);
        gridPane.add(courseLabel, 1, 0);
        gridPane.add(projectLabel, 2, 0);
        gridPane.add(electiveLabel, 3, 0);

        gridPane.add(programComboBox, 0, 1);
        gridPane.add(courseComboBox, 1, 1);
        gridPane.add(projectComboBox, 2, 1);
        gridPane.add(electiveComboBox, 3, 1);

        gridPane.add(selectProgramButton, 0, 2);
        gridPane.add(selectCourseButton, 1, 2);
        gridPane.add(selectProjectButton, 2, 2);
        gridPane.add(selectElectiveButton, 3, 2);

        gridPane.add(programListView, 0, 3);
        gridPane.add(courseListView, 1, 3);
        gridPane.add(projectListView, 2, 3);
        gridPane.add(electiveListView, 3, 3);

        gridPane.add(ectsLabelProgram, 0, 4);
        gridPane.add(ectsLabelCourse, 1, 4);
        gridPane.add(ectsLabelProject, 2, 4);
        gridPane.add(ectsLabelElective, 3, 4);
    }

    private void initializeHandlers() {
        selectProgramButton.setOnAction(e -> {
            String selectedProgram = programComboBox.getValue();
            if (selectedProgram != null) {
                programListView.getItems().clear(); // Clear any existing selections
                programListView.getItems().add(selectedProgram);
                ectsLabelProgram.setText("ECTS: " + database.getECTSForItem(selectedProgram, "Program"));

                // Filter courses and projects based on selected program
                filterCoursesAndProjects(selectedProgram);
            }
        });

        selectCourseButton.setOnAction(e -> {
            String selectedItem = courseComboBox.getValue();
            if (selectedItem != null) {
                courseListView.getItems().add(selectedItem);
                ectsLabelCourse.setText("ECTS: " + database.getECTSForItem(selectedItem, "Course"));
            }
        });

        selectProjectButton.setOnAction(e -> {
            String selectedItem = projectComboBox.getValue();
            if (selectedItem != null) {
                projectListView.getItems().add(selectedItem);
                ectsLabelProject.setText("ECTS: " + database.getECTSForItem(selectedItem, "Project"));
            }
        });

        selectElectiveButton.setOnAction(e -> {
            String selectedItem = electiveComboBox.getValue();
            if (selectedItem != null) {
                electiveListView.getItems().add(selectedItem);
                ectsLabelElective.setText("ECTS: " + database.getECTSForItem(selectedItem, "Elective"));
            }
        });
    }

    private void filterCoursesAndProjects(String programName) {
        // Filter courses
        List<String> filteredCourses = database.fetchCoursesForProgram(programName);
        courseComboBox.getItems().clear();
        courseComboBox.getItems().addAll(filteredCourses);

        // Filter projects
        List<String> filteredProjects = database.fetchProjectsForProgram(programName);
        projectComboBox.getItems().clear();
        projectComboBox.getItems().addAll(filteredProjects);
    }

    private void populateComboBoxes() {
        programComboBox.getItems().addAll(database.fetchPrograms());
        // Initial population is empty; will be filled on program selection
        courseComboBox.getItems().clear();
        projectComboBox.getItems().clear();
        electiveComboBox.getItems().addAll(database.fetchElectives());
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
