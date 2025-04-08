package edu.rit.croatia.iste422.g1.controller.subcontroller;

import edu.rit.croatia.iste422.g1.model.SchemaModel;
import edu.rit.croatia.iste422.g1.view.SchemaView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Serves as the base class for all subcontrollers in the application.
 * <p>
 * The {@code Subcontroller} class provides a structure for handling specific user actions 
 * by implementing the {@link javafx.event.EventHandler} interface. Subclasses of this 
 * class are responsible for defining the behavior for specific UI events and ensuring 
 * communication between the {@link SchemaModel} and {@link SchemaView}.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Defines the base structure for handling {@link javafx.event.ActionEvent}s.</li>
 *   <li>Provides access to the {@code SchemaModel} and {@code SchemaView} for subclasses.</li>
 *   <li>Encourages modularity and reusability for handling different actions.</li>
 * </ul>
 *
 * <h2>Extension:</h2>
 * <p>
 * Subclasses should override the {@link #handle(ActionEvent)} method to define custom 
 * event-handling logic for specific actions.
 * </p>
 *
 * @see javafx.event.EventHandler
 * @see edu.rit.croatia.iste422.g1.controller.SchemaController
 * @see edu.rit.croatia.iste422.g1.model.SchemaModel
 * @see edu.rit.croatia.iste422.g1.view.SchemaView
 * 
 * @author Orest Brukhal
 * @version 2.8
 */
public class Subcontroller implements EventHandler<ActionEvent> {

    /**
     * The {@link SchemaView} instance that this subcontroller interacts with.
     */
    protected SchemaView schemaView;

    /**
     * The {@link SchemaModel} instance that this subcontroller interacts with.
     */
    protected SchemaModel schemaModel;

    /**
     * Constructs a {@code Subcontroller} with the specified {@link SchemaView} and {@link SchemaModel}.
     *
     * @param schemaView  the {@link SchemaView} instance representing the user interface.
     * @param schemaModel the {@link SchemaModel} instance representing the application data.
     */
    public Subcontroller(SchemaView schemaView, SchemaModel schemaModel) {
        this.schemaView = schemaView;
        this.schemaModel = schemaModel;
    }

    /**
     * Handles the {@link javafx.event.ActionEvent}.
     * <p>
     * This method serves as a placeholder for subclasses to implement specific 
     * behavior for different user actions. Subclasses must override this method 
     * to provide their custom event-handling logic.
     * </p>
     *
     * @param event the {@link javafx.event.ActionEvent} to handle.
     */
    @Override
    public void handle(ActionEvent event) {
        // Placeholder for subclass-specific logic
    }
}