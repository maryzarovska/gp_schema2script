package edu.rit.croatia.iste422.g1.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import edu.rit.croatia.iste422.g1.model.Table;

/**
 * Generates MySQL scripts based on provided table definitions.
 * <p>
 * The {@code MySQLGenerator} class implements the {@link ScriptGenerator}
 * interface
 * and generates SQL scripts for creating tables, including their columns,
 * primary keys, and foreign keys.
 * This class also writes the generated script to a file.
 * </p>
 *
 * <h2>Responsibilities:</h2>
 * <ul>
 * <li>Generating MySQL scripts for creating tables based on the provided list
 * of {@link Table} objects.</li>
 * <li>Writing the generated SQL script to a file for persistence.</li>
 * <li>Handling any errors related to file writing and providing appropriate
 * logging.</li>
 * </ul>
 *
 * <h2>Logging:</h2>
 * <p>
 * Logs events such as script generation, file writing success, and errors
 * encountered during the process.
 * </p>
 *
 * @see ScriptGenerator
 * @see Table
 * 
 * @author Mariia Zarovska, Orest Brukhal, Marko Skific
 * @version 2.8
 */
public class MySQLGenerator implements ScriptGenerator {

    private static final Logger logger = LogManager.getLogger(MySQLGenerator.class);

    @Override
    public String generate(List<Table> tables) {
        StringBuilder sqlBuilder = new StringBuilder();

        for (Table table : tables) {
            generateTableSQL(table, sqlBuilder);
        }

        String sqlScript = sqlBuilder.toString();
        writeToFile(sqlScript);

        return sqlScript;
    }

    private void generateTableSQL(Table table, StringBuilder sqlBuilder) {
        sqlBuilder.append("CREATE TABLE ").append(table.getName()).append(" (\n");

        appendColumns(table, sqlBuilder);
        appendPrimaryKeys(table, sqlBuilder);
        appendForeignKeys(table, sqlBuilder);

        sqlBuilder.append(");\n\n");
    }

    private void appendColumns(Table table, StringBuilder sqlBuilder) {
        String[][] columns = table.getAttributes();
        for (int i = 0; i < columns.length; i++) {
            sqlBuilder.append("    ").append(columns[i][0]) // Column name
                    .append(" ").append(columns[i][1]); // Column type

            if (i < columns.length - 1 || !table.getPrimaryKeys().isEmpty() || !table.getForeignKeys().isEmpty()) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append("\n");
        }
    }

    private void appendPrimaryKeys(Table table, StringBuilder sqlBuilder) {
        List<String> primaryKeys = table.getPrimaryKeys();
        if (!primaryKeys.isEmpty()) {
            sqlBuilder.append("    PRIMARY KEY (")
                    .append(String.join(", ", primaryKeys))
                    .append(")");
            if (!table.getForeignKeys().isEmpty()) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append("\n");
        }
    }

    private void appendForeignKeys(Table table, StringBuilder sqlBuilder) {
        List<String> foreignKeys = table.getForeignKeys();
        List<String> relatedForeignKeys = table.getRelatedForeignKeys();
        List<String> relatedTables = table.getRelatedTables();

        int fkSize = Math.min(foreignKeys.size(),
                Math.min(relatedForeignKeys.size(), relatedTables.size()));

        for (int i = 0; i < fkSize; i++) {
            sqlBuilder.append("    FOREIGN KEY (")
                    .append(foreignKeys.get(i))
                    .append(") REFERENCES ")
                    .append(relatedTables.get(i))
                    .append(" (")
                    .append(relatedForeignKeys.get(i))
                    .append(")");
            if (i < fkSize - 1) {
                sqlBuilder.append(",");
            }
            sqlBuilder.append("\n");
        }
    }

    private void writeToFile(String sqlScript) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scripts/generated_mysql_script.sql"))) {
            writer.write(sqlScript);
            logger.info("SQL script successfully written to file.");
        } catch (IOException e) {
            logger.error("Error writing SQL script to file: ", e);
        }
    }
}