package com.github.hanielcota.essentials.listener.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NaturalSpawnListener implements Listener {

    @EventHandler
    public void onSpawnNaturally(CreatureSpawnEvent event) {
        CreatureSpawnEvent.SpawnReason reason = event.getSpawnReason();

        if (isNonNaturalSpawnReason(reason)) {
            event.setCancelled(true);
        }
    }

    private boolean isNonNaturalSpawnReason(CreatureSpawnEvent.SpawnReason reason) {
        return reason != CreatureSpawnEvent.SpawnReason.NATURAL
                && reason != CreatureSpawnEvent.SpawnReason.SPAWNER
                && reason != CreatureSpawnEvent.SpawnReason.JOCKEY
                && reason != CreatureSpawnEvent.SpawnReason.MOUNT;
    }
}
