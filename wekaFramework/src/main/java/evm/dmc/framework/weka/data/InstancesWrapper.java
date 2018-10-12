package evm.dmc.framework.weka.data;

import evm.dmc.core.api.AttributeType;
import evm.dmc.core.api.Data;
import evm.dmc.core.api.HasMultiAttributes;
import evm.dmc.core.api.exceptions.IndexOutOfRange;
import weka.core.Attribute;
import weka.core.Instances;

import java.util.List;

/**
 * Wraps <code>Instances</code> object under <code>Data</code> interface.
 *
 */
public class InstancesWrapper extends Instances implements Data {
    public InstancesWrapper(Instances dataset) {
        super(dataset);
    }

    @Override
    @SuppressWarnings("unchecked")
    // TODO: check, test and refactor
    public <T> T getValue(int row, int column) throws IndexOutOfRange {
        Attribute attribute = attribute(column);
        if (attribute.isNumeric()) {
            return (T)((Double)get(row).value(column));
        } else {
            return (T)get(row).stringValue(column);
        }
    }

    @Override
    public HasMultiAttributes selectAttributes(int... indexes) throws IndexOutOfRange {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AttributeType> getAttributesTypes() throws IndexOutOfRange {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAttributeName(int index) throws IndexOutOfRange {
        return attribute(index).name();
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }
}
