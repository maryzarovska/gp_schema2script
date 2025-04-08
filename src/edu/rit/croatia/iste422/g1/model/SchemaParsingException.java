package edu.rit.croatia.iste422.g1.model;

/**
 * Exception class to represent errors encountered during schema parsing.
 * <p>
 * This custom exception extends {@link Exception} and is used to provide
 * detailed error information when issues occur during the parsing of schema files.
 * It supports error messages, underlying causes, or both.
 * </p>
 * 
 * <h2>Main Use Cases:</h2>
 * <ul>
 *   <li>To signal problems in the schema parsing process, such as invalid file formats or syntax errors.</li>
 *   <li>To encapsulate and propagate the root cause of parsing issues.</li>
 * </ul>
 * 
 * @see Exception
 * 
 * @author Orest Brukhal
 * @version 2.8
 */
public class SchemaParsingException extends Exception {

    /**
     * Constructs a new {@code SchemaParsingException} with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public SchemaParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SchemaParsingException} with the specified cause.
     * 
     * @param cause the underlying cause of the exception
     */
    public SchemaParsingException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code SchemaParsingException} with the specified detail message
     * and cause.
     * 
     * @param message the detail message explaining the reason for the exception
     * @param cause   the underlying cause of the exception
     */
    public SchemaParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}