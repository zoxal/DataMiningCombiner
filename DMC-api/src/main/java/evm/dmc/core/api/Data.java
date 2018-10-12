package evm.dmc.core.api;

import evm.dmc.core.api.back.data.DataModel;
import evm.dmc.core.api.exceptions.IndexOutOfRange;

/**
 * General interface for any DMC data.
 * Can be treated as table.
 *
 * @see DataModel
 */
// TODO: iterable?
public interface Data extends HasMultiAttributes, HasNameAndDescription {
    /**
     * Returns value of single item in data set.
     *
     * @param row               row number
     * @param column            attribute index
     * @return                  double value of item
     * @throws IndexOutOfRange  if row or column is out of range
     */
    <T> T getValue(int row, int column) throws IndexOutOfRange;

    /**
     * Returns number of row in data.
     *
     * @return                  number of rows.
     */
    int size();
}
