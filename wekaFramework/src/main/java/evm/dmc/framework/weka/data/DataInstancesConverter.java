package evm.dmc.framework.weka.data;

import evm.dmc.core.api.AttributeType;
import evm.dmc.core.api.Data;
import evm.dmc.core.api.HasMultiAttributes;
import evm.dmc.framework.base.data.SimpleInMemoryData;
import org.apache.commons.lang3.tuple.Pair;
import weka.core.*;

import java.util.*;

/**
 * Converts Data objects to Instances and vice versa.
 * Supports following converting to the following Weka attribute types:
 * <li>
 *     <ul>{@link Attribute#NUMERIC}</ul>
 *     <ul>{@link Attribute#STRING}</ul>
 * </li>
 */
// TODO: Create wrapper for Instance under Data interface to avoid
// creation of new Data object from instances
public class DataInstancesConverter {
    private final static Map<AttributeType, Integer> directMap;
    private final static Map<Integer, AttributeType> reverseMap;

    static {
        directMap = new HashMap<>();
        directMap.put(AttributeType.NUMERIC, Attribute.NUMERIC);

        reverseMap = new HashMap<>();
        reverseMap.put(Attribute.NUMERIC, AttributeType.NUMERIC);
    }

    /**
     * Transforms DMC data object to <code>Instances</code>.
     *
     * @param data      DMC data object
     * @return          instances object
     */
    // TODO: write tests
    public Instances dataToInstances(Data data) {
        ArrayList<Attribute> attributes = getWekaAttributes(data);
        Instances instances = new Instances(data.getName(), attributes, data.size());
        for (int i = 0; i < data.size(); i++) {
            Instance instance = new DenseInstance(attributes.size());
            for (int j = 0; j < attributes.size(); j++) {
                instance.setValue(j, data.getValue(i, j));
            }
            instances.add(instance);
        }

        return instances;
    }

    /**
     * Transforms Weka <code>Instances</code> to DMC data object.
     *
     * @param instances     instances object
     * @return              DMC data object
     */
    // TODO: test
    public Data instancesToData(Instances instances) {
        if (instances.isEmpty()) {
            return SimpleInMemoryData.empty();
        }
        Object[][] data = new Object[instances.numAttributes()][instances.size()];
        Instance firstInstance = instances.get(0);
        Pair<List<AttributeType>, List<String>> dataAttributes = getDataAttributes(firstInstance);
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            for (int j = 0; j < instance.numAttributes(); j++) {
                data[j][i] = instance.value(j);
            }
        }
        return new SimpleInMemoryData(data, dataAttributes.getLeft(), dataAttributes.getRight());
    }

    /**
     * Creates list of attributes in Weka format from DMC data object.
     *
     * @param data      DMC data object
     * @return          list of Weka attributes
     */
    private ArrayList<Attribute> getWekaAttributes(HasMultiAttributes data) {
        List<AttributeType> attributesTypes = data.getAttributesTypes();
        ArrayList<Attribute> attributes = new ArrayList<>(attributesTypes.size());
        for (int i = 0; i < attributesTypes.size(); i++) {
            switch (attributesTypes.get(i)) {
                case INT: {}
                case FLOAT: {}
                case NUMERIC: {
                    attributes.add(new Attribute(data.getAttributeName(i)));
                    break;
                }
                case STRING: {
                    attributes.add(new Attribute(data.getAttributeName(i), true));
                    break;
                }
                // TODO: Support other types
            }
        }
        return attributes;
    }

    /**
     * Extracts data attributes in DMC format from Weka's instance.
     *
     * @param instance      instance object
     * @return              pair of lists of attributes types and names
     */
    private Pair<List<AttributeType>, List<String>> getDataAttributes(Instance instance) {
        List<AttributeType> attributesTypes = new ArrayList<>(instance.numAttributes());
        List<String> attributesNames = new ArrayList<>(instance.numAttributes());
        for (int i = 0; i < instance.numAttributes(); i++) {
            attributesTypes.add(reverseMap.get(instance.attribute(i).type()));
            attributesNames.add(instance.attribute(i).name());
        }
        return Pair.of(attributesTypes, attributesNames);
    }
}
