package com.minegusta.portalspawn;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager
{
    private static FileConfiguration conf;

    public static void loadConfig()
    {
        Main.getMain().saveDefaultConfig();
        conf = Main.getMain().getConfig();
    }

    public static void save()
    {
        Main.getMain().saveConfig();
    }

    public static FileConfiguration getConfig()
    {
        return conf;
    }
}
