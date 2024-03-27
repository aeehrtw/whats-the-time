package com.sid.whatsthetime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {


    @Test
    public void testEmptyString() {
        try {
            new Time("");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Empty string did not throw exception");
    }

    @Test
    public void testInvalidNoColon() {
        try {
            new Time("1220");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Invalid string did not throw exception");
    }

    @Test
    public void testInvalidBiggerNumberHour() {
        try {
            new Time("123:45");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Invalid string did not throw exception");
    }

    @Test
    public void testInvalidBiggerNumberMinute() {
        try {
            new Time("12:345");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Invalid string did not throw exception");
    }

    @Test
    public void testInvalidBiggerNumberHour_2() {
        try {
            new Time("54:45");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Invalid string did not throw exception");
    }

    @Test
    public void testInvalidBiggerNumberMinute_2() {
        try {
            new Time("12:77");
        } catch (IllegalArgumentException i) {

            return;
        }
        assertTrue(true, "Invalid string did not throw exception");
    }

}