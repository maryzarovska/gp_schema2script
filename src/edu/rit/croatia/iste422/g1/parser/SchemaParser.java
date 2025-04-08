package edu.rit.croatia.iste422.g1.parser;

import java.io.File;
import java.util.List;

import edu.rit.croatia.iste422.g1.model.SchemaParsingException;
import edu.rit.croatia.iste422.g1.model.Table;

/**
 * Interface for parsing schema files and extracting table information.
 * 
 * <p>
 * Implementations of this interface parse schema files in various formats
 * (e.g., JSON, XML) and return a list of {@link Table} objects representing
 * the schema details.
 * </p>
 * 
 * @author Adrian Martinov, Orest Brukhal
 * @version 2.8
 */
public interface SchemaParser {

    /**
     * Parses a schema file and extracts table information.
     * 
     * @param file the schema file to be parsed
     * @return a list of {@link Table} objects representing the schema
     * @throws SchemaParsingException if an error occurs during parsing
     */
    public List<Table> parse(File file) throws SchemaParsingException;
}
