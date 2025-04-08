/**
 * The edu.rit.croatia.iste422.g1.controller package contains the core
 * controller classes responsible for managing the interaction between
 * the model, view, and subcontrollers in the Schema2Script application.
 * <p>
 * The {@code controller} package acts as the central layer of the MVC
 * architecture,
 * coordinating the flow of data and events:
 * </p>
 * 
 * <h2>Main Responsibilities:</h2>
 * <ul>
 * <li>Facilitating communication between the {@code model} and {@code view}
 * layers.</li>
 * <li>Setting up action handlers for user interactions within the UI.</li>
 * <li>Delegating specific tasks to subcontrollers for modularity and separation
 * of concerns.</li>
 * </ul>
 * 
 * <h2>Key Classes:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.controller.SchemaController}:
 * The main controller coordinating the {@code SchemaModel} and
 * {@code SchemaView}.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller}:
 * The base class for all subcontrollers handling specific user actions.</li>
 * </ul>
 * 
 * <h2>Subcontroller Package:</h2>
 * <p>
 * The {@code subcontroller} subpackage contains classes that manage specific
 * user actions,
 * such as opening files, generating scripts, and editing tables. Each
 * subcontroller
 * extends
 * {@link edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller}.
 * </p>
 * 
 * @see edu.rit.croatia.iste422.g1.controller.SchemaController
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller
 * @see edu.rit.croatia.iste422.g1.model
 * @see edu.rit.croatia.iste422.g1.view
 * 
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.controller;