/**
 * The edu.rit.croatia.iste422.g1.controller.subcontroller package contains the core
 * subcontroller classes responsible for managing the interaction between
 * the model, view, and subcontrollers in the Schema2Script application.
 * <p>
 * The {@code subcontroller} package classes act as the handling layer,
 * coordinating the flow of data and events:
 * </p>
 * 
 * <h2>Main Responsibilities:</h2>
 * <ul>
 * <li>Acting as handlers for user interactions within the UI.</li>
 * <li>Executing specific tasks for modularity and separation
 * of concerns.</li>
 * </ul>
 * 
 * <h2>Key Classes:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller}:
 * superclass holding the main functions to handle the user interaction input
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateSubcontroller}:
 * responsible for executing the events that follow after interacting with the Generate MySQL button in view
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateOracleSubcontroller}:
 * responsible for executing the events that follow after interacting with the Generate Oracle button in view
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.OpenFileSubontroller}:
 * responsible for executing the events that follow after interacting with the Open file button in view
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.EditTableViewSubcontroller}:
 * responsible for executing the events that follow after interacting with the Edit buttons of TableView objects in view
 * <li>{@link edu.rit.croatia.iste422.g1.controller.subcontroller.DeleteTableSubcontroller}:
 * responsible for executing the events that follow after interacting with the Delete buttons of TableView objects in view
 * </ul>
 * 
 * <h2>Extention:</h2>
 * <p>
 * The {@code subcontroller} subpackage classes
 * extends
 * {@link edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller}.
 * </p>
 * 
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.Subcontroller
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateSubcontroller
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.GenerateOracleSubcontroller
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.OpenFileSubontroller
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.EditTableViewSubcontroller
 * @see edu.rit.croatia.iste422.g1.controller.subcontroller.DeleteTableSubcontroller
 * 
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.controller.subcontroller;