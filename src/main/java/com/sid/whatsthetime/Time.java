package com.sid.whatsthetime;

import lombok.Data;

/**
 * Representation of time in hours and minutes.
 */
@Data
public class Time {
    private int hour;
    private int minute;

    public static final char SEPARATOR = ':';

    /**
     * Makes an instance of the Time from 'timeString'
     * a valid timeString will be of the format "h:mm" or "hh:mm"
     *      * Constraints : (valid values)
     *      *  0 <= hour <= 24
     *      *  0 <= minute <= 60
     * @param timeString
     */
    public Time(String timeString) {
        if (timeString == null || timeString.isBlank()
        || timeString.indexOf(SEPARATOR) == -1) {
            throw new IllegalArgumentException("Invalid input :" + timeString);
        }

        String [] times = timeString.split(":");
        if (times == null || times.length == 0 || times.length > 2) {
            throw new IllegalArgumentException("Invalid input :" + timeString);
        }

        int hour = getTimeInteger(times[0], timeString);
        int minute = getTimeInteger(times[1], timeString);

        // validate
        timeValidator(hour, minute);

        this.hour = hour;
        this.minute = minute;

    }

    /**
     * Makes an instance of Time, given the hour and minute
     * Constraints : (valid values)
     *  0 <= hour <= 24
     *  0 <= minute <= 60
     * @param hour
     * @param minute
     */
    public Time(int hour, int minute) {
        timeValidator(hour, minute);

        this.hour = hour;
        this.minute = minute;
    }

    private int getTimeInteger(String string, String input) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + string + " in part of input:" + input);
        }
    }

    private void timeValidator(int hour, int minute) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid hour or minute value. hour:[" + hour +
                    "] minute:[" + minute + "]");
        }
    }
}
