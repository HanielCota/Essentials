package com.github.hanielcota.essentials.utils;

import com.github.hanielcota.essentials.EssentialsPlugin;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class JailManager implements Listener {

    private static final Map<UUID, Jail> jailedPlayers = new HashMap<>();
    private static final int REMOVE_DELAY_TICKS = 60;

    private JailManager() {
    }

    public static void createJail(Player player) {
        Location jailLocation = player.getLocation().clone().add(0, 30, 0);
        Jail jail = new Jail(jailLocation);
        jailedPlayers.put(player.getUniqueId(), jail);
        jail.build();
        player.teleport(jailLocation.clone().add(0.5, 1, 0.5));
    }

    public static void removeJail(Player player) {
        UUID playerUUID = player.getUniqueId();
        Jail jail = jailedPlayers.remove(playerUUID);
        if (jail != null) {
            jail.destroy();
        }
    }

    public static boolean isInJail(Player player) {
        return jailedPlayers.containsKey(player.getUniqueId());
    }

    private static void scheduleRemoveJail(UUID playerUUID) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = Bukkit.getPlayer(playerUUID);
                if (player != null && player.isOnline()) {
                    removeJail(player);
                }
            }
        }.runTaskLater(JavaPlugin.getPlugin(EssentialsPlugin.class), REMOVE_DELAY_TICKS);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        removeJail(player);
    }

    @Data
    private static class Jail {

        private final Location center;
        private static final int SIZE = 2;

        public Jail(Location center) {
            this.center = center;
        }

        public void build() {
            for (int x = -SIZE; x <= SIZE; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -SIZE; z <= SIZE; z++) {
                        Location currentLocation = center.clone().add(x, y, z);
                        Material blockType = determineBlockType(x, y, z);
                        Block block = currentLocation.getBlock();
                        block.setType(blockType);
                    }
                }
            }
        }

        public void destroy() {
            for (int x = -SIZE; x <= SIZE; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -SIZE; z <= SIZE; z++) {
                        Location currentLocation = center.clone().add(x, y, z);
                        currentLocation.getBlock().setType(Material.AIR);
                    }
                }
            }
        }

        public boolean contains(Location location) {
            int centerX = center.getBlockX();
            int centerY = center.getBlockY();
            int centerZ = center.getBlockZ();
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();

            return x >= centerX - SIZE
                   && x <= centerX + SIZE
                   && y >= centerY - 2
                   && y <= centerY + 2
                   && z >= centerZ - SIZE
                   && z <= centerZ + SIZE;
        }

        private Material determineBlockType(int x, int y, int z) {
            boolean isEdge = x == -SIZE || x == SIZE || z == -SIZE || z == SIZE;
            boolean isBottomCenter = y == -2 && x >= -1 && x <= 1 && z >= -1 && z <= 1;
            boolean isTop = y == 2;

            return (isEdge || isTop || isBottomCenter) ? Material.MAGENTA_STAINED_GLASS : Material.AIR;
        }
    }
}
