package edu.rit.croatia.iste422.g1.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import edu.rit.croatia.iste422.g1.model.Table;

/**
 * Generates Oracle scripts based on provided table definitions.
 * <p>
 * The {@code OracleGenerator} class implements the {@link ScriptGenerator} interface
 * and generates SQL scripts for creating tables, including their columns, primary keys, and foreign keys.
 * This class also writes the generated script to a file.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 *   <li>Generating Oracle SQL scripts for creating tables based on the provided list of {@link Table} objects.</li>
 *   <li>Writing the generated SQL script to a file for persistence.</li>
 *   <li>Handling any errors related to file writing and providing appropriate logging.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as script generation, file writing success, and errors encountered during the process.
 * </p>
 *
 * @see ScriptGenerator
 * @see Table
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class OracleGenerator implements ScriptGenerator {

    /**
     * Logger instance for recording application events and debugging information.
     */
    private static final Logger logger = LogManager.getLogger(OracleGenerator.class);

    /**
     * Generates an Oracle SQL script for creating tables based on the provided list of {@link Table} objects.
     * <p>
     * This method iterates over the list of tables, generates SQL for creating each table including columns,
     * primary keys, and foreign keys, and then writes the complete script to a file.
     * </p>
     *
     * @param tables the list of {@link Table} objects representing the database tables to generate SQL for.
     * @return the generated SQL script as a {@link String}.
     */
    @Override
    public String generate(List<Table> tables) {
        StringBuilder oracleBuilder = new StringBuilder();

        for (Table table : tables) {
            // Start table creation
            oracleBuilder.append("CREATE TABLE ").append(table.getName()).append(" (\n");

            // Add columns
            String[][] columns = table.getAttributes();
            for (int i = 0; i < columns.length; i++) {
                String columnName = columns[i][0];
                String columnType = columns[i][1];

                // Map common data types to Oracle-specific types if needed
                if (columnType.equalsIgnoreCase("VARCHAR")) {
                    columnType = "VARCHAR2";
                }

                oracleBuilder.append("    ").append(columnName).append(" ").append(columnType);

                // Add comma between columns
                if (i < columns.length - 1 || columns.length > 0) {
                    oracleBuilder.append(",\n");
                }
            }

            // Automatically set the first attribute as the primary key
            if (columns.length > 0) {
                String primaryKeyColumn = columns[0][0]; // First column as primary key
                oracleBuilder.append("    CONSTRAINT ").append(table.getName()).append("_PK PRIMARY KEY (")
                        .append(primaryKeyColumn).append(")");
            }

            // Add foreign keys
            List<String> foreignKeys = table.getForeignKeys();
            List<String> relatedForeignKeys = table.getRelatedForeignKeys();
            List<String> relatedTables = table.getRelatedTables();

            // Check that foreign key information exists and the lists are the same size
            int fkSize = Math.min(foreignKeys.size(), Math.min(relatedForeignKeys.size(), relatedTables.size()));
            for (int i = 0; i < fkSize; i++) {
                oracleBuilder.append(",\n    CONSTRAINT ").append(table.getName()).append("_FK").append(i + 1)
                        .append(" FOREIGN KEY (").append(foreignKeys.get(i))
                        .append(") REFERENCES ").append(relatedTables.get(i))
                        .append(" (").append(relatedForeignKeys.get(i)).append(")");
            }

            oracleBuilder.append("\n);\n\n");
        }

        // Write the SQL script to the scripts/ folder
        String sqlScript = oracleBuilder.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scripts/generated_oracle_script.sql"))) {
            writer.write(sqlScript);
        } catch (IOException e) {
            logger.error("Error writing SQL script to file: ", e);
        }

        return sqlScript;
    }
}
