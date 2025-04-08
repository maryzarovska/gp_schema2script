package edu.rit.croatia.iste422.g1.controller.subcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;

/**
 * Handles the "Generate PostgreSQL" action within the application.
 * <p>
 * The {@code GeneratePostgreSQLSubcontroller} is responsible for triggering the script generation,
 * parsing it, and updating the UI and model with the script data.
 * This class extends {@link Subcontroller} and interacts with the {@link SchemaView} and
 * {@link SchemaModel}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Triggering the script generation process in the model.</li>
 *   <li>Updating the view with the generated script data and displaying relevant messages.</li>
 *   <li>Handling errors during script generation and providing feedback to the user using the logger.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as script generation success, errors, and user actions for better debugging
 * and monitoring.
 * </p>
 *
 * @see Subcontroller
 * @see SchemaModel
 * @see SchemaView
 * 
 * @author Marko Skific
 * @version 2.9
 */
public class GeneratePostgreSQLSubcontroller extends Subcontroller {

    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(GeneratePostgreSQLSubcontroller.class);

    /**
     * Constructs a {@code GeneratePostgreSQLSubcontroller} and initializes it with the given view and model.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the application data.
     */
    public GeneratePostgreSQLSubcontroller(SchemaView schemaView, SchemaModel schemaModel) {
        super(schemaView, schemaModel);
        logger.info("Set GeneratePostgreSQLSubcontroller successfully...");
    }

    /**
     * Handles the "Generate PostgreSQL" action event.
     * <p>
     * This method triggers the schemaModel to generate the "postgresql" type of script and then triggers the 
     * schemaView to interpret, set the script, and refresh the view to display it.
     * </p>
     *
     * @param event the action event triggered by the "Generate PostgreSQL" action.
     */
    @Override
    public void handle(ActionEvent event) {
        logger.info("Attempting to retrieve PostgreSQL script file...");
        schemaModel.generateScript("postgresql"); // Generate PostgreSQL script
        schemaView.setOutputView(schemaModel.getScript()); // Display the script in the view
        logger.info("Retrieved and displayed PostgreSQL script file successfully");
    }
}
