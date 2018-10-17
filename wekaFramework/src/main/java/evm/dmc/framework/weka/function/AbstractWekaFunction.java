package evm.dmc.framework.weka.function;

import evm.dmc.core.api.Data;
import evm.dmc.framework.base.AbstractDMCFunction;
import evm.dmc.framework.weka.data.DataInstancesConverter;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWekaFunction extends AbstractDMCFunction {
    private List<Instances> instancesCache;
    private DataInstancesConverter converter = new DataInstancesConverter();

    @Override
    public void setArgs(List<Data> args) {
        instancesCache = new ArrayList<>(args.size());
        super.setArgs(args);
    }

    /**
     * Returns some argument as {@link Instances} object.
     *
     * @param argumentIndex argument index
     * @return              converted object
     */
    protected Instances asInstances(int argumentIndex) {
        if (instancesCache.get(argumentIndex) == null) {
            // maybe worth to move to separate class
            instancesCache.set(
                argumentIndex,
                converter.dataToInstances(arguments.get(argumentIndex))
            );
        }
        return instancesCache.get(argumentIndex);

    }

    protected void saveResult(int index, Instances instancesResult) {
        saveResult(index, converter.instancesToData(instancesResult));
    }
}
