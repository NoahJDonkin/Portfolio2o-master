package org.example.portfolio2o;

import javafx.scene.control.*;

public class HelloController {

    private Database db;
    private ComboBox<String> programComboBox, courseComboBox, projectComboBox, electiveComboBox;
    private ListView<String> programListView, courseListView, projectListView, electiveListView;
    private Button selectProgramButton, selectCourseButton, selectProjectButton, selectElectiveButton;
    private Label ectsLabelProgram, ectsLabelCourse, ectsLabelProject, ectsLabelElective;

    public HelloController(Database db,
                           ComboBox<String> programComboBox,
                           ComboBox<String> courseComboBox,
                           ComboBox<String> projectComboBox,
                           ComboBox<String> electiveComboBox,
                           ListView<String> programListView,
                           ListView<String> courseListView,
                           ListView<String> projectListView,
                           ListView<String> electiveListView,
                           Button selectProgramButton,
                           Button selectCourseButton,
                           Button selectProjectButton,
                           Button selectElectiveButton,
                           Label ectsLabelProgram,
                           Label ectsLabelCourse,
                           Label ectsLabelProject,
                           Label ectsLabelElective) {

        this.db = db;
        this.programComboBox = programComboBox;
        this.courseComboBox = courseComboBox;
        this.projectComboBox = projectComboBox;
        this.electiveComboBox = electiveComboBox;
        this.programListView = programListView;
        this.courseListView = courseListView;
        this.projectListView = projectListView;
        this.electiveListView = electiveListView;
        this.selectProgramButton = selectProgramButton;
        this.selectCourseButton = selectCourseButton;
        this.selectProjectButton = selectProjectButton;
        this.selectElectiveButton = selectElectiveButton;
        this.ectsLabelProgram = ectsLabelProgram;
        this.ectsLabelCourse = ectsLabelCourse;
        this.ectsLabelProject = ectsLabelProject;
        this.ectsLabelElective = ectsLabelElective;

        initializeHandlers();
    }

    private void initializeHandlers() {
        selectProgramButton.setOnAction(e -> {
            String selectedProgram = programComboBox.getValue();
            if (selectedProgram != null && !programListView.getItems().contains(selectedProgram)) {
                programListView.getItems().add(selectedProgram);
                ectsLabelProgram.setText("ECTS: " + db.getECTSForItem(selectedProgram, "Program"));
                db.saveSelectedItem(selectedProgram, "Program", 1); // Assuming student ID 1
            }
        });

        selectCourseButton.setOnAction(e -> {
            String selectedCourse = courseComboBox.getValue();
            if (selectedCourse != null && !courseListView.getItems().contains(selectedCourse)) {
                courseListView.getItems().add(selectedCourse);
                ectsLabelCourse.setText("ECTS: " + db.getECTSForItem(selectedCourse, "Course"));
                db.saveSelectedItem(selectedCourse, "Course", 1);
            }
        });

        selectProjectButton.setOnAction(e -> {
            String selectedProject = projectComboBox.getValue();
            if (selectedProject != null && !projectListView.getItems().contains(selectedProject)) {
                projectListView.getItems().add(selectedProject);
                ectsLabelProject.setText("ECTS: " + db.getECTSForItem(selectedProject, "Project"));
                db.saveSelectedItem(selectedProject, "Project", 1);
            }
        });

        selectElectiveButton.setOnAction(e -> {
            String selectedElective = electiveComboBox.getValue();
            if (selectedElective != null && !electiveListView.getItems().contains(selectedElective)) {
                electiveListView.getItems().add(selectedElective);
                ectsLabelElective.setText("ECTS: " + db.getECTSForItem(selectedElective, "Elective"));
                db.saveSelectedItem(selectedElective, "Elective", 1);
            }
        });
    }
}
