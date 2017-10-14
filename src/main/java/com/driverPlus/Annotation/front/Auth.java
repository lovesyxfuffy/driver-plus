package com.driverPlus.Annotation.front;

import java.lang.annotation.*;

/**
 * Created by yujingyang on 2017/10/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface Auth {

}
