package edu.rit.croatia.iste422.g1.parser;

import edu.rit.croatia.iste422.g1.model.SchemaParsingException;
import edu.rit.croatia.iste422.g1.model.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Parses JSON schema files and converts them into {@link Table} objects.
 * <p>
 * This class implements the {@link SchemaParser} interface and provides methods to
 * read, validate, and process the JSON structure to produce {@link Table} objects
 * with attributes, keys, and relationships.
 * </p>
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class JsonParser implements SchemaParser {

    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    /**
     * Parses a JSON schema file and extracts a list of {@link Table} objects.
     *
     * @param file the JSON file to parse
     * @return a list of parsed {@link Table} objects
     * @throws SchemaParsingException if an error occurs during parsing
     */
    @Override
    public ArrayList<Table> parse(File file) throws SchemaParsingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Attempting to parse JSON file...");
        ArrayList<Table> fileOutput = new ArrayList<>();

        try {
            List<Map<String, Object>> tables = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
            });
            logger.info("Found {} tables in the JSON file", tables.size());

            // Parse each table data and convert it to Table objects
            for (Map<String, Object> tableData : tables) {
                Table table = parseTable(tableData);
                fileOutput.add(table);
                logger.info("Added table {} to output list", table);
            }

        } catch (Exception e) {
            logger.info("Error occurred while parsing the JSON file {}", file.getName());
            throw new SchemaParsingException("Error parsing JSON schema", e);
        }

        return fileOutput;
    }

    /**
     * Parses a table represented as a Map and converts it into a {@link Table}
     * object.
     *
     * @param tableData the data of the table as a map
     * @return the parsed {@link Table} object
     * @throws SchemaParsingException if required fields are missing or invalid
     */
    private Table parseTable(Map<String, Object> tableData) throws SchemaParsingException {
        if (!tableData.containsKey("tableName") || !tableData.containsKey("columns")) {
            logger.info("Missing required fields for table {}", tableData);
            throw new SchemaParsingException("Missing required fields");
        }

        Table table = new Table();
        String tableName = (String) tableData.get("tableName");
        table.setName(tableName);
        logger.info("Processing table {}", tableName);

        List<Map<String, String>> columns = getColumns(tableData);
        if (columns != null) {
            processColumns(table, columns);
        } else {
            logger.info("No columns defined for table {}", tableName);
        }

        processRelationships(tableData, table, tableName);

        return table;
    }

    /**
     * Extracts the list of columns from the table data.
     *
     * @param tableData the data of the table as a map
     * @return a list of columns, or an empty list if no columns are found
     */
    private List<Map<String, String>> getColumns(Map<String, Object> tableData) {
        Object columnsObj = tableData.get("columns");

        if (columnsObj instanceof List<?>) {
            List<?> list = (List<?>) columnsObj;

            if (!list.isEmpty() && list.get(0) instanceof Map<?, ?>) {
                List<Map<String, String>> columns = new ArrayList<>();
                for (Object item : list) {
                    if (item instanceof Map<?, ?>) {
                        @SuppressWarnings("unchecked")
                        Map<String, String> map = (Map<String, String>) item;
                        columns.add(map);
                    }
                }
                return columns;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Processes the columns and populates the {@link Table} object with attributes.
     *
     * @param table   the {@link Table} object to populate
     * @param columns the list of columns to process
     */
    private void processColumns(Table table, List<Map<String, String>> columns) {
        logger.info("Processing {} columns for table {}", columns.size(), table.getName());
        String[][] tableAttributes = new String[columns.size()][2];
        table.setAttributes(tableAttributes);

        // Process each column and populate the table's attributes
        for (int i = 0; i < columns.size(); i++) {
            Map<String, String> column = columns.get(i);
            String columnName = column.get("name");
            String columnType = column.get("type");

            if (columnName != null && columnType != null) {
                logger.info("Processing column {} {}", columnName, columnType);
                table.setAttribute(columnName, i, 0);
                table.setAttribute(columnType, i, 1);
            } else {
                logger.warn("Column at index {} is missing required fields: {}", i, column);
            }
        }
    }

    /**
     * Processes relationships for the table and populates the {@link Table} object
     * with keys and related data.
     *
     * @param tableData the data of the table as a map
     * @param table     the {@link Table} object to populate
     * @param tableName the name of the table
     */
    private void processRelationships(Map<String, Object> tableData, Table table, String tableName) {
        Object relationshipsObj = tableData.get("relationships");

        if (!(relationshipsObj instanceof List<?>)) {
            logger.info("No relationships defined for table {}", tableName);
            return;
        }

        List<?> relList = (List<?>) relationshipsObj;
        if (relList.isEmpty() || !(relList.get(0) instanceof Map<?, ?>)) {
            logger.info("No relationships defined for table {}", tableName);
            return;
        }

        List<Map<String, String>> relationships = extractRelationships(relList);
        logger.info("Processing {} relationships for table {}", relationships.size(), tableName);

        LinkedList<String> primaryKeys = new LinkedList<>();
        LinkedList<String> foreignKeys = new LinkedList<>();
        LinkedList<String> relatedForeignKeys = new LinkedList<>();
        LinkedList<String> relatedTables = new LinkedList<>();

        processRelationshipTypes(relationships, foreignKeys, relatedForeignKeys, relatedTables);
        setTableKeys(table, primaryKeys, foreignKeys, relatedForeignKeys, relatedTables);
    }

    /**
     * Extracts relationships from the provided list.
     *
     * @param relList the list of relationships
     * @return a list of relationships as maps
     */
    private List<Map<String, String>> extractRelationships(List<?> relList) {
        List<Map<String, String>> relationships = new ArrayList<>();

        for (Object item : relList) {
            if (item instanceof Map<?, ?>) {
                @SuppressWarnings("unchecked")
                Map<String, String> relationshipMap = (Map<String, String>) item;
                relationships.add(relationshipMap);
            }
        }

        return relationships;
    }

    /**
     * Processes different relationship types and populates key lists.
     *
     * @param relationships      the list of relationships to process
     * @param foreignKeys        the list of foreign keys to populate
     * @param relatedForeignKeys the list of related foreign keys to populate
     * @param relatedTables      the list of related tables to populate
     */
    private void processRelationshipTypes(List<Map<String, String>> relationships,
            LinkedList<String> foreignKeys,
            LinkedList<String> relatedForeignKeys,
            LinkedList<String> relatedTables) {
        for (Map<String, String> relationship : relationships) {
            String relationshipType = relationship.get("relationshipType");

            if (isValidRelationshipType(relationshipType)) {
                foreignKeys.add(relationship.get("foreignKey"));
                relatedForeignKeys.add(relationship.get("relatedForeignKey"));
                relatedTables.add(relationship.get("relatedTable"));
            }
        }
    }

    /**
     * Validates whether a relationship type is supported.
     *
     * @param relationshipType the type of the relationship
     * @return true if the relationship type is valid, false otherwise
     */
    private boolean isValidRelationshipType(String relationshipType) {
        return "one-to-many".equals(relationshipType) ||
                "many-to-one".equals(relationshipType) ||
                "many-to-many".equals(relationshipType);
    }

    /**
     * Sets the keys and related data in the {@link Table} object.
     *
     * @param table              the {@link Table} object to populate
     * @param primaryKeys        the list of primary keys
     * @param foreignKeys        the list of foreign keys
     * @param relatedForeignKeys the list of related foreign keys
     * @param relatedTables      the list of related tables
     */
    private void setTableKeys(Table table,
            LinkedList<String> primaryKeys,
            LinkedList<String> foreignKeys,
            LinkedList<String> relatedForeignKeys,
            LinkedList<String> relatedTables) {
        for (int i = 0; i < primaryKeys.size(); i++) {
            table.setPrimaryKey(primaryKeys.get(i), i);
        }

        for (int i = 0; i < foreignKeys.size(); i++) {
            table.setForeignKey(foreignKeys.get(i), i);
        }

        for (int i = 0; i < relatedForeignKeys.size(); i++) {
            table.setRelatedForeignKey(relatedForeignKeys.get(i), i);
        }

        for (int i = 0; i < relatedTables.size(); i++) {
            table.setRelatedTable(relatedTables.get(i), i);
        }
    }
}
