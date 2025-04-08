package edu.rit.croatia.iste422.g1.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a database table, including its name, attributes, keys, and relationships.
 * <p>
 * This class encapsulates the structure of a database table, such as its name,
 * attributes, primary keys, foreign keys, and relationships with other tables.
 * It provides methods to manipulate and retrieve these properties.
 * </p>
 * 
 * <h2>Main Features:</h2>
 * <ul>
 *   <li>Defines attributes as a two-dimensional array for flexibility.</li>
 *   <li>Supports relationships between tables through foreign keys and related tables.</li>
 *   <li>Offers setter methods to update specific elements of the table.</li>
 * </ul>
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class Table {

    /**
     * The name of the table, representing the identifier or label for the table in the database.
     */
    private String tableName;

    /**
     * A two-dimensional array representing the attributes (columns) of the table.
     * Each attribute may include details such as name and type.
     */
    private String[][] tableAttributes;

    /**
     * A list of column names that serve as the primary keys for the table.
     * Primary keys uniquely identify each row in the table.
     */
    private List<String> primaryKeys;

    /**
     * A list of column names that serve as foreign keys in the table.
     * Foreign keys reference primary keys in other tables to define relationships.
     */
    private List<String> foreignKeys;

    /**
     * A list of foreign key columns in other tables that are related to this table.
     * Represents incoming foreign key references from other tables.
     */
    private List<String> relatedForeignKeys;

    /**
     * A list of names of other tables that are related to this table.
     * Represents the tables referenced or connected through foreign keys.
     */
    private List<String> relatedTables;

    /**
     * Default constructor that initializes the table with default values.
     * <p>
     * Sets empty lists for primary keys, foreign keys, related foreign keys, and related tables.
     * Attributes and table name are set to {@code null}.
     * </p>
     */
    public Table() {
        this.tableName = null;
        this.tableAttributes = null;
        this.primaryKeys = new LinkedList<>();
        this.foreignKeys = new LinkedList<>();
        this.relatedForeignKeys = new LinkedList<>();
        this.relatedTables = new LinkedList<>();
    }

    /**
     * Constructs a table with the specified attributes.
     * 
     * @param tableName         the name of the table
     * @param tableAttributes   a 2D array of attributes for the table
     * @param primaryKeys       a list of primary keys
     * @param foreignKeys       a list of foreign keys
     * @param relatedForeignKeys a list of related foreign keys
     */
    public Table(String tableName, String[][] tableAttributes, List<String> primaryKeys, List<String> foreignKeys, List<String> relatedForeignKeys) {
        this.tableName = tableName;
        this.tableAttributes = tableAttributes;
        this.primaryKeys = primaryKeys != null ? primaryKeys : new LinkedList<>();
        this.foreignKeys = foreignKeys != null ? foreignKeys : new LinkedList<>();
        this.relatedForeignKeys = relatedForeignKeys != null ? relatedForeignKeys : new LinkedList<>();
        this.relatedTables = new LinkedList<>();
    }

    // Setters

    /**
     * Sets the name of the table.
     *
     * @param name the name of the table
     */
    public void setName(String name) {
        this.tableName = name;
    }

    /**
     * Sets a specific attribute of the table.
     *
     * @param name the attribute name
     * @param idx  the row index
     * @param idy  the column index
     */
    public void setAttribute(String name, int idx, int idy) {
        this.tableAttributes[idx][idy] = name;
    }

    /**
     * Sets all attributes of the table.
     *
     * @param attributes a 2D array representing the table's attributes
     */
    public void setAttributes(String[][] attributes) {
        this.tableAttributes = attributes;
    }

    /**
     * Sets a primary key at the specified index.
     *
     * @param name the primary key name
     * @param id   the index in the list
     */
    public void setPrimaryKey(String name, int id) {
        while (primaryKeys.size() <= id) {
            primaryKeys.add(null);
        }
        this.primaryKeys.set(id, name);
    }

    /**
     * Sets a foreign key at the specified index.
     *
     * @param name the foreign key name
     * @param id   the index in the list
     */
    public void setForeignKey(String name, int id) {
        while (foreignKeys.size() <= id) {
            foreignKeys.add(null);
        }
        this.foreignKeys.set(id, name);
    }

    /**
     * Sets a related foreign key at the specified index.
     *
     * @param relatedForeignKey the related foreign key name
     * @param index             the index in the list
     */
    public void setRelatedForeignKey(String relatedForeignKey, int index) {
        if (index < relatedForeignKeys.size()) {
            relatedForeignKeys.set(index, relatedForeignKey);
        } else {
            relatedForeignKeys.add(relatedForeignKey);
        }
    }

    /**
     * Sets a related table at the specified index.
     *
     * @param relatedTable the name of the related table
     * @param index        the index in the list
     */
    public void setRelatedTable(String relatedTable, int index) {
        while (relatedTables.size() <= index) {
            relatedTables.add(null);
        }
        relatedTables.set(index, relatedTable);
    }

    // Getters

    /**
     * Gets the name of the table.
     *
     * @return the table name
     */
    public String getName() {
        return this.tableName;
    }

    /**
     * Gets all attributes of the table.
     *
     * @return a 2D array of table attributes
     */
    public String[][] getAttributes() {
        return this.tableAttributes;
    }

    /**
     * Gets the list of primary keys.
     *
     * @return a list of primary keys
     */
    public List<String> getPrimaryKeys() {
        return this.primaryKeys;
    }

    /**
     * Gets the list of foreign keys.
     *
     * @return a list of foreign keys
     */
    public List<String> getForeignKeys() {
        return this.foreignKeys;
    }

    /**
     * Gets the list of related foreign keys.
     *
     * @return a list of related foreign keys
     */
    public List<String> getRelatedForeignKeys() {
        return this.relatedForeignKeys;
    }

    /**
     * Gets the list of related tables.
     *
     * @return a list of related tables
     */
    public List<String> getRelatedTables() {
        return this.relatedTables;
    }
}