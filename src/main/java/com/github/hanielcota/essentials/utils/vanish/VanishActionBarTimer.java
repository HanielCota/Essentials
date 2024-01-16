package com.github.hanielcota.essentials.utils.vanish;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
@AllArgsConstructor
@Getter
public class VanishActionBarTimer extends BukkitRunnable {

    private final Player player;

    @Override
    public void run() {
        if (!player.isOnline() || !player.isInvisible()) {
            cancel();
            return;
        }

        String message = player.isInvisible() ? "§eSeu modo Vanish está ativo." : "§cVanish desativado.";
        sendActionBar(player, message);
    }

    private static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(message));
    }
}
