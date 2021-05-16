package com.au2b2t.deathmessages.util.compat;

import org.bukkit.Material;

public class Items {

    public static boolean checkLadderBlock(Material m){
        boolean check;
        if (CompatUtil.is1_16()) {
            check = isTrapdoor(m) || m == Material.LADDER ||
                    m == Material.VINE ||
                    m == Material.WATER ||
                    m == Material.SCAFFOLDING ||
                    m == Material.TWISTING_VINES ||
                    m == Material.WEEPING_VINES;
        } else if (CompatUtil.is1_13())
            check = m == Material.LADDER || m == Material.VINE || m == Material.WATER;
        else
            check = materialSafeCheck(m, "STATIONARY_WATER") ||
                    m == Material.LADDER ||
                    m == Material.VINE ||
                    m == Material.WATER;
        return check;
    }

    public static boolean checkBedBlock(Material m) {
        boolean bool;
        if(CompatUtil.is1_13()) {
            bool = m == Material.BLACK_BED ||
                    m == Material.BLUE_BED ||
                    m == Material.BROWN_BED ||
                    m == Material.CYAN_BED ||
                    m == Material.GRAY_BED ||
                    m == Material.GREEN_BED ||
                    m == Material.LIGHT_BLUE_BED ||
                    m == Material.LIGHT_GRAY_BED ||
                    m == Material.LIME_BED ||
                    m == Material.MAGENTA_BED ||
                    m == Material.ORANGE_BED ||
                    m == Material.PINK_BED ||
                    m == Material.PURPLE_BED ||
                    m == Material.RED_BED ||
                    m == Material.WHITE_BED ||
                    m == Material.YELLOW_BED;
        } else {
            bool = materialSafeCheck(m, "BED") || materialSafeCheck(m, "BED_BLOCK");
        }
        if(CompatUtil.is1_16()) {
            bool |= m == Material.RESPAWN_ANCHOR;
        }
        return bool;
    }

    private static boolean isTrapdoor(Material m) {
        return m == Material.OAK_TRAPDOOR || m == Material.BIRCH_TRAPDOOR || m == Material.SPRUCE_TRAPDOOR
                || m == Material.ACACIA_TRAPDOOR || m == Material.DARK_OAK_TRAPDOOR || m == Material.JUNGLE_TRAPDOOR
                || m == Material.IRON_TRAPDOOR;
    }

    private static boolean materialSafeCheck(Material m, String s) {
        return m.name().equalsIgnoreCase(s);
    }
}
