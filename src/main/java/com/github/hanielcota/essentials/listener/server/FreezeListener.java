package com.github.hanielcota.essentials.listener.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void onFreeze(BlockFormEvent event) {
        if (!event.getBlock().getType().toString().contains("WATER")) return;
        event.setCancelled(true);
    }
}