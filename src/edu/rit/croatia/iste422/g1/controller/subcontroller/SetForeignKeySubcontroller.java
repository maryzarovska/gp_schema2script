package edu.rit.croatia.iste422.g1.controller.subcontroller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.model.Table;
import edu.rit.croatia.iste422.g1.model.TableRow;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;

/**
 * Handles the "Set FK" and "Bind Primary Key" actions within the application.
 * <p>
 * The {@code SetForeignKeySubcontroller} is responsible for binding the
 * selected foreign keys between the tables.
 * This class extends {@link Subcontroller} and interacts with the
 * {@link SchemaView} and
 * {@link SchemaModel}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 * <li>Define the selected foreign key and the selected primary key</li>
 * <li>Bind foreign and primary keys</li>
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
 * 
 * @author Orest Brukhal
 * @version 2.8
 */
public class SetForeignKeySubcontroller extends Subcontroller {

    /**
     * Logger instance for recording application events and debugging information.
     */
    public static final Logger logger = LogManager.getLogger(SetForeignKeySubcontroller.class);

    private TableRow foreignkeyRow = null;
    private TableRow primaryKeyRow = null;
    private int fkTableId = -1;
    private int pkTableId = -1;

    /**
     * Constructs an {@code SetForeignKeySubcontroller} and initializes it with the
     * given view and model.
     *
     * @param schemaView  the {@link SchemaView} instance representing the UI.
     * @param schemaModel the {@link SchemaModel} instance representing the
     *                    application data.
     */
    public SetForeignKeySubcontroller(SchemaView schemaView, SchemaModel schemaModel) {
        super(schemaView, schemaModel);
        logger.info("Set SetForeignKeySubcontroller successfully...");
    }

    /**
     * Gets the current foreign key row.
     *
     * @return the foreign key row.
     */
    public TableRow getForeignKeyRow() {
        return foreignkeyRow;
    }

    /**
     * Sets the foreign key row.
     *
     * @param foreignkeyRow the foreign key row to set.
     */
    public void setForeignKeyRow(TableRow foreignkeyRow) {
        this.foreignkeyRow = foreignkeyRow;
    }

    /**
     * Handles the "Set FK" and "Bind Primary Key" action events.
     * <p>
     * This method defines the selected foreign key row after user presses "Set FK",
     * then defines the second selected row after user presses "Bind to Primary
     * Key",
     * binds those between each other while changing the view and model.
     * </p>
     *
     * @param event the action event triggered by the "Set FK" and "Bind to Primary
     *              Key" actions.
     */
    @Override
    public void handle(ActionEvent event) {
        if (isSetForeignKeyAction()) {
            handleSetForeignKey();
        } else if (isBindPrimaryKeyAction()) {
            handleBindPrimaryKey();
        }
    }

    private boolean isSetForeignKeyAction() {
        return schemaView.getSetFKButton().getText().equals("Set FK");
    }

    private boolean isBindPrimaryKeyAction() {
        return schemaView.getSetFKButton().getText().equals("Bind to Primary Key")
                && primaryKeyRow == null
                && foreignkeyRow != null;
    }

    private void handleSetForeignKey() {
        logger.info("Attempting to set FK...");
        selectForeignKeyRow();
        if (foreignkeyRow != null) {
            schemaView.getSetFKButton().setText("Bind to Primary Key");
        }
        logger.info("Set FK successfully");
    }

    private void selectForeignKeyRow() {
        for (int i = 0; i < schemaView.getTableViews().size(); i++) {
            TableRow anyRow = schemaView.getTableViews().get(i).getSelectionModel().getSelectedItem();
            if (anyRow != null) {
                foreignkeyRow = anyRow;
                fkTableId = i;
            }
        }
    }

    private void handleBindPrimaryKey() {
        logger.info("Attempting to bind to PK...");
        selectPrimaryKeyRow();
        if (primaryKeyRow != null) {
            bindFKtoPK();
            resetAfterBinding();
        }
        logger.info("Binded PK successfully");
    }

    private void selectPrimaryKeyRow() {
        for (int i = 0; i < schemaView.getTableViews().size(); i++) {
            TableRow anyRow = schemaView.getTableViews().get(i).getSelectionModel().getSelectedItem();
            if (anyRow != null && anyRow != foreignkeyRow) {
                primaryKeyRow = anyRow;
                pkTableId = i;
            }
        }
    }

    private void resetAfterBinding() {
        schemaView.setInputView(schemaModel.getTables());
        schemaView.getSetFKButton().setText("Set FK");
        fkTableId = -1;
        pkTableId = -1;
        foreignkeyRow = null;
        primaryKeyRow = null;
    }

    /**
     * This method changes the model accordingly to the FK and PK binding.
     */
    private void bindFKtoPK() {
        logger.info("Attempting to change the model...");
        Table fkTable = schemaModel.getTables().get(fkTableId);
        Table pkTable = schemaModel.getTables().get(pkTableId);
        fkTable.setForeignKey(foreignkeyRow.getName(), 0);
        fkTable.setRelatedForeignKey(primaryKeyRow.getName(), 0);
        fkTable.setRelatedTable(pkTable.getName(), fkTableId);
        pkTable.setForeignKey(primaryKeyRow.getName(), 1);
        pkTable.setRelatedForeignKey(foreignkeyRow.getName(), 1);
        pkTable.setRelatedTable(fkTable.getName(), pkTableId);
        logger.info("Changed model successfully");
    }
}
