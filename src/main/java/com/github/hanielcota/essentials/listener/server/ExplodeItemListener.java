package com.github.hanielcota.essentials.listener.server;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ExplodeItemListener implements Listener {

    @EventHandler
    public void onItemExplode(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();

        if (!(entity instanceof Item)) {
            return;
        }

        e.setCancelled(true);
    }
}