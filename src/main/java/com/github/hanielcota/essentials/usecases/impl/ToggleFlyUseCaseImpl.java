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
        if (target.getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR) {
            sender.sendMessage("§cVocê não pode ativar o modo de voo para jogadores neste modo de jogo.");
            return;
        }

        boolean isFlying = target.isFlying();
        target.setAllowFlight(!isFlying);
        target.setFlying(!isFlying);

        if (isFlying) {
            sender.sendMessage("§aModo de voo desativado para " + target.getName() + ".");
            target.sendMessage("§cSeu modo de voo foi desativado por " + sender.getName() + ".");
            return;
        }

        sender.sendMessage("§aModo de voo ativado para " + target.getName() + ".");
        target.sendMessage("§aSeu modo de voo foi ativado por " + sender.getName() + ".");
    }
}
