package com.au2b2t.deathmessages.util;

import com.au2b2t.deathmessages.util.compat.CompatUtil;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public static String getItemStackNBTTag(ItemStack itemStack) throws Throwable {
        // Minecraft has used a variety of less-than-JSON formats in the past
        // so it leads to a lot of stuff like this
        String json = convertItemStackToJsonRegular(itemStack);
        int tagIndex = json.indexOf("tag:");
        if (tagIndex < 0) throw new IllegalArgumentException();
        return json.substring(tagIndex, json.length() - 1);
    }

    public static String convertItemStackToJsonRegular(ItemStack itemStack) throws Throwable {
        // yay, NMS
        Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
        Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

        Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
        Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
        Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

        Object nmsNbtTagCompoundObj;
        Object nmsItemStackObj;
        Object itemAsJsonObject;

        try {
            nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
            nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
            itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
        } catch (Throwable t) {
            throw t;
        }

        return itemAsJsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static String convertEntityToJson(Entity entity, String name) throws Exception {
        Map<String, String> data = new HashMap<>();
        String str = entity.getType().toString().replace('_', ' ').toLowerCase();
        data.put("name", name );
        data.put("type", CompatUtil.is1_14() ?
                entity.getType().getKey().toString() :
                Util.capitalize(str).replace(" ","")
        );
        data.put( "id", entity.getUniqueId().toString() );
        JSONObject json = new JSONObject();
        json.putAll( data );
        return json.toString();
    }

    public static String prepareEntityJson(String json) {
        if (CompatUtil.is1_15()) {
            return json;
        }
        return json.replace("\"id\"", "id").replace("\"name\"", "name").replace("\"type\"", "type");
    }
}
