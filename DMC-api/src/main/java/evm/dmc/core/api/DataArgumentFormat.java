package evm.dmc.core.api;

import java.util.List;

/**
 * Describes format of some Data object. Used in predictions about arguments and
 * return types of functions.
 * Each <code>DataArgumentFormat</code> instance describes one Data instance
 * format:
 * <ul>
 *     <li>Attributes number and their types</li>
 *     <li>List of allowed <code>Data</code> sizes</li>
 * </ul>
 *
 * @see Data
 * @see DMCFunction
 */
// Or interface?
public class DataArgumentFormat {
    private List<AttributeType> argumentsTypes;
    private List<Integer> allowedSize;

    public DataArgumentFormat(List<AttributeType> argumentsTypes, List<Integer> allowedSize) {
        this.argumentsTypes = argumentsTypes;
        this.allowedSize = allowedSize;
    }

    /**
     * Returns count of attributes presented in current data set, is analog of
     * column in table view.
     *
     * @return number of attributes
     */
    int getAttributesCount() {
        return argumentsTypes.size();
    }

    /**
     * Returns type of single attribute.
     *
     * @param index                         row number
     * @return                              type of selected attribute
     * @throws IndexOutOfBoundsException    if index is out of range
     */
    AttributeType getAttributeType(int index) {
        return argumentsTypes.get(index);
    }

    // Create dedicated class for non-trivial cases (e.g. 2 or 3) in future
    List<Integer> getAllowedSizes() {
        return allowedSize;
    }
}
