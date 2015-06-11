package com.minegusta.portalspawn;

import com.demigodsrpg.chitchat.Chitchat;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TitleListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Location from = e.getFrom();
        Location to = e.getTo();

        ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(from.getWorld()).getApplicableRegions(from);
        ApplicableRegionSet set2 = WorldGuardPlugin.inst().getRegionManager(to.getWorld()).getApplicableRegions(to);

        for(ProtectedRegion r : set2.getRegions())
        {
            if(!set.getRegions().contains(r))
            {
                runTitle(e.getPlayer(), r);
                break;
            }
        }
    }

    private void runTitle(Player p, ProtectedRegion r)
    {
        String name = r.getId();
        for(String s : ConfigManager.getConfig().getConfigurationSection("regions").getKeys(false))
        {
            if(s.equalsIgnoreCase(name))
            {
                String path = "regions." + name + ".";

                String title = ConfigManager.getConfig().getString(path + "title", "&4&lWasted");
                String subTitle = ConfigManager.getConfig().getString(path + "subtitle", "Kappa");
                int duration = ConfigManager.getConfig().getInt(path + "duration", 20);
                int fadeIn = ConfigManager.getConfig().getInt(path + "fade-in", 10);
                int fadeOut = ConfigManager.getConfig().getInt(path + "fade-out", 10);

                Chitchat.sendTitle(p, fadeIn, duration, fadeOut, title, subTitle);
            }
        }
    }

}
