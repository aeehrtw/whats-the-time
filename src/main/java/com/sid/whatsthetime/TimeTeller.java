package com.sid.whatsthetime;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

/**
 * Tells the time like the British say it
 */
public class TimeTeller {

    private static final Integer TWELVE_HOURS = 12;
    private static final Integer MINUTES_TO_HOUR = 34;
    private static final HashSet<Integer> SPECIAL_MINUTES = new HashSet<>(Arrays.asList(15, 30, 45));
    private static final HashSet<Integer> SIMPLE_MINUTES = new HashSet<>(Arrays.asList(31, 32, 33, 34));
    private static final String WORD_SEPARATOR = " ";
    private static final String PAST = "past";
    private static final String TO = "to";

    // We are interested in British ways
    private static final Locale locale = Locale.UK;


    // private boolean isPostNoon;     // can be used to tell 'am', 'pm' times ; unused now

    public String tellTheTime(Time time) {

        StringBuilder stringBuilder = new StringBuilder();
        int hour = time.getHour();
        int min = time.getMinute();

        // decide what's the connector between hour and minute
        String connector = geHourMinuteConnector(min);


        RuleBasedNumberFormat ruleBasedNumberFormat
                = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);

        String hourString = handleHours(hour, min, ruleBasedNumberFormat);

        // special cases handling
        if (hour == 0 && min == 0) {
            stringBuilder.append("midnight");
        } else if (hour == 12 && min == 0) {
            stringBuilder.append("noon");
        } else if (min == 0) {
            // all the o'clocks
            stringBuilder.append(hourString)
                    .append(WORD_SEPARATOR)
                        .append(connector);


        // not-so-special cases
        } else {


            String minuteString = handleMinutes(min, ruleBasedNumberFormat);

            // putting it all together
            if (SIMPLE_MINUTES.contains(min)) {
                stringBuilder.append(hourString).append(WORD_SEPARATOR).append(minuteString);
            } else {
                stringBuilder.append(minuteString)
                        .append(WORD_SEPARATOR).append(connector)
                            .append(WORD_SEPARATOR).append(hourString);
            }
        }

        return stringBuilder.toString();
    }



    private static String geHourMinuteConnector(int min) {
        StringBuilder connectorBuilder = new StringBuilder();
        if (min > MINUTES_TO_HOUR) {                // 35 to 59
            connectorBuilder.append(TO);
        } else if (SIMPLE_MINUTES.contains(min)) {  // 31 to 34
            connectorBuilder.append("");
        } else if (min > 0) {                       // 01 to 30
            connectorBuilder.append(PAST);
        } else {                                    // min == 0
            connectorBuilder.append("o'clock");
        }
        return connectorBuilder.toString();
    }

    private String handleHours(int hour, int min, RuleBasedNumberFormat ruleBasedNumberFormat) {

        // we will make two small improvement-ish here, so that we can support the 24-hour time format
        // in the current scope we will not distinguish between am and pm, but support spoken time of the 24-hour time format

        if(hour > TWELVE_HOURS) {     // no need to check for invalid values, Time class takes care of it
            hour = hour - TWELVE_HOURS;
        }

        // The interesting thing is, hour's string value depends on the minute!
        if (min > MINUTES_TO_HOUR) {
            hour = hour + 1;
            if (hour > TWELVE_HOURS) {  // an edge case for values like 12:44 etc.
                hour = hour - TWELVE_HOURS;
            }
        }

        // if the hour is 0 (and min != 0) we should treat it as 12
        if (hour == 0) hour = TWELVE_HOURS;

        return ruleBasedNumberFormat.format(hour);
    }

    private String handleMinutes(int min, RuleBasedNumberFormat ruleBasedNumberFormat) {
        String minuteString;

        if (SPECIAL_MINUTES.contains(min)) {
            minuteString = switch (min) {
                case 15, 45 -> "quarter";
                case 30 -> "half";
                default -> "";
            };
        } else {
            // form the minute string

            // if minute >= 35, we subtract it from 60 to get 'x minutes to the next hour'
            if (min >= 35) {
                min = 60 - min;
            }

            String minString = ruleBasedNumberFormat.format(min);

            // RuleBasedNumberFormat gives out some numbers with a hyphen, for example forty-two
            // As per the requirements, we want space to be the separator

            minuteString = minString.replace('-', ' ');
        }

        return minuteString;
    }

}
