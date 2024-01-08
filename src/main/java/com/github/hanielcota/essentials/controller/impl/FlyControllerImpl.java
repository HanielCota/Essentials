package com.github.hanielcota.essentials.controller.impl;

import com.github.hanielcota.essentials.controller.FlyController;
import com.github.hanielcota.essentials.usecases.ToggleFlyUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class FlyControllerImpl implements FlyController {

    private final ToggleFlyUseCase toggleFlyUseCase;

    @Override
    public void toggleFly(Player player) {
        if (hasPermission(player)) {
            player.sendMessage("§cVocê não tem permissão para ativar/desativar o modo de voo.");
            return;
        }

        toggleFlyUseCase.toggleFly(player);
    }

    @Override
    public void toggleFly(Player sender, Player target) {
        if (hasPermission(sender)) {
            sender.sendMessage("§cVocê não tem permissão para ativar/desativar o modo de voo para outros jogadores.");
            return;
        }

        toggleFlyUseCase.toggleFly(sender, target);
    }

    private boolean hasPermission(Player player) {
        return !player.hasPermission("essentials.fly");
    }

}
