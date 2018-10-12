package evm.dmc.framework.base.data;

import java.io.File;

public interface InFileData {
	String getFileName();
	void setFileName(String fileName);
	
	File getFile();
	void setFileName(File file);
}
