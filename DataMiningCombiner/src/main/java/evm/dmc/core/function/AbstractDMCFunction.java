package evm.dmc.core.function;

import java.util.Arrays;
import java.util.List;

import evm.dmc.core.data.Data;

/**
 * @author id23cat
 *
 */
public abstract class AbstractDMCFunction<T> implements DMCFunction<T> {

	/**
	 * Name of the function, is used as key in map of functions presented in
	 * Framework collection
	 */
	protected String name;
	/**
	 * Count of the parameters have to be setted as arguments
	 */
	protected Integer argsCount;

	protected List<Data<T>> arguments;

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the paramCount
	 */
	@Override
	public Integer getArgsCount() {
		return argsCount;
	}

	/**
	 * @param paramCount
	 *            the paramCount to set
	 */
	protected void setArgsCount(Integer paramCount) {
		this.argsCount = paramCount;
	}

	@Override
	@SafeVarargs
	final public void setArgs(Data<T>... datas) {
		this.arguments = Arrays.asList(datas).subList(0, this.argsCount);
	}

	@Override
	public void setArgs(List<Data<T>> args) {
		this.arguments = args.subList(0, this.argsCount);
	}

}
