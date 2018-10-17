package evm.dmc.framework.weka;

import evm.dmc.framework.base.annotations.FW;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR,
		java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@FW("WekaDMCFramework")
public @interface WekaFW {

}
