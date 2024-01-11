package com.github.hanielcota.essentials.listener.server;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamageListener implements Listener {

    @EventHandler
    public void onDamageVoid(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() != EntityType.PLAYER || !event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            return;
        }

        event.setCancelled(true);
    }
}