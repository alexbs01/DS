package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilitiesTest {

    @Test
    void isLeap() {
        assertFalse(DateUtilities.isLeap(2022));
        assertFalse(DateUtilities.isLeap(2023));
        assertFalse(DateUtilities.isLeap(1900));
        assertTrue(DateUtilities.isLeap(2020));
        assertTrue(DateUtilities.isLeap(2000));
    }

    @Test
    void numberOfDays() {
        assertEquals(31, DateUtilities.numberOfDays(1,2022));
        assertEquals(28, DateUtilities.numberOfDays(2,2022));
        assertEquals(31, DateUtilities.numberOfDays(3,2022));
        assertEquals(30, DateUtilities.numberOfDays(4,2022));
        assertEquals(31, DateUtilities.numberOfDays(5,2022));
        assertEquals(30, DateUtilities.numberOfDays(6,2022));
        assertEquals(31, DateUtilities.numberOfDays(7,2022));
        assertEquals(31, DateUtilities.numberOfDays(8,2022));
        assertEquals(30, DateUtilities.numberOfDays(9,2022));
        assertEquals(31, DateUtilities.numberOfDays(10,2022));
        assertEquals(30, DateUtilities.numberOfDays(11,2022));
        assertEquals(31, DateUtilities.numberOfDays(12,2022));

        assertEquals(29, DateUtilities.numberOfDays(2,2020));
        assertThrows(IllegalArgumentException.class, () -> DateUtilities.numberOfDays(13,2020));
    }

    @Test
    void convertToISODate() {
        assertEquals("2022-01-01", DateUtilities.convertToISODate("January 01, 2022"));
        assertEquals("2022-02-02", DateUtilities.convertToISODate("February 02, 2022"));
        assertEquals("2022-03-03", DateUtilities.convertToISODate("March 03, 2022"));
        assertEquals("2022-04-04", DateUtilities.convertToISODate("April 04, 2022"));
        assertEquals("2022-05-05", DateUtilities.convertToISODate("May 05, 2022"));
        assertEquals("2022-06-06", DateUtilities.convertToISODate("June 06, 2022"));
        assertEquals("2022-07-07", DateUtilities.convertToISODate("July 07, 2022")); //  Saint Fermin
        assertEquals("2022-08-08", DateUtilities.convertToISODate("August 08, 2022"));
        assertEquals("2022-09-09", DateUtilities.convertToISODate("September 09, 2022"));
        assertEquals("2022-10-10", DateUtilities.convertToISODate("October 10, 2022"));
        assertEquals("2022-11-11", DateUtilities.convertToISODate("November 11, 2022"));
        assertEquals("2022-12-12", DateUtilities.convertToISODate("December 12, 2022"));
    }

    @Test
    void checkISODate() {
        assertTrue(DateUtilities.checkISODate("2022-07-07"));
        assertTrue(DateUtilities.checkISODate("2020-02-29"));
        assertTrue(DateUtilities.checkISODate("2000-10-14"));

        assertFalse(DateUtilities.checkISODate("2022-13-01"));
        assertFalse(DateUtilities.checkISODate("2022-02-29"));
        assertFalse(DateUtilities.checkISODate("2022-07-35"));
        assertFalse(DateUtilities.checkISODate("2022-07-35-32")); // Se comprueba que no haya nada extra ni de menos
        assertFalse(DateUtilities.checkISODate("2022-07"));
    }
}