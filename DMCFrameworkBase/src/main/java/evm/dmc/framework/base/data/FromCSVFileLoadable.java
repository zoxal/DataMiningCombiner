package evm.dmc.framework.base.data;

import evm.dmc.core.api.Data;

public interface FromCSVFileLoadable extends FromFileLoadable {

	Data load(String fileNamae, int classIndex) throws Exception;

	Data load(String fileNamae, int classIndex, String separator) throws Exception;

}
