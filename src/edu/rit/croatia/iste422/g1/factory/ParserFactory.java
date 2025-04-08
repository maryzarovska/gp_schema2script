package edu.rit.croatia.iste422.g1.factory;

import edu.rit.croatia.iste422.g1.parser.JsonParser;
import edu.rit.croatia.iste422.g1.parser.XmlParser;
import edu.rit.croatia.iste422.g1.parser.SchemaParser;

/**
 * Factory class for creating instances of {@link SchemaParser} based on the
 * specified format.
 * <p>
 * This factory supports the creation of parsers for different schema formats
 * such as JSON and XML.
 * The specific parser instance returned depends on the format string provided
 * as input.
 * </p>
 *
 * <h2>Usage Example:</h2>
 * 
 * <pre>{@code
 * SchemaParser parser = ParserFactory.get("json");
 * }</pre>
 *
 * @see SchemaParser
 * @see JsonParser
 * @see XmlParser
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class ParserFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     * This class is intended to be used statically via the {@link #get(String)}
     * method.
     */
    private ParserFactory() {
    }

    /**
     * Returns an instance of {@link SchemaParser} based on the specified format.
     * <p>
     *  Supported formats:
     *  <ul>
     *      <li><b>json</b> - Returns an instance of {@link JsonParser}.</li>
     *      <li><b>xml</b> - Returns an instance of {@link XmlParser}.</li>
     *  </ul>
     *  The format string is case-insensitive.
     * </p>
     *
     * @param format the schema format for which a parser is required (e.g., "json"
     *               or "xml").
     * @return an instance of {@link SchemaParser} corresponding to the specified
     *         format.
     * @throws IllegalArgumentException if the format is not supported.
     * @see JsonParser
     * @see XmlParser
     */
    public static SchemaParser get(String format) {
        switch (format.toLowerCase()) {
            case "json":
                return new JsonParser();
            case "xml":
                return new XmlParser();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}