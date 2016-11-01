package com.davidlum.deck;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A boilerplate Java class to route all string-catalog lookups through the same place.  Using
 * this class to access string resources means that a Javac compiler plugin could be used to
 * perform compile-time validation that each desired message exists and that all necessary
 * parameters are being passed to it.
 */
class Msg {
    /** the resource bundle of messages */
    private static final ResourceBundle s_MessageBundle = ResourceBundle.getBundle(
            "com.davidlum.deck.Strings",
            Locale.getDefault());

    /**
     * Looks up a parameterized string in this package's message catalog.
     * @param msgKey the key for the message
     * @param params the parameters to plug into the message
     * @return the formatted message with 'params' inserted
     */
    static String get( String msgKey, Object... params) {
        try {
            String formatStr = s_MessageBundle.getString( msgKey);
            MessageFormat fmt = new MessageFormat( formatStr, Locale.getDefault());
            return fmt.format( params);
        } catch ( Exception e) {
            // There's no point blowing up here, is there?  We've already done all we can do
            // at compile-time using static analyzers to ensure this never happens.  If it does
            // happen anyway, we don't want to make things any worse by introducing a new failure.
            // The best we can do is return a string that contains all the parameters as well as
            // the missing key value.
            return "!!!" + msgKey + "!!! " + Arrays.asList( params);
        }
    }
}
