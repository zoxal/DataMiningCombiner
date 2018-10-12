package evm.dmc.framework.base.data;

import evm.dmc.core.api.AttributeType;

import java.util.ArrayList;
import java.util.List;

// TODO: add test
public class AttributeTypesParser {
    public static List<AttributeType> getAttributeTypes(String attributeTypesList) {
        String[] attributeTypesArray = attributeTypesList.split(",\\s*");
        List<AttributeType> result = new ArrayList<>(attributeTypesArray.length);
        for (String attributeTypeString : attributeTypesArray) {
            result.add(AttributeType.valueOf(attributeTypeString));
        }
        return result;
    }
}
