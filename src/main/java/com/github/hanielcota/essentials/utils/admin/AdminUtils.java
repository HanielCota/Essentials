package com.github.hanielcota.essentials.utils.admin;

import com.github.hanielcota.essentials.EssentialsPlugin;
import com.github.hanielcota.essentials.utils.JailManager;
import com.github.hanielcota.essentials.utils.items.AdminItemUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AdminUtils {

    private final Map<String, Boolean> adminStatusMap = new HashMap<>();

    public void setAdminItems(Player player) {
        player.getInventory().setItem(8, AdminItemUtils.createKillAuraItem());
        player.getInventory().setItem(0, AdminItemUtils.createAntiKBItem());
        player.getInventory().setItem(4, AdminItemUtils.createJailItem());
    }

    public void clearAdminItems(Player player) {
        player.getInventory().clear(8);
        player.getInventory().clear(0);
        player.getInventory().clear(4);
    }

    public boolean toggleAdminMode(Player player) {
        boolean currentStatus = adminStatusMap.getOrDefault(player.getName(), false);
        adminStatusMap.put(player.getName(), !currentStatus);
        return !currentStatus;
    }

    public boolean isAdmin(Player player) {
        return adminStatusMap.getOrDefault(player.getName(), false);
    }

    public boolean isItem(ItemStack itemStack, ItemStack referenceItem) {
        return referenceItem.isSimilar(itemStack);
    }

    public void applyKillAuraAction(Player player, EssentialsPlugin plugin) {
        if (player == null) {
            return;
        }

        player.setInvisible(true);
        player.setCollidable(false);

        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            player.setInvisible(false);
            player.setCollidable(true);},
                20L);
    }

    public void applyAntiKBAction(Player target) {
        if (target == null) {
            return;
        }

        Vector velocity = target.getLocation().getDirection().multiply(-2.5);
        target.setVelocity(velocity);
    }

    public void applyJailAction(Player target) {
        if (target == null) {
            return;
        }

        Location targetLocation = target.getLocation();

        if (hasObstructingBlocks(targetLocation)) {
            target.sendMessage("§cNão é possível criar uma prisão aqui. Remova os blocos obstruindo.");
            return;
        }

        JailManager.createJail(target);
    }

    private boolean hasObstructingBlocks(Location location) {
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (location.clone().add(x, y, z).getBlock().getType() != Material.AIR) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}