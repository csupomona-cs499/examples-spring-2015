package edu.cpp.cs499.demostorage;

import junit.framework.TestCase;

/**
 * Created by yusun on 5/12/15.
 */
public class MyUnitTest extends TestCase {

    public void testAddition() {
        Project p = new Project("yu", "qq", 100);
        assertNotNull(p);
        assertEquals("yu", p.getName());
        assertEquals("qq", p.getMember());
        assertEquals(100, p.getBudget());
    }
}
