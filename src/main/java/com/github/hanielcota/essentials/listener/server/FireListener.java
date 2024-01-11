package com.github.hanielcota.essentials.listener.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;

public class FireListener implements Listener {

    @EventHandler
    public void onSpreadFire(BlockIgniteEvent event) {
        if (isAllowedIgniteCause(event.getCause())) {
            return;
        }

        event.setCancelled(true);
    }

    private boolean isAllowedIgniteCause(BlockIgniteEvent.IgniteCause cause) {
        return cause == BlockIgniteEvent.IgniteCause.LAVA || cause == BlockIgniteEvent.IgniteCause.SPREAD;
    }

    @EventHandler
    public void onBlockFire(BlockBurnEvent event) {
        event.setCancelled(true);
    }
}