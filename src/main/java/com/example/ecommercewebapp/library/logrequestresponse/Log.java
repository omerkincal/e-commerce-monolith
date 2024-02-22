package com.example.ecommercewebapp.library.logrequestresponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @apiNote Metod veya sınıf için kullanılabilir,
 * metod için request ve response değerlerini loglar,
 * sınıf için sınıfa ait tüm metodların request ve responselarını loglar
 *
 * @see LogRequestAndResponseAspect
 *
 * @author Ahmet Emin Hergüner
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    boolean request() default true;
    boolean response() default true;
}
