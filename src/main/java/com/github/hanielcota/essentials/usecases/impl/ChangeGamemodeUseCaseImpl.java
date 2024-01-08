package com.github.hanielcota.essentials.usecases.impl;

import com.github.hanielcota.essentials.usecases.ChangeGamemodeUseCase;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ChangeGamemodeUseCaseImpl implements ChangeGamemodeUseCase {

    @Override
    public boolean changeGamemode(Player player, GameMode gamemode) {
        if (!hasPermission(player, gamemode)) {
            player.sendMessage("§cVocê não tem permissão para alterar para esse modo de jogo.");
            return false;
        }

        if (player.getGameMode() == gamemode) {
            player.sendMessage("§cVocê já está nesse modo de jogo.");
            return false;
        }

        player.setGameMode(gamemode);
        return true;
    }

    private boolean hasPermission(Player player, GameMode gamemode) {
        String permission = "essentials.gamemode." + gamemode.name().toLowerCase();
        return player.hasPermission(permission);
    }
}
