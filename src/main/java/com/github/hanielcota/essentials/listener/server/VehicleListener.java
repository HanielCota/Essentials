package com.github.hanielcota.essentials.listener.server;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class VehicleListener implements Listener {

    @EventHandler
    public void onVehicle(VehicleEnterEvent event) {
        Entity entity = event.getEntered();
        Player player = (Player) entity;

        if (entity.getType() != EntityType.PLAYER || player.hasPermission("essentials.op")) {
            return;
        }

        event.setCancelled(true);
    }
}
