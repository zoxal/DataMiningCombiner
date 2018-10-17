package evm.dmc.framework.base;

import evm.dmc.core.api.DMCFunction;
import evm.dmc.core.api.Data;
import evm.dmc.core.api.DataArgumentFormat;

import java.util.*;

/**
 * Base implementation of {@link DMCFunction interface}.
 *
 */
public abstract class AbstractDMCFunction implements DMCFunction {
	protected List<Data> arguments;
	protected List<Data> results;

	@Override
	public void setArgs(Data... args) {
        setArgs(Arrays.asList(args));
	}

	@Override
	public void setArgs(List<Data> args) {
        validateArguments(args);
		this.arguments = args;
	}

	/**
	 * Default straightforward implementation, that checks that arguments are
     * specified correctly.
	 *
	 * @throws      IllegalArgumentException    if arguments are invalid
	 */
	protected void validateArguments(List<Data> arguments) {
        List<DataArgumentFormat> argumentsFormats = new ArrayList<>(arguments.size());
        for (Data argument : arguments) {
            argumentsFormats.add(new DataArgumentFormat(
                argument.getAttributesTypes(),
                Collections.singletonList(argument.size())
            ));
        }
        predictResultFormat(argumentsFormats);
	}

    @Override
    public Optional<List<Data>> getResults() throws IllegalStateException {
        return Optional.ofNullable(results);
    }

    protected void saveResult(int index, Data result) {
	    if (results == null) {
	        results = new ArrayList<>(getArgsInfo().size());
        }
        results.set(index, result);
    }
}
