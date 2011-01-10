/**
 * Copied from:
 * http://mycila.googlecode.com/svn/sandbox/src/main/java/com/mycila/sandbox/
 * junit/runner/ConcurrentJunitRunner.java
 * 
 * original author: Mathieu Carbou (mathieu.carbou@gmail.com)
 */

package com.opower.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Concurrent {
    int threads() default 5;
}
