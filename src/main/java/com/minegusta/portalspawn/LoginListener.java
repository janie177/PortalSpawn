package com.minegusta.portalspawn;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener
{

    private static final boolean WG_ENABLED = Main.WG_ENABLED;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerJoinEvent e)
    {
        /**
         * If the player is close to the spawn, they will be teleported there.
         */
        Player player = e.getPlayer();
        Location loginSpot = player.getLocation();
        Location spawn = player.getWorld().getSpawnLocation();

        if(loginSpot.distance(spawn) < 100.0)
        {
            if(WG_ENABLED)
            {
                ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(player.getWorld()).getApplicableRegions(loginSpot);
                if(set.size() > 0)
                {
                    player.teleport(spawn);
                }
            }
            else
            {
                player.teleport(spawn);
            }
        }

    }
}
