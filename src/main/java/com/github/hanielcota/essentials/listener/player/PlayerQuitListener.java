package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.EssentialsPlugin;
import com.github.hanielcota.essentials.utils.JailManager;
import com.github.hanielcota.essentials.utils.admin.AdminUtils;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class PlayerQuitListener implements Listener {

    private final EssentialsPlugin plugin;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
        Player player = event.getPlayer();

        if (plugin.getAdminUtils().isAdmin(player)) {
            player.getInventory().clear();
            plugin.getAdminUtils().getAdminStatusMap().remove(player.getName());
            return;
        }

        boolean inJail = JailManager.isInJail(player);
        if (inJail) {
            JailManager.removeJail(player);
            notifyStaff(player);
        }
    }

    private void notifyStaff(Player player) {

        Bukkit.getOnlinePlayers().stream()
                .filter(staff -> staff.hasPermission("essentials.staff"))
                .forEach(staff -> staff.sendMessage(
                        "",
                        "§cO jogador " + player.getName() + " deslogou em modo jail.",
                        "§cConsidere aplicar uma punição.",
                        ""));
    }
}
