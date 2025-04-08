/**
 * The {@code edu.rit.croatia.iste422.g1.view} package provides classes and resources 
 * for managing the graphical user interface (GUI) of the Schema-to-Script application.
 * <p>
 * This package is responsible for displaying and interacting with schema data, including
 * table views, relationship definitions, and SQL code generation. It utilizes JavaFX 
 * components to create a dynamic and user-friendly interface.
 * </p>
 *
 * <h2>Package Overview:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.view.SchemaView} - The main class for initializing 
 * and managing the application's GUI, including loading FXML views and handling user interactions.</li>
 * <li>{@code FXML Files} - Layout files that define the structure and styling of the 
 * application's user interface. These are dynamically loaded at runtime by the {@link SchemaView} class.</li>
 * <li>Event Handlers - Methods and listeners embedded within the GUI to respond to user 
 * actions, such as button clicks and menu selections.</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 * <li>Modular Design: Each FXML file corresponds to a specific screen or component, 
 * allowing for clear separation of concerns and easier maintenance.</li>
 * <li>Scalable Architecture: The view package is designed to support the dynamic addition 
 * of tables and components, making it flexible for expanding application requirements.</li>
 * <li>Integration with Models: The views interact seamlessly with the schema models to 
 * display and manipulate data in a structured manner.</li>
 * </ul>
 *

 *
 * @see edu.rit.croatia.iste422.g1.view.SchemaView
 *
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.view;
