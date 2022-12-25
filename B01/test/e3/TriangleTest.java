package e3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private static Triangle t90_1 = new Triangle(30, 60, 90);
    private static Triangle t90_2 = new Triangle(60, 90, 30);
    private static Triangle t90_3 = new Triangle(90, 30, 60);
    private static Triangle t60 = new Triangle(60, 60, 60);
    private static Triangle t100 = new Triangle(100, 40, 40);


    @Test
    void testConstructors() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));

        Triangle t1bis = new Triangle(t90_1);
        assertEquals(t90_1.angle0(), t1bis.angle0());
        assertEquals(t90_1.angle1(), t1bis.angle1());
        assertEquals(t90_1.angle2(), t1bis.angle2());
    }

    @Test
    void isRight() {
        assertTrue(t90_1.isRight());
        assertTrue(t90_2.isRight());
        assertTrue(t90_3.isRight());
    }

    @Test
    void isAcute() {
        assertFalse(t90_1.isAcute());
        assertTrue(t60.isAcute());
    }

    @Test
    void isObtuse() {
        assertFalse(t90_1.isObtuse());
        assertTrue(t100.isObtuse());
    }

    @Test
    void isEquilateral() {
        assertFalse(t90_1.isEquilateral());
        assertTrue(t60.isEquilateral());
    }

    @Test
    void isIsosceles() {
        assertFalse(t90_1.isIsosceles());
        assertTrue(t100.isIsosceles());
        assertFalse(t60.isIsosceles());
    }

    @Test
    void isScalene() {
        assertTrue(t90_1.isScalene());
        assertFalse(t100.isScalene());
    }

    @Test
    void testEquals() {
        assertEquals(t90_1, t90_2);
        assertEquals(t90_1, t90_3);
        assertEquals(t90_2, t90_3);
    }

    @Test
    void testHashCode() {
        assertEquals(t90_1.hashCode(), t90_2.hashCode());
        assertEquals(t90_1.hashCode(), t90_3.hashCode());
        assertEquals(t90_2.hashCode(), t90_3.hashCode());
        assertNotEquals(t90_1.hashCode(), t60.hashCode());
        assertNotEquals(t60.hashCode(), t100.hashCode());
    }

    @Test
    void angle0() {
        assertEquals(30, t90_1.angle0());
        assertEquals(60, t90_2.angle0());
        assertEquals(90, t90_3.angle0());
    }

    @Test
    void angle1() {
        assertEquals(60, t90_1.angle1());
        assertEquals(90, t90_2.angle1());
        assertEquals(30, t90_3.angle1());
    }

    @Test
    void angle2() {
        assertEquals(90, t90_1.angle2());
        assertEquals(30, t90_2.angle2());
        assertEquals(60, t90_3.angle2());
    }
}