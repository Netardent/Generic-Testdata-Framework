package org.robot.gtf.builder;

import java.io.IOException;
import java.util.Map;

import org.robot.gtf.runtime_configuration.BuilderConfiguration;
import org.robot.gtf.runtime_configuration.Metadata;

/**
 * Interface for the Builder classes. 
 * @author thomas.jaspers
 */
public interface IBuilder {

	/**
	 * Main method to start building a concrete testsuite file.
	 * @param builderConfiguration Configuration of the Builder
	 * @param metadataMap The metadata
	 * @return Complete String that represents a "builded" testsuite
	 * @throws IOException 
	 */
	public String build(BuilderConfiguration builderConfiguration, Map<String, Metadata> metadataMap) throws BuilderException;
}
