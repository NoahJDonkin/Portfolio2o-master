package org.example.portfolio2o;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Database db;

    @Override
    public void start(Stage primaryStage) {
        db = new Database();
        if (db == null || db.getConnection() == null) {
            System.out.println("Database connection is not established. Exiting.");
            return;
        }

        BachelorView bachelorView = new BachelorView(db);

        Scene scene = new Scene(bachelorView.getGridPane(), 800, 600);
        primaryStage.setTitle("Bachelor Program Selection");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            db.closeConnection();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
