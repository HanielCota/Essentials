package com.github.hanielcota.essentials.controller.impl;

import com.github.hanielcota.essentials.controller.GiveController;
import com.github.hanielcota.essentials.usecases.GiveItemUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class GiveControllerImpl implements GiveController {

    private final GiveItemUseCase giveItemUseCase;

    @Override
    public void giveItem(Player sender, String targetName, String item, int amount) {
        Player target = sender.getServer().getPlayer(targetName);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("§cJogador não encontrado ou offline.");
            return;
        }

        boolean success = giveItemUseCase.giveItem(sender, target, item, amount);
        if (success) {
            sender.sendMessage("§aItem enviado com sucesso para " + target.getName() + ".");
        }
    }
}
