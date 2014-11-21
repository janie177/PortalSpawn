package com.minegusta.portalspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static boolean WG_ENABLED = false;
    @Override
    public void onEnable()
    {
        if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard"))WG_ENABLED = true;

         Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
    }

    @Override
    public void onDisable()
    {

    }
}
