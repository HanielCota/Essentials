package com.github.hanielcota.essentials.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        World world = Bukkit.getWorld("world");

        if (world == null) {
            Bukkit.getLogger().warning("The world 'world' was not found!");
            return;
        }

        Location spawnLocation = new Location(world, 453.452, 130, 34.607);

        event.setRespawnLocation(spawnLocation);
    }
}