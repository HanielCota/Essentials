package com.github.hanielcota.essentials.listener.server;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionsListener implements Listener {

    @EventHandler
    public void handleEntityExplode(EntityExplodeEvent event) {
        EntityType entityType = event.getEntity().getType();

        if (shouldCancelExplosion(entityType)) {
            event.setCancelled(true);
            event.blockList().clear();
        }
    }

    private boolean shouldCancelExplosion(EntityType entityType) {
        return entityType == EntityType.ENDER_DRAGON
                || entityType == EntityType.PRIMED_TNT
                || entityType == EntityType.MINECART_TNT;
    }

    @EventHandler
    public void handleExplosionPrime(ExplosionPrimeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(true);
    }
}
