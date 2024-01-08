package com.github.hanielcota.essentials.controller.impl;

import com.github.hanielcota.essentials.controller.GamemodeController;
import com.github.hanielcota.essentials.usecases.ChangeGamemodeUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class GamemodeControllerImpl implements GamemodeController {

    private final ChangeGamemodeUseCase changeGamemodeUseCase;

    @Override
    public void changeGamemode(Player player, String gamemode) {
        GameMode mode = parseGamemode(gamemode);
        if (mode == null) {
            player.sendMessage("§cModo de jogo inválido. Use /gamemode <0, 1, 2 ou 3>");
            return;
        }

        boolean success = changeGamemodeUseCase.changeGamemode(player, mode);
        if (success) {
            player.sendMessage("§aSeu modo de jogo foi alterado para " + mode.name());
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10f, 10f);
        }
    }

    private GameMode parseGamemode(String gamemode) {
        return switch (gamemode.toLowerCase()) {
            case "0", "survival" -> GameMode.SURVIVAL;
            case "1", "creative" -> GameMode.CREATIVE;
            case "2", "adventure" -> GameMode.ADVENTURE;
            case "3", "spectator" -> GameMode.SPECTATOR;
            default -> null;
        };
    }
}
