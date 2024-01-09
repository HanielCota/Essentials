package com.github.hanielcota.essentials.usecases.impl;

import com.github.hanielcota.essentials.usecases.ToggleFlyUseCase;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ToggleFlyUseCaseImpl implements ToggleFlyUseCase {

    @Override
    public void toggleFly(Player player) {
        toggleFly(player, player);
    }

    @Override
    public void toggleFly(Player sender, Player target) {
        GameMode targetGameMode = target.getGameMode();

        if (targetGameMode == GameMode.CREATIVE || targetGameMode == GameMode.SPECTATOR) {
            sender.sendMessage("§cVocê não pode ativar o modo de voo para jogadores neste modo de jogo.");
            return;
        }

        boolean isFlying = target.getAllowFlight();

        target.setAllowFlight(!isFlying);
        target.setFlying(!isFlying);

        String message = isFlying ? "§cFly desativado." : "§aFly ativado.";
        target.sendMessage(message);
    }
}
