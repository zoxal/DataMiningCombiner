package evm.dmc.core.api;

import java.util.List;
import java.util.Optional;

/**
 * Contains common methods that every DMC function must implement.
 */
public interface DMCFunction extends HasNameAndDescription {
    /**
     * Stats function execution.
     */
    void execute();

    /**
     * Returns the information about function arguments.
     *
     * @return          list of structures, describing argument types
     */
    List<DataArgumentFormat> getArgsInfo();

    /**
     * Sets function arguments as array.
     *
     * @param args      array of function arguments
     */
    void setArgs(Data... args);

    /**
     * Sets function arguments as list.
     *
     * @param args     list of function arguments
     */
    void setArgs(List<Data> args);

    /**
     * Returns the result of function execution.
     *
     * @return                              execution result
     * @throws      IllegalStateException   if function was not executed yet
     */
    Optional<List<Data>> getResults() throws IllegalStateException;

    /**
     * Predicts format of result values by provided arguments formats. Can be
     * called before execution.
     *
     * @param   args                     arguments formats
     * @return                           results formats
     * @throws  IllegalArgumentException if provided arguments are not supported
     */
    List<DataArgumentFormat> predictResultFormat(List<DataArgumentFormat> args)
            throws IllegalArgumentException;
}
