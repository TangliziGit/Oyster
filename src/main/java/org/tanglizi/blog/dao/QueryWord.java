package org.tanglizi.blog.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryWord {
    String column() default "";
    ExampleQuery.MatchType type() default ExampleQuery.MatchType.EQUAL;
}
