package com.github.hanielcota.essentials.usecases.impl;

import com.github.hanielcota.essentials.usecases.KillPlayerUseCase;
import org.bukkit.entity.Player;

public class KillPlayerUseCaseImpl implements KillPlayerUseCase {

    @Override
    public boolean killPlayer(Player player) {
        if (player == null || !player.isOnline() || player.isDead()) {
            return false;
        }

        if (!hasPermission(player)) {
            player.sendMessage("§cVocê não tem permissão para se matar.");
            return false;
        }

        player.setHealth(0.0);
        return true;
    }

    private boolean hasPermission(Player player) {
        String permission = "essentials.kill";
        return player.hasPermission(permission);
    }
}
