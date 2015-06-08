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
        if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard"))WG_ENABLED = true;

        if(WG_ENABLED)
        {
            Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
            if(Bukkit.getPluginManager().isPluginEnabled("ChitChat"))
            {
                Bukkit.getPluginManager().registerEvents(new TitleListener(), this);
            }
        }

        //Set PLUGIN
        PLUGIN = this;
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
