package evm.dmc.framework.base.data;

import evm.dmc.core.api.Data;

public interface FromFileLoadable {
	Data load(String fileName) throws Exception;

}
