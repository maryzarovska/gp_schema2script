package edu.rit.croatia.iste422.g1.main;

import edu.rit.croatia.iste422.g1.controller.SchemaController;
import javafx.application.Application;
import javafx.stage.Stage;
import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main entry point for the application.
 * <p>
 * The {@code Main} class extends {@link Application} and serves as the main entry point for launching
 * the JavaFX application. It initializes the model, view, and controller components of the application.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Launching the JavaFX application.</li>
 *   <li>Initializing the {@link SchemaModel}, {@link SchemaView}, and {@link SchemaController} components.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as application initialization, model and view setup, and controller initialization.
 * </p>
 *
 * @see Application
 * @see SchemaModel
 * @see SchemaView
 * @see SchemaController
 * 
 * @author Orest Brukhal
 * @version 2.8
 */
public class Main extends Application {

    /**
     * Logger instance for recording application events and debugging information.
     */
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * The main method which serves as the entry point of the application.
     * <p>
     * This method logs the initialization process and launches the JavaFX application.
     * </p>
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        logger.info("Initializing");
        launch(args);
    }

    /**
     * Starts the JavaFX application.
     * <p>
     * This method initializes the {@link SchemaModel} and {@link SchemaView}, sets up the view,
     * and creates an instance of {@link SchemaController} to manage the application logic.
     * </p>
     *
     * @param primaryStage the primary stage for this application.
     * @throws Exception if an error occurs during the initialization.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Initializing Model and View parameters for Controller");
        SchemaModel model = new SchemaModel();
        SchemaView view = new SchemaView();
        view.start(primaryStage);
        logger.info("Initializing Controller");
        new SchemaController(model, view);
    }
}
