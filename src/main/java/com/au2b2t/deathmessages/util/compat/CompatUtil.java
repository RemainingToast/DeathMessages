package com.au2b2t.deathmessages.util.compat;

import com.au2b2t.deathmessages.util.Log;
import org.bukkit.Bukkit;

public class CompatUtil {

    // 1.8 = 1_008
    // 1.9 = 1_009
    // 1.10 = 1_010
    // ...
    // 1.14 = 1_014
    // 1.15 = 1_015

    private static int VERSION;
    private static int REVISION;

    public CompatUtil(){
        String ver = Bukkit.getServer().getVersion().split("\\(MC:")[1].split("\\)")[0].trim().split(" ")[0].trim();
        Log.info("Minecraft version is " + ver);
        try {
            String[] tokens = ver.split("\\.");
            int mcMajor = Integer.parseInt(tokens[0]);
            int mcMinor = 0;
            int mcRevision = 0;
            if (tokens.length > 1) {
                mcMinor = Integer.parseInt(tokens[1]);
            }
            if (tokens.length > 2) {
                mcRevision = Integer.parseInt(tokens[2]);
            }
            VERSION = mcMajor * 1000 + mcMinor;
            REVISION = mcRevision;
        } catch (Exception ex) {
            Log.warn("Cannot detect Minecraft version from string - " +
                    "some features will not work properly. " +
                    "Please contact the plugin author if you are on " +
                    "standard CraftBukkit or Spigot. This plugin " +
                    "expects getVersion() to return a string " +
                    "containing '(MC: 1.15)' or similar. The version " +
                    "DeathMessages tried to parse was '" + ver + "'");
        }
    }

    public static boolean is1_16() { return ver(1_016); }

    public static boolean is1_15() { return ver(1_015); }

    public static boolean is1_14() { return ver(1_014); }

    public static boolean is1_13() { return ver(1_013); }

    public static boolean is1_12() { return ver(1_012); }

    public static boolean is1_11() { return ver(1_011); }

    public static boolean is1_10() { return ver(1_010); }

    public static boolean is1_8() { return ver(1_008); }


    private static boolean ver(final int ver) {
        return VERSION >= ver;
    }

    private static boolean rev(final int ver, final int rev) {
        return VERSION > ver || (VERSION == ver && REVISION >= rev);
    }
}
