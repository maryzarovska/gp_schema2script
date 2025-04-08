package edu.rit.croatia.iste422.g1.controller;

import edu.rit.croatia.iste422.g1.controller.subcontroller.DeleteTableSubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.EditTableViewSubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateSubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateOracleSubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.OpenFileSubontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.SaveAsSubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.SetForeignKeySubcontroller;
import edu.rit.croatia.iste422.g1.controller.subcontroller.GeneratePostgreSQLSubcontroller;
import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main controller class responsible for managing the interaction between the
 * {@link SchemaModel}
 * and the {@link SchemaView}.
 * <p>
 * This controller sets up the action handlers for various UI components in the
 * {@link SchemaView}
 * by delegating functionality to appropriate subcontrollers.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 * <li>Coordinates communication between the model and view.</li>
 * <li>Assigns event handlers to UI components in the {@link SchemaView}.</li>
 * <li>Delegates specific actions to subcontrollers for modularity and
 * separation of concerns.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * This class uses Log4j for logging important actions and events, such as the
 * initialization
 * of event handlers.
 * </p>
 *
 * @see SchemaModel
 * @see SchemaView
 * @see OpenFileSubontroller
 * @see GenerateSubcontroller
 * @see GenerateOracleSubcontroller
 * @see SetForeignKeySubcontroller
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class SchemaController {

    private EditTableViewSubcontroller editSubcontroller;
    /**
     * Logger instance for recording application events and debugging information.
     */
    private static final Logger logger = LogManager.getLogger(SchemaController.class);

    /**
     * Constructs a {@code SchemaController} and initializes the action handlers for
     * the {@link SchemaView}.
     *
     * @param model the model object representing the application's data.
     * @param view  the view object representing the application's UI.
     */
    public SchemaController(SchemaModel model, SchemaView view) {
        setAllOnAction(view, model);
    }

    /**
     * Sets the event handlers for all UI components in the provided
     * {@link SchemaView}.
     * <p>
     * The method initializes subcontrollers for each UI component to handle
     * specific actions,
     * such as opening files, generating scripts, and editing table views.
     * </p>
     *
     * @param view  the {@link SchemaView} whose components will have event handlers
     *              set.
     * @param model the {@link SchemaModel} used by the subcontrollers for
     *              operations.
     */
    public void setAllOnAction(SchemaView view, SchemaModel model) {
        logger.info("Setting subcontrollers on action...");
        view.getOpenFileButton().setOnAction(new OpenFileSubontroller(view, model));
        view.getGenerateButton().setOnAction(new GenerateSubcontroller(view, model));
        view.getOracleGenerateButton().setOnAction(new GenerateOracleSubcontroller(view, model));
        view.getPostgreSQLGenerateButton().setOnAction(new GeneratePostgreSQLSubcontroller(view, model));
        view.getSaveFileButton().setOnAction(new SaveAsSubcontroller(view, model));

        for (int i = 0; i < view.getEditButtons().length; i++) {
            editSubcontroller = new EditTableViewSubcontroller(
                    view, model, view.getTableViews().get(i), view.getEditButtons()[i]);

            logger.info("Updated SchemaModel: {}", getUpdatedSchemaModel());
            view.getEditButtons()[i].setOnAction(editSubcontroller);
        }
        for (int i = 0; i < view.getDeleteButtons().length; i++) {
            view.getDeleteButtons()[i].setOnAction(new DeleteTableSubcontroller(view, model));
        }
        view.getSetFKButton().setOnAction(new SetForeignKeySubcontroller(view, model));
        logger.info("Set subcontrollers on action successfully");
    }

    /**
     * Fetches the updated {@link SchemaModel}.
     *
     * @return the updated {@link SchemaModel}.
     */
    public SchemaModel getUpdatedSchemaModel() {
        logger.info("Fetching updated SchemaModel...");
        return editSubcontroller.getUpdatedSchemaModel();
    }
}