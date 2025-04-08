package edu.rit.croatia.iste422.g1.controller.subcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.model.Table;
import edu.rit.croatia.iste422.g1.model.TableRow;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
/**
 * Handles the "Edit" action within the application.
 * <p>
 * The {@code EditTableViewSubcontroller} is responsible to toggle the linked
 * {@link TableView} editable to true and changes the linked {@link Button} text
 * responsibly.
 * This class extends {@link Subcontroller} and interacts with the
 * {@link SchemaView} and
 * {@link SchemaModel}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 * <li>Toggling the TableView edit value.</li>
 * <li>Toggling the Button display text value.</li>
 * <li>Handling errors during file parsing and providing feedback to the user
 * using logger.</li>
 * </ul>
 *
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as successful uploads, parsing errors, and user actions for
 * better debugging
 * and monitoring.
 * </p>
 *
 * @see Subcontroller
 * @see SchemaModel
 * @see SchemaView
 * @see TableView
 * @see Button
 * 
 * @author Mattia Vela, Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class EditTableViewSubcontroller extends Subcontroller {

    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(EditTableViewSubcontroller.class);

    /**
     * Variable table, instance of TableView class:
     * holds the injected table from the EditTableViewSubcontroller constructor for
     * within class manipulation
     * 
     * Variable button, instance of Button class:
     * holds the injected button from the EditTableViewSubcontroller constructor for
     * within class manipulation
     */
    @SuppressWarnings("rawtypes")
    private TableView table;
    private Button button;

    /**
     * Constructs an {@code GenerateOracleSubcontroller} and initializes it with the
     * given view, model, table and button.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the
     *                    application data.
     * @param table       the {@link TableView} instance representing the table view
     *                    object to be toggled and manipulated.
     * @param button      the {@link Button} instance representing the button view
     *                    object to be toggled and manipulated.
     */
    @SuppressWarnings("rawtypes")
    public EditTableViewSubcontroller(SchemaView schemaView, SchemaModel schemaModel, TableView table, Button button) {
        super(schemaView, schemaModel);
        this.table = table;
        this.button = button;
        logger.info("Set EditTableViewSubcontroller successfully...");
    }

    /**
     * Handles the "Edit" action event.
     * <p>
     * Checks the value of table object's Table.isEditable(), type boolean
     * Depending on the value of table.isEditable(), the program run is followed by
     * two cases:
     * if value is false and the table is not Editable, the button is switching text
     * to "Stop Editing"
     * and table.isEditable() is being set to the reversed value (toggled);
     * if value is true and the table is Editable, the button is switching text to
     * "Edit"
     * and table.isEditable() is being set to the reversed value.
     * </p>
     *
     * @param arg0 the action event triggered by the "Generate Oracle" action.
     */

    @Override
    public void handle(ActionEvent arg0) {
        logger.info("Attempting to edit a Table...");

        boolean isEditable = table.isEditable();

        if (!isEditable) {
            button.setText("Save");
            table.setEditable(true);
        } else {
            button.setText("Edit");
            table.setEditable(false);

            // Log the committed changes
            for (Object tableRow : table.getItems()) {
                TableRow row = (TableRow) tableRow;
                logger.info("Row committed: Name = {}, Type = {}", row.getName(), row.getType());
            }
        }

        // for every table from model(There are 4)
        for (int tableIndex = 0; tableIndex < schemaModel.getTables().size(); tableIndex++) {
            Table tableModel = schemaModel.getTables().get(tableIndex);
            // Get Attributes from Schema Model
            String[][] attributes = tableModel.getAttributes();
            
            // Ensure you only process if the table IDs match
            if (table.getId().equals(tableModel.getName())) {
                for (int i = 0; i < attributes.length; i++) {
                    TableRow row = (TableRow) table.getItems().get(i);
                    logger.debug("Processing table with ID: {}", table.getId());
                    
                    // Set string to attributes
                    String columnName = row.getName();
                    String columnType = row.getType();
                    
                    // Set model attributes to string
                    // Set name to string attribute name
                    tableModel.setAttribute(columnName, i, 0);
                    // Set type to string attribute type
                    tableModel.setAttribute(columnType, i, 1);
                }
            }
        }
        
    }

    // create get method for schema model, make it so that it gets the altred schema
    // model from subcontroller, and the sent it to SchemaController

    public SchemaModel getUpdatedSchemaModel() {
        logger.info("Returning updated SchemaModel...");
        return this.schemaModel;
    }

}
