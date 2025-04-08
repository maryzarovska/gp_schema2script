/**
 * The edu.rit.croatia.iste422.g1.factory package contains factory classes for
 * creating instances of parser and script generators.
 * <p>
 * The classes in this package are responsible for abstracting the instantiation
 * of different implementations of key interfaces, such as
 * {@link edu.rit.croatia.iste422.g1.parser.SchemaParser}
 * and {@link edu.rit.croatia.iste422.g1.generator.ScriptGenerator}.
 * </p>
 *
 * <h2>Package Overview:</h2>
 * <ul>
 * <li>{@link edu.rit.croatia.iste422.g1.factory.ParserFactory} - Factory for
 * creating parser instances
 * for supported schema formats (e.g., JSON, XML).</li>
 * <li>{@link edu.rit.croatia.iste422.g1.factory.ScriptGeneratorFactory} -
 * Factory for creating script generator
 * instances for supported database platforms (e.g., MySQL, Oracle).</li>
 * </ul>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 * <li>The factory classes in this package use the Factory Method design pattern
 * to
 * encapsulate the creation logic for different implementations.</li>
 * <li>They ensure that the code remains open to extension (new formats or
 * platforms)
 * while closed to modification of existing logic, adhering to the Open-Closed
 * Principle.</li>
 * </ul>
 *
 * @see edu.rit.croatia.iste422.g1.factory.ParserFactory
 * @see edu.rit.croatia.iste422.g1.factory.ScriptGeneratorFactory
 * 
 * @version 2.8
 */
package edu.rit.croatia.iste422.g1.factory;
