package com.au2b2t.deathmessages.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Log {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("DeathMessages");

    public static void info(final String s) {
        logger.log(Level.INFO, s);
    }

    public static void warn(final String s) {
        logger.log(Level.WARNING, s);
    }

    public static void error(final String s, final Throwable thrown) {
        if (thrown != null)
            logger.log(Level.SEVERE, s, thrown);
        else
            logger.log(Level.SEVERE, s);
    }

    public static void error(final String s) {
        error(s, null);
    }
}
