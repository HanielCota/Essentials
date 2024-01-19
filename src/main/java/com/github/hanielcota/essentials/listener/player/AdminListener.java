package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.EssentialsPlugin;
import com.github.hanielcota.essentials.utils.items.AdminItemUtils;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class AdminListener implements Listener {

    private final EssentialsPlugin plugin;

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        final Entity rightClicked = event.getRightClicked();
        if (!(rightClicked instanceof Player target) || event.getPlayer().equals(target)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (plugin.getAdminUtils().isItem(itemInHand, AdminItemUtils.createJailItem())) {
            plugin.getAdminUtils().applyJailAction(target);
            return;
        }

        if (plugin.getAdminUtils().isItem(itemInHand, AdminItemUtils.createKillAuraItem())) {
            plugin.getAdminUtils().applyKillAuraAction(target, plugin);
            return;
        }

        if (plugin.getAdminUtils().isItem(itemInHand, AdminItemUtils.createAntiKBItem())) {
            plugin.getAdminUtils().applyAntiKBAction(target);
        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        final Player player = event.getPlayer();

        if (plugin.getAdminUtils().getAdminStatusMap().containsKey(player.getName())) {
            player.sendMessage("§cNão é possível colocar blocos no chão no modo admin.");
            event.setCancelled(true);
        }
    }
}
