package evm.dmc.framework.weka.function;

import evm.dmc.api.model.FunctionType;
import evm.dmc.core.api.AttributeType;
import evm.dmc.core.api.DataArgumentFormat;
import evm.dmc.framework.base.annotations.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import java.util.Collections;
import java.util.List;

@Service(WekaFunctions.NORMALIZATION)
@PropertySource("classpath:weka.properties")
@Function
public class Normalization extends AbstractWekaFilter {
	public static final String name = WekaFunctions.NORMALIZATION;

	@Value("${weka.noraml_desc}")
	private String description;

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
		Normalize norm = new Normalize();
		norm.setIgnoreClass(true);
		return norm;
	}

	// TODO: set correct info
    @Override
    public List<DataArgumentFormat> getArgsInfo() {
        return Collections.singletonList(
            new DataArgumentFormat(
                Collections.singletonList(AttributeType.NUMERIC),
                Collections.singletonList(0)
            )
        );
    }
}
