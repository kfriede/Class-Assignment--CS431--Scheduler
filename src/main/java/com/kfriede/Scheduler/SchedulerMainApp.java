package com.kfriede.Scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <p>Primary entrance point for JavaFX UI
 * <p>Builds scene from primary FXML and displays on screen
 */
public class SchedulerMainApp extends Application {
	private static String MAIN_VIEW_FILE_PATH = "Views/MainWindow.fxml";

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(MAIN_VIEW_FILE_PATH));

		Scene scene = new Scene(root);

		stage.setTitle("Scheduler UI");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
