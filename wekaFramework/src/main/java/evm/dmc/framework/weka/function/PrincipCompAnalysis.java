package evm.dmc.framework.weka.function;

import evm.dmc.api.model.FunctionType;
import evm.dmc.core.api.DataArgumentFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;

import java.util.List;
import java.util.Properties;

@Service(WekaFunctions.PCA)
@PropertySource("classpath:weka.properties")
public class PrincipCompAnalysis extends AbstractWekaFilter {
	public final static String MAXARRTIB_PROPERTY_KEY = "Max_attr_count";
	private final static String NAME = WekaFunctions.PCA;
	private static FunctionType type = FunctionType.PCA;
	private Properties functionProperties = new Properties();

	// TODO: use "Attribute" instead of hardcoded:
	private int components = 2;

	@Value("${weka.pca_desc}")
	private String description;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	protected Filter getFilter() {
		PrincipalComponents pca = new PrincipalComponents();
		pca.setMaximumAttributes(components);
		return pca;
	}

    @Override
    public List<DataArgumentFormat> getArgsInfo() {
        // TODO: implement me
        throw new UnsupportedOperationException();
    }
}
