
/**
 * The {@code edu.rit.croatia.iste422.g1.parser} package provides classes and interfaces 
 * for parsing database schema files into structured representations.
 * <p>
 * This package facilitates the extraction of table metadata, including attributes, 
 * primary keys, foreign keys, and relationships, from schema files in various formats.
 * It is designed to support multiple parsers and ensure consistent schema representation 
 * across the application.
 * </p>
 *
 * <h2>Package Overview:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.parser.SchemaParser} - An interface that defines the 
 * contract for schema parsers, specifying the {@code parse} method to process schema files.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.parser.XmlParser} - A concrete implementation of 
 * {@code SchemaParser} for parsing XML schema files.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.parser.JsonParser} - A concrete implementation of 
 * {@code SchemaParser} for parsing JSON schema files.</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 * <li>Extensible architecture: Additional schema formats can be supported by implementing 
 * the {@link edu.rit.croatia.iste422.g1.parser.SchemaParser} interface.</li>
 * <li>Integration-ready: Designed to integrate seamlessly with other components of the 
 * system, including schema models and generators.</li>
 * </ul>
 *
 *
 * @see edu.rit.croatia.iste422.g1.parser.SchemaParser
 * @see edu.rit.croatia.iste422.g1.parser.XmlParser
 * @see edu.rit.croatia.iste422.g1.parser.JsonParser
 *
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.parser;
