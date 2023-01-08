package vials;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import colorenum.Colors;

public class AbstractVialTest {
    EnumVial vial;
    EnumVial vial2;

    @Before
    public void setUp() throws Exception {
        vial = new EnumVial(Colors.BLUE, Colors.CYAN, Colors.GREEN, Colors.GREEN);
        vial2 = new EnumVial();
    }

    @Test
    public void testColors() {
        Colors[] colors = vial.colors().toArray(Colors[]::new);
        Colors[] expected = { Colors.BLUE, Colors.CYAN, Colors.GREEN, Colors.GREEN };
        assertArrayEquals(colors, expected);

        Colors[] colors2 = vial2.colors().toArray(Colors[]::new);
        Colors[] expected2 = { Colors.EMPTY, Colors.EMPTY, Colors.EMPTY, Colors.EMPTY };
        assertArrayEquals(colors2, expected2);
    }

    @Test
    public void testEmptyColor() {
        assertEquals(Colors.EMPTY, vial.emptyColor());
    }

    @Test
    public void testGet() {
        assertEquals(Colors.CYAN, vial.get(1));
        assertEquals(Colors.EMPTY, vial2.get(0));
    }

    @Test
    public void testIsEmpty() {
        assertFalse("Vial should not be empty", vial.isEmpty());
        assertTrue("Vial should be empty", vial2.isEmpty());
    }

    @Test
    public void testIsSolved() {
        EnumVial solved = new EnumVial(Colors.BLUE, Colors.BLUE, Colors.BLUE, Colors.BLUE);

        assertFalse("Vial should not be solved", vial.isSolved());
        assertTrue("Vial should be solved", vial2.isSolved());
        assertTrue("Vial should be solved", solved.isSolved());
    }

    @Test
    public void testSet() {
        vial.set(0, Colors.RED);
        assertEquals(Colors.RED, vial.get(0));
    }

    @Test
    public void testTransfer() {
        vial.transfer(vial2);
        // Vial 2 tests
        assertEquals(Colors.GREEN, vial2.get(0));
        assertEquals(Colors.GREEN, vial2.get(1));
        assertEquals(Colors.EMPTY, vial2.get(2));
        assertEquals(Colors.EMPTY, vial2.get(3));

        // Vial 1 tests
        assertEquals(Colors.BLUE, vial.get(0));
        assertEquals(Colors.CYAN, vial.get(1));
        assertEquals(Colors.EMPTY, vial.get(2));
        assertEquals(Colors.EMPTY, vial.get(3));
    }
}
