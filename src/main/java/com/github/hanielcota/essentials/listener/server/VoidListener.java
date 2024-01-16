package com.github.hanielcota.essentials.listener.server;

import com.github.hanielcota.essentials.utils.player.PlayerUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidListener implements Listener {

    @EventHandler
    public void damageVoid(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity.getType() != EntityType.PLAYER) return;
        Player player = (Player) entity;

        if (!e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) return;
        if (player.getLocation().getBlockY() > 0) return;

        e.setCancelled(true);
        player.setFallDistance(0);

        PlayerUtils.teleportPlayerToSpawn(player);
    }
}
