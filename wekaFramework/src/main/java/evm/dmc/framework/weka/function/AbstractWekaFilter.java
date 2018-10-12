package evm.dmc.framework.weka.function;

import evm.dmc.core.api.DataArgumentFormat;
import evm.dmc.framework.weka.exceptions.FilterError;
import weka.core.Instances;
import weka.filters.Filter;

import java.util.List;

public abstract class AbstractWekaFilter extends AbstractWekaFunction {
	protected Filter filter;

	@Override
	public void execute() {
		filter = getFilter();
		Instances inst = asInstances(0);
		try {
			filter.setInputFormat(inst);
			inst = Filter.useFilter(inst, filter);
		} catch (Exception e) {
			throw new FilterError(e);
		}
		saveResult(0,inst);
	}


	@Override
	public List<DataArgumentFormat> predictResultFormat(List<DataArgumentFormat> args) throws IllegalArgumentException {
		return getArgsInfo();
	}

	abstract protected Filter getFilter();
}
