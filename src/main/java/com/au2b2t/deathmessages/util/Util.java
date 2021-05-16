package com.au2b2t.deathmessages.util;

import org.bukkit.ChatColor;

public class Util {

    public static String color(final String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static  String capitalize(String s) {
        // capitalizes every word (separated by spaces) in a string
        if (s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        for (String word: s.split(" ")) {
            sb.append(" ");
            if (word.length() < 2) {
                sb.append(word.toUpperCase());
            } else {
                sb.append(word.toUpperCase().charAt(0));
                sb.append(word.substring(1));
            }
        }
        return sb.substring(1);
    }

}
