package evm.dmc.framework.weka.function;

import evm.dmc.api.model.FunctionDstModel;
import evm.dmc.api.model.FunctionType;
import evm.dmc.core.api.CSVSaver;
import evm.dmc.core.api.DMCDataSaver;
import evm.dmc.core.api.Data;
import evm.dmc.core.api.back.data.DataSrcDstType;
import evm.dmc.core.api.exceptions.StoreDataException;
import evm.dmc.framework.base.exceptions.UncheckedStoringException;
import evm.dmc.framework.weka.WekaFunction;
import evm.dmc.framework.weka.data.DataInstancesConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import weka.core.converters.AbstractSaver;

import java.io.File;
import java.io.IOException;

@Service(WekaFunctions.CSVSAVER)
@PropertySource("classpath:weka.properties")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WekaCSVSave implements CSVSaver, WekaFunction {
	private static final String NAME = WekaFunctions.CSVSAVER;
	
	private static FunctionType type = FunctionType.CSV_DATADESTINATION;
	private FunctionDstModel model = FunctionDstModel.dstBuilder().build();

	private File destination = null;
	private Data save = null;

	@Value("${weka.csvsave_desc}")
	String description;


	@Override
	public void save(Data data) throws ClassCastException, StoreDataException {
		save = data;
		try {
			execute();
		} catch (UncheckedStoringException e) {
			throw new StoreDataException("Trying to save data to csv failed", e);
		}
	}

	@Override
	public CSVSaver setDestination(String filename) {
		destination = new File(filename);
		model.setDestination(filename);
		model.setTypeSrcDst(DataSrcDstType.LOCAL_FS);
		return this;
	}

	@Override
	public CSVSaver setDestination(File file) {
		this.destination = file;
		return this;
	}

	public void execute() throws ClassCastException, UncheckedStoringException {

		AbstractSaver saver = new weka.core.converters.CSVSaver();
		if (destination == null)
			throw new UncheckedStoringException("Destination file does not declred");
		try {
			saver.setFile(destination);
			saver.setInstances(new DataInstancesConverter().dataToInstances(save));
			saver.writeBatch();
		} catch (IOException e) {
			throw new UncheckedStoringException(e);
		}
	}
}
