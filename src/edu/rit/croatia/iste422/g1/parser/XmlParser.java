package edu.rit.croatia.iste422.g1.parser;

import edu.rit.croatia.iste422.g1.model.SchemaParsingException;
import edu.rit.croatia.iste422.g1.model.Table;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A parser implementation for XML schema files.
 * <p>
 * This class parses XML files representing database schemas and extracts
 * {@link Table} objects containing table names, attributes, and relationships.
 * </p>
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class XmlParser implements SchemaParser {

    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    /**
     * Parses an XML schema file and extracts table information.
     * @param file the XML file to parse
     * @return a list of {@link Table} objects representing the parsed schema
     * @throws SchemaParsingException if an error occurs during parsing
     */
    @Override
    public ArrayList<Table> parse(File file) throws SchemaParsingException {
        logger.info("Attempting to parse XML file...");
        ArrayList<Table> fileOutput = new ArrayList<>();
        try {
            Document document = createDocument(file);
            Element root = document.getDocumentElement();
            NodeList tableNodes = root.getElementsByTagName("table");

            logger.info("Found {} tables in the XML file", tableNodes.getLength());

            for (int i = 0; i < tableNodes.getLength(); i++) {
                Node tableNode = tableNodes.item(i);
                if (tableNode.getNodeType() == Node.ELEMENT_NODE) {
                    processTableElement((Element) tableNode, fileOutput);
                }
            }
        } catch (SchemaParsingException e) {
            logger.error("SchemaParsingException: ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while parsing the XML file: {}", file.getName(), e);
            throw new SchemaParsingException("Error parsing XML schema", e);
        }
        return fileOutput;
    }

    /**
     * Creates a DOM Document object from the given XML file.
     * 
     * @param file the XML file
     * @return the created {@link Document} object
     * @throws SchemaParsingException if an error occurs during document creation
     */
    private Document createDocument(File file) throws SchemaParsingException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            return document;
        } catch (Exception e) {
            logger.error("Error while creating Document from file: {}", file.getName(), e);
            throw new SchemaParsingException("Error creating XML document from file: " + file.getName(), e);
        }
    }

    /**
     * Processes an individual table element from the XML file.
     * 
     * @param tableElement the {@link Element} representing a table
     * @param fileOutput   the list to which the parsed table is added
     * @throws SchemaParsingException if an error occurs during processing
     */
    private void processTableElement(Element tableElement, ArrayList<Table> fileOutput) throws SchemaParsingException {
        String tableName = getElementTextContent(tableElement, "tableName");
        logger.info("Processing table {}", tableName);

        if (tableName == null || tableName.isEmpty()) {
            throw new SchemaParsingException("Missing table name");
        }

        String[][] columns = extractColumns(tableElement);
        LinkedList<String> primaryKeys = new LinkedList<>();
        LinkedList<String> foreignKeys = new LinkedList<>();
        LinkedList<String> relatedForeignKeys = new LinkedList<>();
        LinkedList<String> relatedTables = new LinkedList<>();

        processRelationships(tableElement, foreignKeys, relatedForeignKeys, relatedTables);

        Table table = createTable(tableName, columns, primaryKeys, foreignKeys, relatedForeignKeys, relatedTables);
        fileOutput.add(table);
        logger.info("Added table {} to output list", table.getName());
    }

    /**
     * Extracts column information from a table element.
     * 
     * @param tableElement the {@link Element} representing a table
     * @return a 2D array of column attributes (name and type)
     */
    private String[][] extractColumns(Element tableElement) {
        NodeList columnsList = tableElement.getElementsByTagName("column");
        String[][] tableAttributes = new String[columnsList.getLength()][2];
        logger.info("Processing {} columns", columnsList.getLength());

        for (int j = 0; j < columnsList.getLength(); j++) {
            Element columnElement = (Element) columnsList.item(j);
            tableAttributes[j][0] = getElementTextContent(columnElement, "name");
            tableAttributes[j][1] = getElementTextContent(columnElement, "type");
        }
        return tableAttributes;
    }

    /**
     * Processes relationships from a table element.
     * 
     * @param tableElement       the {@link Element} representing a table
     * @param foreignKeys        the list to store foreign keys
     * @param relatedForeignKeys the list to store related foreign keys
     * @param relatedTables      the list to store related tables
     */
    private void processRelationships(Element tableElement, LinkedList<String> foreignKeys,
            LinkedList<String> relatedForeignKeys, LinkedList<String> relatedTables) {
        NodeList relationshipsList = tableElement.getElementsByTagName("relationship");

        for (int j = 0; j < relationshipsList.getLength(); j++) {
            Element relationshipElement = (Element) relationshipsList.item(j);
            extractForeignKeys(relationshipElement, foreignKeys, relatedForeignKeys, relatedTables);
        }
    }

    /**
     * Extracts foreign key information from a relationship element.
     * 
     * @param relationshipElement the {@link Element} representing a relationship
     * @param foreignKeys         the list to store foreign keys
     * @param relatedForeignKeys  the list to store related foreign keys
     * @param relatedTables       the list to store related tables
     */
    private void extractForeignKeys(Element relationshipElement, LinkedList<String> foreignKeys,
            LinkedList<String> relatedForeignKeys, LinkedList<String> relatedTables) {
        addElementsToList(relationshipElement.getElementsByTagName("foreignKey"), foreignKeys);
        addElementsToList(relationshipElement.getElementsByTagName("relatedForeignKey"), relatedForeignKeys);
        addElementsToList(relationshipElement.getElementsByTagName("relatedTable"), relatedTables);
    }

    /**
     * Adds text content from a list of XML nodes to a LinkedList.
     * 
     * @param nodeList the list of XML nodes
     * @param list     the LinkedList to store text content
     */
    private void addElementsToList(NodeList nodeList, LinkedList<String> list) {
        for (int k = 0; k < nodeList.getLength(); k++) {
            list.add(nodeList.item(k).getTextContent());
        }
    }

    /**
     * Creates a {@link Table} object using the provided parameters.
     * 
     * @param tableName          the name of the table
     * @param columns            the table's column attributes
     * @param primaryKeys        the table's primary keys
     * @param foreignKeys        the table's foreign keys
     * @param relatedForeignKeys the table's related foreign keys
     * @param relatedTables      the table's related tables
     * @return the created {@link Table} object
     */
    private Table createTable(String tableName, String[][] columns, LinkedList<String> primaryKeys,
            LinkedList<String> foreignKeys, LinkedList<String> relatedForeignKeys,
            LinkedList<String> relatedTables) {
        Table table = new Table();
        table.setName(tableName);
        table.setAttributes(columns);

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

        return table;
    }

    /**
     * Retrieves the text content of a specific child element.
     * 
     * @param element the parent element
     * @param tagName the tag name of the child element
     * @return the text content of the child element, or null if not found
     */
    private String getElementTextContent(Element element, String tagName) {
        Node node = element.getElementsByTagName(tagName).item(0);
        return (node != null) ? node.getTextContent() : null;
    }
}
