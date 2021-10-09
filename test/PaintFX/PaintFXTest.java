package PaintFX;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaintFXTest {

    /**
     * Test of getW and getH methods, of class PaintFX.
     */
    @Test
    public void testGetDimensions() {
        PaintFX.setVariables(1000, 500);
        double expResult = 1000;
        double result = PaintFX.getW();
        double expResult1 = 500;
        double result1 = PaintFX.getH();
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult1, result1, 0.0);
    }

    /**
     * Test of getChange, getChange1, and getChange2 methods, of class PaintFX.
     */
    @Test
    public void testGetChanges() {
        int expResult = 0;
        int result = PaintFX.getChange();
        assertEquals(expResult, result);
        int expResult1 = 0;
        int result1 = PaintFX.getChange1();
        assertEquals(expResult1, result1);
        int expResult2 = 0;
        int result2 = PaintFX.getChange2();
        assertEquals(expResult2, result2);
        
        PaintFX.setChange(1);
        PaintFX.setChange1(0);
        PaintFX.setChange2(2);
        expResult = 1;
        result = PaintFX.getChange();
        assertEquals(expResult, result);
        expResult1 = 0;
        result1 = PaintFX.getChange1();
        assertEquals(expResult1, result1);
        expResult2 = 2;
        result2 = PaintFX.getChange2();
        assertEquals(expResult2, result2);
        
        PaintFX.setChange(2);
        PaintFX.setChange1(1);
        PaintFX.setChange2(0);
        expResult = 2;
        result = PaintFX.getChange();
        assertEquals(expResult, result);
        expResult1 = 1;
        result1 = PaintFX.getChange1();
        assertEquals(expResult1, result1);
        expResult2 = 0;
        result2 = PaintFX.getChange2();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getSelec method, of class PaintFX.
     */
    @Test
    public void testGetSelec() {
        PaintFX.setSelec(1);
        int expResult = 1;
        int result = PaintFX.getSelec();
        assertEquals(expResult, result);
    }
    
}
