package com.github.hanielcota.essentials.controller.impl;


import com.github.hanielcota.essentials.controller.KillController;
import com.github.hanielcota.essentials.usecases.KillPlayerUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class KillControllerImpl implements KillController {

    private final KillPlayerUseCase killPlayerUseCase;

    @Override
    public void killPlayer(Player player) {
        if (player == null || !player.isOnline() || player.isDead()) {
            return;
        }

        if (!killPlayerUseCase.killPlayer(player)) {
            player.sendMessage("§cNão foi possível matar você.");
            return;
        }

        player.sendMessage("§aVocê foi morto.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_DEATH, 1f, 1f);
    }
}
