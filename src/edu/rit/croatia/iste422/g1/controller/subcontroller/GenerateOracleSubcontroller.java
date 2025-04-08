package edu.rit.croatia.iste422.g1.controller.subcontroller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;

/**
 * Handles the "Generate Oracle" action within the application.
 * <p>
 * The {@code GenerateOracleSubcontroller} is responsible for triggering the script generation, trigger of parsing it, and updating the UI and model with the script data.
 * This class extends {@link Subcontroller} and interacts with the {@link SchemaView} and
 * {@link SchemaModel}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Triggering the script generation process in model.</li>
 *   <li>Updating the view with the uploaded script data and displaying relevant messages.</li>
 *   <li>Handling errors during file parsing and providing feedback to the user using logger.</li>
 * </ul>
 *
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
 * @author Mariia Zarovska
 * @version 2.8
 */
public class GenerateOracleSubcontroller extends Subcontroller {


    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(GenerateOracleSubcontroller.class);



    /**
     * Constructs an {@code GenerateOracleSubcontroller} and initializes it with the given view and model.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the application data.
     */
    public GenerateOracleSubcontroller(SchemaView schemaView, SchemaModel schemaModel){
        super(schemaView, schemaModel);
        logger.info("Set GenerateOracleSubcontroller successfully...");
    }


    /**
     * Handles the "Generate Oracle" action event.
     * <p>
     * This method triggers the schemaModel to generate the "oracle" type of script and then triggers the 
     * schemaView to interpret, set the script and refresh the view to display it
     * </p>
     *
     * @param event the action event triggered by the "Generate Oracle" action.
     */
    @Override
    public void handle(ActionEvent event) {
        logger.info("Attempting to retrieve script file...");
        schemaModel.generateScript("oracle");
        schemaView.setOutputView(schemaModel.getScript());
        logger.info("Retrieved Generated Oracle script file successfully");
    }
    

}
