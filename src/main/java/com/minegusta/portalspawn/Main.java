package com.minegusta.portalspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static boolean WG_ENABLED = false;
    private static Main PLUGIN;

    @Override
    public void onEnable()
    {

        //Set PLUGIN
        PLUGIN = this;

        if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard"))WG_ENABLED = true;

        ConfigManager.loadConfig();

        if(WG_ENABLED)
        {
            boolean loginClear = ConfigManager.getConfig().getBoolean("reset-inv-on-join", false);

            if(loginClear) Bukkit.getPluginManager().registerEvents(new LoginListener(), this);


            boolean titles = ConfigManager.getConfig().getBoolean("title-on-enter-region", false);

            if(Bukkit.getPluginManager().isPluginEnabled("Chitchat") && titles)
            {
                Bukkit.getPluginManager().registerEvents(new TitleListener(), this);
            }
        }
    }

    public static Main getMain()
    {
        return PLUGIN;
    }

    @Override
    public void onDisable()
    {

    }
}
