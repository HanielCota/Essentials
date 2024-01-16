package com.github.hanielcota.essentials.listener.server;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class PlantationDamageListener implements Listener {

    @EventHandler
    public void onJumpPlantation(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() != Material.FARMLAND) return;
        event.setCancelled(true);
    }

}