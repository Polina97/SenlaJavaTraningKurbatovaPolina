
package com.senla.bookshop.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;



@Inherited
@Retention(RUNTIME)
@Target(FIELD)
public @interface ConfigProperty {

	String configName() default "src/configuration.properties";

	String propertyName() default "FILE_PATH";

	String type() default "String";

}
