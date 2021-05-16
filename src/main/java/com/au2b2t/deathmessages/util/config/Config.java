package com.au2b2t.deathmessages.util.config;

import com.au2b2t.deathmessages.DeathMessages;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class Config extends AbstractConfiguration<DeathMessages> {

    @Getter
    private int version;

    @Getter
    private boolean showRarity;

    public Config(final DeathMessages plugin) throws Exception { super(plugin, "config"); }

    @Override
    protected void loadValues(FileConfiguration config) throws Exception {
        if (config.getInt("config-version", 0) < getLatestVersion()) {
            config = convert(null);
        }

        version = config.getInt("config-version");
        showRarity = config.getBoolean("death-messages.show-weapon-rarity", false);

    }
}
