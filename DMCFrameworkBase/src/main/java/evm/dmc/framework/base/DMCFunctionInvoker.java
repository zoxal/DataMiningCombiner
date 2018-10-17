package evm.dmc.framework.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wraps the binding between function method and framework object.
 *
 */
public class DMCFunctionInvoker {
    private Object functionObject;
    private Method functionMethod;
//    private List<DMCParameter> parameters;

    public DMCFunctionInvoker(Object functionObject, Method functionMethod) {
        this.functionObject = functionObject;
        this.functionMethod = functionMethod;
//        arguments = new ArrayList<>(functionMethod.getParameterCount());
//        for (Parameter p functionMethod.getParameters()) {
//            parameters.add(DMCParameter.builder().name(p.getName()).)
//        }
    }

    /**
     * Calls wrapped function. It's caller responsibility to ensure, that
     * function arguments was specified correctly and return type will be
     * as expected.
     *
     * @param args                          function argument list
     * @return                              function invocation result
     * @throws InvocationTargetException    see {@link Method#invoke(Object, Object...)}
     * @throws IllegalAccessException       see {@link Method#invoke(Object, Object...)}
     */
    public Object call(Object ... args) throws InvocationTargetException, IllegalAccessException {
        return functionMethod.invoke(functionObject, args);
    }

//    public List<DMCParameter> getParameters() {
//        return Collections.unmodifiableList(parameters);
//    }
}
