package edu.rit.croatia.iste422.g1.controller.subcontroller;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the "Save As" functionality, allowing the user to save
 * the generated SQL script to a file of their choice.
 */
public class SaveAsSubcontroller implements EventHandler<ActionEvent> {
    private final SchemaView view;
    private final SchemaModel model;

    /**
     * Constructs a SaveAsSubcontroller with the provided view and model.
     *
     * @param view  the application's view.
     * @param model the application's model.
     */
    public SaveAsSubcontroller(SchemaView view, SchemaModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        // Open the file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save SQL Script");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL Files", "*.sql"));

        // Dynamically retrieve the Window from the MenuItem's parent popup
        Window window = view.getSaveFileButton().getParentPopup().getOwnerWindow();

        // Show the Save As dialog
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                // Retrieve the generated SQL script from the model
                String sqlScript = model.getScript();

                // Write the script to the selected file
                writer.write(sqlScript);
            } catch (IOException e) {
                // Show an error message if writing fails
                view.showError("Error saving file: " + e.getMessage());
            }
        }
    }
}
