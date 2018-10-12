package evm.dmc.framework.base.data;

import evm.dmc.api.model.data.DataModel;
import evm.dmc.core.api.AttributeType;
import evm.dmc.core.api.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DataLoader {
    @Autowired
    EntityManager entityManager;

    public Data loadData(String dataId) {
        DataModel dataModel = entityManager.find(DataModel.class, dataId);
        List<AttributeType> attributeTypes = dataModel.getAttributesTypes();
        String dataValue = dataModel.getValue();
        Data data = new SimpleInMemoryData(null, null, null);
        return data;
    }
}
