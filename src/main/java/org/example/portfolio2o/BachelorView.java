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
        // Program Selection
        selectProgramButton.setOnAction(e -> {
            String selectedProgram = programComboBox.getValue();
            if (selectedProgram != null && !programListView.getItems().contains(selectedProgram)) {
                programListView.getItems().add(selectedProgram);

                // Update ECTS
                int ectsForProgram = database.getECTSForItem(selectedProgram, "Program");
                int currentTotalECTS = Integer.parseInt(ectsLabelProgram.getText().replace("ECTS: ", ""));
                currentTotalECTS += ectsForProgram;
                ectsLabelProgram.setText("ECTS: " + currentTotalECTS);

                // Update related combo boxes (Courses and Projects)
                filterCoursesAndProjects(selectedProgram);
            }
        });

        // Course Selection
        selectCourseButton.setOnAction(e -> {
            String selectedCourse = courseComboBox.getValue();
            if (selectedCourse != null && !courseListView.getItems().contains(selectedCourse)) {
                courseListView.getItems().add(selectedCourse);

                // Update ECTS
                int ectsForCourse = database.getECTSForItem(selectedCourse, "Course");
                int currentTotalECTS = Integer.parseInt(ectsLabelCourse.getText().replace("ECTS: ", ""));
                currentTotalECTS += ectsForCourse;
                ectsLabelCourse.setText("ECTS: " + currentTotalECTS);
            }
        });

        // Project Selection
        selectProjectButton.setOnAction(e -> {
            String selectedProject = projectComboBox.getValue();
            if (selectedProject != null && !projectListView.getItems().contains(selectedProject)) {
                projectListView.getItems().add(selectedProject);

                // Update ECTS
                int ectsForProject = database.getECTSForItem(selectedProject, "Project");
                int currentTotalECTS = Integer.parseInt(ectsLabelProject.getText().replace("ECTS: ", ""));
                currentTotalECTS += ectsForProject;
                ectsLabelProject.setText("ECTS: " + currentTotalECTS);
            }
        });

        // Elective Selection
        selectElectiveButton.setOnAction(e -> {
            String selectedElective = electiveComboBox.getValue();
            if (selectedElective != null && !electiveListView.getItems().contains(selectedElective)) {
                electiveListView.getItems().add(selectedElective);

                // Update ECTS
                int ectsForElective = database.getECTSForItem(selectedElective, "Elective");
                int currentTotalECTS = Integer.parseInt(ectsLabelElective.getText().replace("ECTS: ", ""));
                currentTotalECTS += ectsForElective;
                ectsLabelElective.setText("ECTS: " + currentTotalECTS);
            }
        });

        // Deselect Handlers
        programListView.setOnMouseClicked(e -> removeItem(programListView, ectsLabelProgram, "Program"));
        courseListView.setOnMouseClicked(e -> removeItem(courseListView, ectsLabelCourse, "Course"));
        projectListView.setOnMouseClicked(e -> removeItem(projectListView, ectsLabelProject, "Project"));
        electiveListView.setOnMouseClicked(e -> removeItem(electiveListView, ectsLabelElective, "Elective"));
    }

    private void removeItem(ListView<String> listView, Label ectsLabel, String category) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listView.getItems().remove(selectedItem);

            // Deduct the ECTS of the removed item
            int ectsForItem = database.getECTSForItem(selectedItem, category);
            int currentTotalECTS = Integer.parseInt(ectsLabel.getText().replace("ECTS: ", ""));
            currentTotalECTS -= ectsForItem;
            ectsLabel.setText("ECTS: " + currentTotalECTS);
        }
    }

    private void filterCoursesAndProjects(String programName) {
        // Fetch courses and projects based on the selected program
        List<String> filteredCourses = database.fetchCoursesForProgram(programName);
        List<String> filteredProjects = database.fetchProjectsForProgram(programName);

        // Populate course and project combo boxes
        courseComboBox.getItems().setAll(filteredCourses);
        projectComboBox.getItems().setAll(filteredProjects);
    }

    private void populateComboBoxes() {
        // Populate programs and electives combo boxes
        programComboBox.getItems().addAll(database.fetchPrograms());
        electiveComboBox.getItems().addAll(database.fetchElectives());

        // Clear course and project combo boxes initially
        courseComboBox.getItems().clear();
        projectComboBox.getItems().clear();
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
