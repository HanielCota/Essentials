package com.github.hanielcota.essentials.listener.server;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class JumpPlantationListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.PHYSICAL) {
            return;
        }

        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.FARMLAND) {
            return;
        }

        event.setUseInteractedBlock(Event.Result.DENY);
        event.setCancelled(true);
    }
}
