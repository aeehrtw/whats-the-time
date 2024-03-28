package com.sid.whatsthetime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTellerTest {

    TimeTeller timeTeller;

    @BeforeEach
    public void letsGo() {
        timeTeller = new TimeTeller();
    }

    @AfterEach
    public void windUp() {
        timeTeller = null;
    }


    // special cases
    @Test
    public void testTimeTeller_noon() {
        String expected = "noon";
        Time time = new Time("12:00");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_midnight() {
        String expected = "midnight";
        Time time = new Time("0:00");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    // on the hour
    @Test
    public void testTimeTellerHour_1() {
        String expected = "ten o'clock";
        Time time = new Time("10:00");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTellerHour_2() {
        String expected = "one o'clock";
        Time time = new Time("1:00");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    // general tests
    @Test
    public void testTimeTeller_1() {
        String expected = "ten past three";
        Time time = new Time("3:10");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_2() {
        String expected = "quarter past six";
        Time time = new Time("6:15");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_3() {
        String expected = "twenty two past four";
        Time time = new Time("4:22");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_5() {
        String expected = "half past nine";
        Time time = new Time("9:30");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_6() {
        String expected = "eight thirty two";
        Time time = new Time("8:32");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_7() {
        String expected = "nineteen to nine";
        Time time = new Time("8:41");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_8() {
        String expected = "ten to ten";
        Time time = new Time("9:50");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void testTimeTeller_9() {
        String expected = "quarter to three";
        Time time = new Time("02:45");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    // 24 hour specials
    @Test
    public void test24hrClock_1() {
        String expected = "one o'clock";
        Time time = new Time("13:00");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void test24hrClock_2() {
        String expected = "five to twelve";
        Time time = new Time("23:55");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void test24hrClock_3() {
        String expected = "five past five";
        Time time = new Time("17:05");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }

    @Test
    public void test24hrClock_4() {
        String expected = "quarter past seven";
        Time time = new Time("19:15");
        assertEquals(expected, timeTeller.tellTheTime(time));
    }
}