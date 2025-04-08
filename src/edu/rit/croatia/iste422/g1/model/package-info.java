/**
 * The {@code edu.rit.croatia.iste422.g1.model} package contains classes that define 
 * the structure and metadata of database schemas, tables, and their components.
 * <p>
 * This package provides the foundation for working with database-related entities, 
 * offering tools to represent, validate, and manipulate schema information programmatically.
 * </p>
 *
 * <h2>Package Overview:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.model.SchemaModel} - Represents a database schema, 
 * allowing for schema parsing and SQL script generation.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.model.Table} - Encapsulates metadata about a database 
 * table, including attributes, primary keys, foreign keys, and related tables.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.model.TableRow} - Represents individual rows in a table, 
 * with name and type information.</li>
 * <li>{@link edu.rit.croatia.iste422.g1.model.SchemaParsingException} - Custom exception 
 * for handling schema parsing errors.</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 * <li>This package uses a modular design to ensure extensibility and adaptability to various 
 * schema formats and database platforms.</li>
 * <li>Classes within this package are designed to integrate seamlessly with parsers, 
 * generators, and other components of the system.</li>
 * <li>Provides robust validation mechanisms for schema attributes and relationships, 
 * ensuring data integrity and consistency.</li>
 * </ul>
 *
 * @see edu.rit.croatia.iste422.g1.model.SchemaModel
 * @see edu.rit.croatia.iste422.g1.model.Table
 * @see edu.rit.croatia.iste422.g1.model.TableRow
 * @see edu.rit.croatia.iste422.g1.model.SchemaParsingException
 *
 * @version 2.8
 */

package edu.rit.croatia.iste422.g1.model;
