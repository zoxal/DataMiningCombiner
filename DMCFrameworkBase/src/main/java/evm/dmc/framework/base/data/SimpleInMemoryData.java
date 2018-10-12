package evm.dmc.framework.base.data;

import evm.dmc.core.api.AttributeType;
import evm.dmc.core.api.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple and straightforward container for Data.
 */
public class SimpleInMemoryData implements Data {
    private Object[][] data;
    private List<AttributeType> attributeTypes;
    private List<String> attributeNames;

    public SimpleInMemoryData(Object[][] data, List<AttributeType> attributeTypes, List<String> attributeNames) {
        this.data = data;
        this.attributeTypes = attributeTypes;
        this.attributeNames = attributeNames;
    }

    public static SimpleInMemoryData empty() {
        return new SimpleInMemoryData(
            new Object[0][0],
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    public int getAttributesCount() {
        return attributeTypes.size();
    }

    @Override
    public int size() {
        return data[0].length;
    }

    @Override
    public Data selectAttributes(int... indexes) {
        Object[][] newData = new Object[indexes.length][data[0].length];
        List<AttributeType> newAttributeTypes = new ArrayList<>(indexes.length);
        List<String> newAttributeNames = new ArrayList<>(indexes.length);

        for (int i = 0; i < indexes.length; i++) {
            System.arraycopy(data[indexes[i]], 0, newData[i], 0, data[0].length);
            newAttributeTypes.add(attributeTypes.get(indexes[i]));
            newAttributeNames.add(attributeNames.get(indexes[i]));
        }

        return new SimpleInMemoryData(newData, newAttributeTypes, newAttributeNames);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getValue(int row, int column) {
        return (T)(data[row][column]);
    }

    @Override
    public List<AttributeType> getAttributesTypes() {
        return Collections.unmodifiableList(attributeTypes);
    }

    @Override
    public String getAttributeName(int index) {
        return attributeNames.get(index);
    }

    @Override
    public String getName() {
        return "SimpleInMemoryData#" + hashCode();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
