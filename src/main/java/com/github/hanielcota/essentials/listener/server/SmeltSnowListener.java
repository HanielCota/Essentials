package com.github.hanielcota.essentials.listener.server;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class SmeltSnowListener implements Listener {

    @EventHandler
    public void onSmeltSnow(BlockFadeEvent event) {
        Material type = event.getBlock().getType();
        if (type == Material.ICE || type == Material.SNOW) event.setCancelled(true);
    }
}
