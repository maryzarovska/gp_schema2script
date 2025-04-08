package edu.rit.croatia.iste422.g1.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.rit.croatia.iste422.g1.parser.SchemaParser;
import edu.rit.croatia.iste422.g1.factory.ParserFactory;
import edu.rit.croatia.iste422.g1.factory.ScriptGeneratorFactory;
import edu.rit.croatia.iste422.g1.generator.ScriptGenerator;

/**
 * Represents the data model of the Schema2Script application.
 * <p>
 * The {@code SchemaModel} class handles the storage, manipulation, and generation 
 * of schemas and scripts. It integrates with the {@code ParserFactory} and 
 * {@code ScriptGeneratorFactory} to provide a dynamic and flexible approach 
 * for parsing and generating scripts based on user inputs.
 * </p>
 * 
 * <h2>Main Responsibilities:</h2>
 * <ul>
 *   <li>Manages the schema as a list of tables.</li>
 *   <li>Handles schema parsing from files of different formats (e.g., JSON, XML).</li>
 *   <li>Generates database scripts based on the schema and specified type (e.g., MySQL, Oracle).</li>
 * </ul>
 * 
 * @see edu.rit.croatia.iste422.g1.parser.SchemaParser
 * @see edu.rit.croatia.iste422.g1.factory.ParserFactory
 * @see edu.rit.croatia.iste422.g1.factory.ScriptGeneratorFactory
 * @see edu.rit.croatia.iste422.g1.generator.ScriptGenerator
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class SchemaModel {

    /**
     * The list of tables in the schema.
     */
    private List<Table> tables;

    /**
     * The generated database script.
     */
    private String script;

    /**
     * Constructs a new {@code SchemaModel} instance with an empty list of tables.
     */
    public SchemaModel() {
        this.tables = new ArrayList<>();
    }

    /**
     * Parses and uploads the schema from a given file.
     * <p>
     * This method determines the file format based on the file extension and 
     * delegates parsing to the appropriate {@link SchemaParser} retrieved from 
     * the {@link ParserFactory}.
     * </p>
     * 
     * @param file the schema file to be parsed
     * @throws SchemaParsingException if there is an error during schema parsing
     */
    public void schemaUpload(File file) throws SchemaParsingException {
        String fileName = file.getName();
        String format = fileName.substring(fileName.lastIndexOf('.') + 1);

        SchemaParser fileParser = ParserFactory.get(format);
        setSchema(fileParser.parse(file));
    }

    /**
     * Generates a database script based on the current schema and specified type.
     * <p>
     * This method uses the {@link ScriptGeneratorFactory} to retrieve the appropriate 
     * {@link ScriptGenerator} and generate a script from the schema.
     * </p>
     * 
     * @param type the type of script to generate (e.g., "mysql", "oracle")
     */
    public void generateScript(String type) {
        ScriptGenerator scriptGenerator = ScriptGeneratorFactory.get(type);
        setScript(scriptGenerator.generate(tables));
    }

    /**
     * Sets the schema by assigning a list of tables.
     * 
     * @param tables the list of tables to set as the schema
     */
    public void setSchema(List<Table> tables) {
        this.tables = tables;
    }

    /**
     * Updates a specific table in the schema.
     * 
     * @param table the new table to set
     * @param id    the index of the table to replace
     */
    public void setTable(Table table, int id) {
        this.tables.set(id, table);
    }


     /**
     * Gets a specific table in the schema.
     * 
     * @param table the new table to set
     * @param id    the index of the table to replace
     */
    public void getTable(int id) {
        this.tables.get(id);
    }

    /**
     * Retrieves the list of tables in the schema.
     * 
     * @return a list of tables in the schema
     */
    public List<Table> getTables() {
        return this.tables;
    }

    /**
     * Sets the generated database script.
     * 
     * @param script the generated script
     */
    private void setScript(String script) {
        this.script = script;
    }

    /**
     * Retrieves the generated database script.
     * 
     * @return the generated script as a string
     */
    public String getScript() {
        return script;
    }
}