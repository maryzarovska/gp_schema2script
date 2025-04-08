package edu.rit.croatia.iste422.g1.generator;

import java.util.List;

import edu.rit.croatia.iste422.g1.model.Table;

/**
 * Defines the contract for generating SQL scripts based on table definitions.
 * <p>
 * The {@code ScriptGenerator} interface provides a method to generate SQL scripts
 * for creating tables, including their columns, primary keys, and foreign keys.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Providing a method to generate SQL scripts for creating tables based on a list of {@link Table} objects.</li>
 * </ul>
 *
 * @see Table
 * 
 * @author Orest Brukhal
 * @version 2.8
 */
public interface ScriptGenerator {

    /**
     * Generates an SQL script for creating tables based on the provided list of {@link Table} objects.
     *
     * @param tables the list of {@link Table} objects representing the database tables to generate SQL for.
     * @return the generated SQL script as a {@link String}.
     */
    public String generate(List<Table> tables);
        
}