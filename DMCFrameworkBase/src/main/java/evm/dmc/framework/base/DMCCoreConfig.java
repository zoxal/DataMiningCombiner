package evm.dmc.framework.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import evm.dmc.core.arithmetic.AbstractArithmeticFunction.ArithmeticContext;

@Configuration
// @ComponentScan(basePackages="evm.dmc.core.arithmetic")
// @ComponentScan(basePackageClasses={/*AbstractArithmeticFunction.class,
// ArithmeticContext.class*/})
@ComponentScan( basePackages="evm.dmc.core, evm.dmc.weka")
//@Import({evm.dmc.weka.DMCWekaConfig.class})
public class DMCCoreConfig {

}
