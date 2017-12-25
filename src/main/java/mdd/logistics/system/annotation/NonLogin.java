package mdd.logistics.system.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonLogin {
    boolean value() default true;
}
