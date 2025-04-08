/**
 * The edu.rit.croatia.iste422.g1.generator package contains classes for generating SQL scripts
 * for various database platforms.
 * <p>
 * The classes in this package are responsible for generating SQL scripts to create
 * tables, including their columns, primary keys, and foreign keys, for different
 * database platforms such as MySQL and Oracle.
 * </p>
 *
 * <h2>Package Overview:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.generator.MySQLGenerator} - Class for generating MySQL scripts.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.generator.OracleGenerator} - Class for generating Oracle scripts.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.generator.ScriptGenerator} - Interface defining the contract for generating SQL scripts.</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 * <li>The generator classes in this package use the Template Method design pattern to encapsulate
 * the generation logic for different database platforms.</li>
 * <li>They provide a unified interface for generating scripts while allowing flexibility for platform-specific details.</li>
 * </ul>
 *
 * @see edu.rit.croatia.iste422.g1.generator.MySQLGenerator
 * @see edu.rit.croatia.iste422.g1.generator.OracleGenerator
 * @see edu.rit.croatia.iste422.g1.generator.ScriptGenerator
 * 
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.generator;
