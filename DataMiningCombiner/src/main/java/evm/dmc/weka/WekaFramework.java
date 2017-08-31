package evm.dmc.weka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import evm.dmc.api.model.FrameworkType;
import evm.dmc.core.AbstractFramework;
import evm.dmc.core.api.Data;
import evm.dmc.weka.data.WekaData;
import weka.core.Instances;

@Service
@WekaFW
//@PropertySource("classpath:frameworkrepo.properties")
public class WekaFramework extends AbstractFramework {
	static Class CLASS = WekaFunction.class;
	
//	@Value("${frameworkrepo.weka_name}")
	private static final String FRAMEWORK_NAME = "wekaFramework";
	private static final FrameworkType FRAMEWORK_TYPE = FrameworkType.LOCAL;

	public WekaFramework() {
		// super(CLASS);
	}

	@Override
	public void initFramework() {
		super.initFrameworkForType(CLASS);
	}

	public WekaData castToWekaData(Data data) throws ClassCastException {
		WekaData wekaData = data instanceof WekaData ? (WekaData) data : null;
		if (wekaData == null) {
			Instances inst = data.getData() instanceof Instances ? (Instances) data.getData() : null;
			if (inst == null)
				throw new ClassCastException("Unsupported data type");
			wekaData = (WekaData) super.getData(WekaData.class);
			wekaData.setData(inst);
		}
		return wekaData;
	}

	@Override
	protected Class getFunctionClass() {
		return CLASS;
	}
	
	@Override
	protected String getFrameworkName() {
		return FRAMEWORK_NAME;
	}

	@Override
	protected FrameworkType getFrameworkType() {
		return FRAMEWORK_TYPE;
	}

	@Override
	public WekaData castToNativeData(Data data) throws ClassCastException {
		return castToWekaData(data);

	}

}
