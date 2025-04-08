package edu.rit.croatia.iste422.g1.controller.subcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.model.TableRow;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles the "Delete" action within the application.
 * <p>
 * The {@code DeleteTableSubcontroller} is responsible to delete the previously
 * selected TableView fields,
 * trigger the {@link SchemaModel} to refresh the data for that TableView object
 * and {@link SchemaView} to refresh the UI in order to include the newest
 * changes into display.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 * <li>Delete the previously selected fields in TableView.</li>
 * <li>Trigger the SchemaModel to rewrite the data about that TableView's new
 * content.</li>
 * <li>Trigger the SchemaView to refresh the display in order to see the
 * TableView with updated content.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as successful deletions, errors, and user actions for better
 * debugging
 * and monitoring.
 * </p>
 *
 * @see Subcontroller
 * @see SchemaModel
 * @see SchemaView
 *
 * @author Adrian Martinov, Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class DeleteTableSubcontroller extends Subcontroller {

    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(DeleteTableSubcontroller.class);

    /**
     * Constructs an {@code DeleteTableSubcontroller} and initializes it with the
     * given view and model.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the
     *                    application data.
     */
    public DeleteTableSubcontroller(SchemaView schemaView, SchemaModel schemaModel) {
        super(schemaView, schemaModel);
        logger.info("Set DeleteTableSubcontroller successfully...");
    }

    /**
     * Handles the "Delete" action event.
     * <p>
     * This method handles deleting either selected rows from the table or the
     * entire table if no rows are selected.
     * Before performing the deletion, it displays a confirmation popup.
     * </p>
     *
     * @param event the action event triggered by the "Delete" button.
     */
    @Override
    public void handle(ActionEvent event) {
        // Get the source of the event (the button clicked)
        Button pressedButton = (Button) event.getSource();
        String buttonId = pressedButton.getId(); // Assume buttons are named deleteTable1, deleteTable2, etc.

        if (buttonId == null || !buttonId.startsWith("deleteTable")) {
            logger.warn("Unrecognized button pressed.");
            return;
        }

        // Parse the table index from the button ID
        int tableIndex = Integer.parseInt(buttonId.replace("deleteTable", "")) - 1;

        // Ensure the table index is valid
        List<TableView<TableRow>> tableViews = schemaView.getTableViews();
        if (tableIndex < 0 || tableIndex >= tableViews.size()) {
            logger.error("Invalid table index derived from button ID: {}", buttonId);
            return;
        }

        // Get the target TableView
        TableView<TableRow> targetTableView = tableViews.get(tableIndex);
        ObservableList<TableRow> items = targetTableView.getItems();
        List<TableRow> selectedRows = new ArrayList<>(targetTableView.getSelectionModel().getSelectedItems());

        // Confirm deletion with the user
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert
                .setContentText("Are you sure you want to delete the selected rows or clear the entire table?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (!selectedRows.isEmpty()) {
                // Delete the selected rows
                items.removeAll(selectedRows);
                logger.info("Deleted selected rows from TableView at index {}", tableIndex);
            } else {
                // Clear all rows in the target TableView if no rows are selected
                items.clear();
                schemaView.clearFK();
                logger.info("Cleared all rows in TableView at index {}", tableIndex);
            }

            // Refresh the view for the specific TableView
            targetTableView.refresh();
        } else {
            logger.info("Delete action was canceled by the user.");
        }
    }
}
