package evm.dmc.core.api;

import evm.dmc.core.api.exceptions.IndexOutOfRange;

import java.util.List;

/**
 * Indicates that implementation contains multiple attributes, such as
 * several columns in the data set.
 * 
 * @author id23cat
 */
public interface HasMultiAttributes {
	/**
	 * Returns new <code>HasMultiAttributes</code> instance contained attributes
	 * selected by set of
	 * indexes from current multiAttribute data set.
	 * 
	 * @param indexes   indexes of attributes to include
	 * @return          new data instance
	 */
	HasMultiAttributes selectAttributes(int ... indexes) throws IndexOutOfRange;

	/**
	 * Returns type of single attribute.
	 *
	 * @return                  type of selected attribute
	 * @throws IndexOutOfRange  if index is out of range
	 */
	List<AttributeType> getAttributesTypes() throws IndexOutOfRange;

    /**
     * Returns name of single attribute.
     *
     * @param index             row number
     * @return                  name of selected attribute
     * @throws IndexOutOfRange  if index is out of range
     */
    String getAttributeName(int index) throws IndexOutOfRange;

//  TODO: move to separate class
//	/**
//	 * Returns the statistics of attribute at specified index.
//	 *
//	 * @param column            attribute index
//	 * @return                  attribute statistics
//	 * @throws IndexOutOfRange  if index is out of range
//	 */
//	Statistics getAttributeStatistics(int column) throws IndexOutOfRange;

//  TODO: move to separate class
//	/**
//	 * Uses as alternative to default getAttributeStatistics(int column) when
//	 * need to set number of bins in elements frequency for numeric attribute
//	 *
//	 * @param column            attribute index
//	 * @param bins              the number of bins (must be at least 1)
//	 * @return
//	 * @throws IndexOutOfRange  if index is out of range
//	 */
//	Statistics getAttributeStatistics(int column, int bins) throws IndexOutOfRange;
}
