package com.minegusta.portalspawn;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener
{
    private static final boolean ENABLED = ConfigManager.getConfig().getBoolean("spawn-on-join", false);
    private static final int DISTANCE = ConfigManager.getConfig().getInt("spawn-on-join-distance", 200);

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerJoinEvent e)
    {
        /**
         * If the player is close to the spawn, they will be teleported there.
         */
        Player player = e.getPlayer();
        Location loginSpot = player.getLocation();
        Location spawn = player.getWorld().getSpawnLocation();

        if(ENABLED && loginSpot.distance(spawn) < DISTANCE)
        {
            ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(player.getWorld()).getApplicableRegions(loginSpot);
            if(set.size() > 0)
            {
                for(ProtectedRegion r : set.getRegions())
                {
                    if(r.getFlags().containsKey(DefaultFlag.PVP) && r.getFlag(DefaultFlag.PVP) == StateFlag.State.DENY)
                    {
                        player.teleport(spawn);
                        break;
                    }
                }
            }
        }

    }
}
