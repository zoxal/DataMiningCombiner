package evm.dmc.weka.function;

import org.springframework.stereotype.Service;

import evm.dmc.core.Function;
import evm.dmc.core.api.Data;
import evm.dmc.core.function.AbstractDMCFunction;
import evm.dmc.weka.WekaFunction;
import evm.dmc.weka.data.WekaData;
import weka.core.Instances;

@Service
public abstract class AbstractWekaFunction extends AbstractDMCFunction<Instances> implements WekaFunction {

	protected Data<Instances> result = null;

	@Override
	public Data<Instances> getResult() {
		return result;
	}

	protected void setResult(WekaData res) {
		result = res;
	}

}
