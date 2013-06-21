package org.robot.gtf.builder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import org.robot.gtf.to.MappingTO;

/**
 * Interface for the Builder classes.
 * 
 * @author thomas.jaspers
 */
public interface IBuilder {

	/**
	 * Main method to start building a concrete testsuite file.
	 * 
	 * @param builderConfiguration
	 *            Configuration of the Builder
	 * @param metadataMap
	 *            The metadata
	 * @param mappingInfo
	 *            Generic mapping information to replace in templates
	 * @return Complete String that represents a "builded" testsuite
	 * @throws IOException
	 */
	public String build(BuilderConfiguration builderConfiguration, Map<String, Metadata> metadataMap,
			List<MappingTO> mappingInfo) throws BuilderException;
}
