package evm.dmc.framework.weka.function;

import evm.dmc.api.model.FunctionType;
import evm.dmc.core.api.DataArgumentFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;

import java.util.List;
import java.util.Properties;

@Service(WekaFunctions.STANDARDIZATION)
@PropertySource("classpath:weka.properties")
public class Standardization extends AbstractWekaFilter {
	public static final String name = WekaFunctions.STANDARDIZATION;
	private static final FunctionType TYPE = FunctionType.STANDARDIZATION;
	private Properties functionProperties = new Properties();
	
	@Value("${weka.standart_desc}")
	String description;

	public Standardization() {
		super();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	protected Filter getFilter() {
		Standardize std = new Standardize();
		std.setIgnoreClass(true);
		return std;
	}

	@Override
	public List<DataArgumentFormat> getArgsInfo() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
