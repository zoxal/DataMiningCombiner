package evm.dmc.web.service;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import evm.dmc.api.model.ProjectModel;
import evm.dmc.api.model.data.DataAttribute;
import evm.dmc.api.model.data.DataStorageModel;
import evm.dmc.api.model.data.MetaData;
import evm.dmc.api.model.datapreview.DataPreview;
import evm.dmc.core.api.back.data.DataSrcDstType;

public interface MetaDataService {
	
	MetaData save(MetaData meta);
	
	MetaData getMetaData(ProjectModel project, Path fullFilePath,
    		DataSrcDstType type, String description, String delimiter, boolean hasHeader);
	
	MetaData persistMetadata(MetaData meta, ProjectModel project);
	
	DataPreview persistPreview(MetaData meta, List<String> previewLines);
	
	MetaData persistAttrubutes(MetaData meta, List<DataAttribute> attributes);
	
	MetaData generateAndPersistAttrubutes(MetaData meta, DataPreview preview);
	
	List<DataAttribute> getDataAttributes(DataPreview preview);

	public static DataStorageModel newDataStorageModel(Path fullFilePath,
			DataSrcDstType type, String delimiter, boolean hasHeader) {
		DataStorageModel storageDesc = new DataStorageModel();

		storageDesc.setStorageType(type);
		storageDesc.setUri(fullFilePath.toUri());
		storageDesc.setDelimiter(delimiter);
		storageDesc.setHasHeader(hasHeader);

		return storageDesc;
	}

	public static MetaData newMetaData(String fileName, String description, DataStorageModel dataStore) {
		MetaData meta = new MetaData();
		meta.setName(fileName);
		meta.setDescription(description);
		meta.setStorage(dataStore);

		return meta;
	}
	
	public static Stream<String> streamLine(String line, String delimiters) {
		return Arrays.stream(StringUtils.tokenizeToStringArray(line, delimiters));
	}
	
	public static List<String> listLine(String line, String delimiters) {
		return Arrays.asList(StringUtils.tokenizeToStringArray(line, delimiters));
	}
}
