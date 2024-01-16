// VanishEventListener.java
package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.EssentialsPlugin;
import com.github.hanielcota.essentials.events.PlayerToggleInvisibilityEvent;
import com.github.hanielcota.essentials.utils.vanish.VanishActionBarTimer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PlayerVanishListener implements Listener {
    private final Map<UUID, VanishActionBarTimer> vanishActionBarTimers = new HashMap<>();
    private final EssentialsPlugin plugin;

    @EventHandler
    public void onPlayerToggleInvisibility(PlayerToggleInvisibilityEvent event) {
        Player player = event.getPlayer();

        if (player.isInvisible() && !vanishActionBarTimers.containsKey(player.getUniqueId())) {
            startVanishActionBarTimer(player);
            return;
        }

        if (!player.isInvisible()) {
            cancelVanishActionBarTimer(player);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player damaged && damaged.isInvisible()) {
            event.setCancelled(true);
            return;
        }

        if (event.getDamager() instanceof Player damaged && damaged.isInvisible()) {
            event.setCancelled(true);
        }
    }

    private void startVanishActionBarTimer(Player player) {
        if (plugin == null) {
            return;
        }
        VanishActionBarTimer actionBarTimer = new VanishActionBarTimer(player);
        actionBarTimer.runTaskTimerAsynchronously(plugin, 0L, 20L);
        vanishActionBarTimers.put(player.getUniqueId(), actionBarTimer);
    }


    private void cancelVanishActionBarTimer(Player player) {
        VanishActionBarTimer actionBarTimer = vanishActionBarTimers.remove(player.getUniqueId());

        if (actionBarTimer != null) {
            actionBarTimer.cancel();
        }
    }
}
