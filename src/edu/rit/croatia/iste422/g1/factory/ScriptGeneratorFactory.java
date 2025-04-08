package edu.rit.croatia.iste422.g1.factory;

import edu.rit.croatia.iste422.g1.generator.MySQLGenerator;
import edu.rit.croatia.iste422.g1.generator.OracleGenerator;
import edu.rit.croatia.iste422.g1.generator.PostgreSQLGenerator;
import edu.rit.croatia.iste422.g1.generator.ScriptGenerator;

/**
 * Factory class for creating instances of {@link ScriptGenerator} based on the
 * specified database format.
 * <p>
 * This factory supports the creation of script generators for different
 * database platforms such as MySQL and Oracle. The specific generator
 * instance returned depends on the format string provided as input.
 * </p>
 *
 * <h2>Usage Example:</h2>
 * 
 * <pre>{@code
 * ScriptGenerator generator = ScriptGeneratorFactory.get("mysql");
 * }</pre>
 *
 * @see ScriptGenerator
 * @see MySQLGenerator
 * @see OracleGenerator
 * 
 * @author Mariia Zarovska, Orest Brukhal
 * @version 2.8
 */
public class ScriptGeneratorFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     * This class is intended to be used statically via the {@link #get(String)}
     * method.
     */
    private ScriptGeneratorFactory() {
    }

    /**
     * Returns an instance of {@link ScriptGenerator} based on the specified
     * database format.
     * <p>
     * Supported formats:
     * <ul>
     * <li><b>mysql</b> - Returns an instance of {@link MySQLGenerator}.</li>
     * <li><b>oracle</b> - Returns an instance of {@link OracleGenerator}.</li>
     * </ul>
     * The format string is case-insensitive.
     * </p>
     *
     * @param format the database format for which a script generator is required
     *               (e.g., "mysql" or "oracle").
     * @return an instance of {@link ScriptGenerator} corresponding to the specified
     *         format.
     * @throws IllegalArgumentException if the format is not supported.
     * @see MySQLGenerator
     * @see OracleGenerator
     */
    public static ScriptGenerator get(String format) {
        switch (format.toLowerCase()) {
            case "mysql":
                return new MySQLGenerator();
            case "oracle":
                return new OracleGenerator();
            case "postgresql":
                return new PostgreSQLGenerator();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
