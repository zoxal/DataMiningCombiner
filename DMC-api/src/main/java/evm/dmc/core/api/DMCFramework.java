package evm.dmc.core.api;

import evm.dmc.core.api.exceptions.NoSuchFunctionException;

import java.util.Map;
import java.util.Set;

/**
 * The Interface DMCFramework. Interface describes common methods for using
 * DMCFramework objects
 *
 * @author id23cat
 */
public interface DMCFramework extends HasNameAndDescription {
	/**
	 * Method must return list of short descriptors or identifiers of functions
	 * provided by framework.
	 *
	 * @return the function descriptors
	 */
	Set<String> getFunctionDescriptors();

	/**
	 * Returns list of available data saver names and their classes.
	 *
	 * @return      available savers
	 */
	Map<String, Class<? extends DMCDataSaver>> getSaverDescriptors();

    /**
     * Returns list of available data loader names and their classes
     *
     * @return      available loaders
     */
	Map<String, Class<? extends DMCDataLoader>> getLoaderDescriptors();

	/**
	 * Gets the DMC function.
	 *
	 * @param descriptor                the descriptor
	 * @return                          the DMC function
	 * @throws NoSuchFunctionException  if there is no function
     *                                  with such descriptor
	 */
	DMCFunction getDMCFunction(String descriptor) throws NoSuchFunctionException;

    /**
     * Returns function by it's descriptor and class.
     *
     * @param descriptor                    function descriptor
     * @param type                          desired function class
     * @param <T>                           function type
     * @return                              function with specified class
     * @throws NoSuchFunctionException      if there is no suitable function
     */
	<T extends DMCFunction> T getDMCFunction(String descriptor, Class<T> type) throws NoSuchFunctionException;

    /**
     * Returns data saver by it's descriptor and class.
     *
     * @param descriptor                    saver descriptor
     * @param type                          desired saver class
     * @param <T>                           saver type
     * @return                              saver with specified class
     * @throws NoSuchFunctionException      if there is no suitable saver
     */
	<T extends DMCDataLoader> T getDMCDataSaver(String descriptor,
												Class<T> type) throws NoSuchFunctionException;

    /**
     * Returns data loader by it's descriptor and class.
     *
     * @param descriptor                    loader descriptor
     * @param type                          desired loader class
     * @param <T>                           loader type
     * @return                              loader with specified class
     * @throws NoSuchFunctionException      if there is no suitable loader
     */
	<T extends DMCDataSaver> T getDMCDataLoader(String descriptor,
                                                Class<T> type) throws NoSuchFunctionException;
}
