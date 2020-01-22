package com.sprince0031.javacar;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class JavaCarFunctionsTest {

    @Test
    public void shouldUnlockJavaCarAndReturnTrue() {
        JavaCarFunctions jcFunc = new JavaCarFunctions();
        assertTrue(jcFunc.unlockCar("Sprince0031", "p@ssw0rD"));
    }
}
