package edu.rit.croatia.iste422.g1.controller.subcontroller;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Handles the "Open File" action within the application.
 * <p>
 * The {@code OpenFileSubontroller} is responsible for facilitating the selection and
 * uploading of schema files, parsing them, and updating the UI and model with the schema data.
 * This class extends {@link Subcontroller} and interacts with the {@link SchemaView} and
 * {@link SchemaModel}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Opening a file dialog for the user to select a schema file.</li>
 *   <li>Validating and uploading the selected file to the model.</li>
 *   <li>Updating the view with the uploaded schema data and displaying relevant messages.</li>
 *   <li>Handling errors during file parsing and providing feedback to the user.</li>
 * </ul>
 *
 * <h2>Supported File Types:</h2>
 * <ul>
 *   <li>JSON files (*.json)</li>
 *   <li>XML files (*.xml)</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as successful uploads, parsing errors, and user actions for better debugging
 * and monitoring.
 * </p>
 *
 * @see Subcontroller
 * @see SchemaModel
 * @see SchemaView
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class OpenFileSubontroller extends Subcontroller {

    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(OpenFileSubontroller.class);

    /**
     * Constructs an {@code OpenFileSubontroller} and initializes it with the given view and model.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the application data.
     */
    public OpenFileSubontroller(SchemaView schemaView, SchemaModel schemaModel) {
        super(schemaView, schemaModel);
        logger.info("Set OpenFileSubcontroller successfully...");
    }

    /**
     * Handles the "Open File" action event.
     * <p>
     * This method displays a file chooser dialog, allowing the user to select a schema file.
     * Once a file is selected, it uploads and parses the schema, updates the model and view,
     * and provides appropriate feedback to the user.
     * </p>
     *
     * @param event the action event triggered by the "Open File" action.
     */
    @Override
    public void handle(ActionEvent event) {
        logger.info("Attempting to upload schema file...");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/resources"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Schema Files", "*.json", "*.xml"));
        Stage stage = (Stage) schemaView.openFileMenu.getParentPopup().getOwnerWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            logger.info("Selected file: {}", file.getAbsolutePath());

            try {
                schemaView.setInputView(new ArrayList<>());
                schemaModel.schemaUpload(file);

                schemaView.setInputView(schemaModel.getTables());
                schemaView.setOutputView(
                        "1. Open configuration file by clicking \"File\" -> \"Open\".\n" +
                        "2. Press \"Generate\" button to generate MySQL script."
                );
                schemaView.showSuccess("Uploaded and parsed the schema successfully");
                logger.info("Uploaded and parsed the schema successfully");
            } catch (edu.rit.croatia.iste422.g1.model.SchemaParsingException e) {
                logger.error("Error parsing the schema: {}", e.getMessage(), e);
                schemaView.showError("Error parsing the schema: " + e.getMessage());
            }
        } else {
            logger.warn("File is not selected");
        }
    }
}
