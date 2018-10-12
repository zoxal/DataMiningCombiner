package evm.dmc.core.services;

import evm.dmc.api.model.FunctionModel;
import evm.dmc.core.api.DMCDataLoader;
import evm.dmc.core.api.DMCDataSaver;
import evm.dmc.core.api.DMCFramework;
import evm.dmc.core.api.DMCFunction;
import evm.dmc.core.api.exceptions.NoSuchFunctionException;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Set;

public interface FrameworksService extends ApplicationContextAware{
	Set<String> getFrameworksDescriptors();
	
	DMCFramework getFramework(String descriptor);
	
	Map<String, String> getFunctionsDescriptors();
	
	DMCFunction getFunction(String descriptor) throws NoSuchFunctionException;
	
	DMCFunction getFunction(FunctionModel model) throws NoSuchFunctionException;
	
	DMCDataLoader getDataLoaderFunction(String descriptor) throws NoSuchFunctionException;
	
	DMCDataSaver getDataSaverFunction(String descriptor) throws NoSuchFunctionException;
	
	DMCFunction getFunction(String descriptor, String framework) throws NoSuchFunctionException;
	
	DMCFunction getFunction(String descriptor, DMCFramework framework) throws NoSuchFunctionException;


}
