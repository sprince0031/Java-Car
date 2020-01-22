package com.sprince0031.javacar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class JavaCarFunctionsTest {

    @Test
    public void shouldUnlockJavaCarAndReturnTrue() {
        JavaCarFunctions jcFunc = new JavaCarFunctions();
        assertTrue(jcFunc.unlockCar("Sprince0031", "p@ssw0rD"));
    }

    @Test
    public void checkRangeAtFullCharge() {
        JavaCarEVFunctions jcEVFunc = new JavaCarEVFunctions();
        assertEquals(593.75, jcEVFunc.calculateRange(), 0.01);
    }

}
