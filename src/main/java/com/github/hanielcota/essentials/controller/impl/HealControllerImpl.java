package com.github.hanielcota.essentials.controller.impl;


import com.github.hanielcota.essentials.controller.HealController;
import com.github.hanielcota.essentials.usecases.HealPlayerUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class HealControllerImpl implements HealController {

    private final HealPlayerUseCase healPlayerUseCase;

    @Override
    public void healPlayer(Player player) {
        if (healPlayerUseCase.healPlayer(player)) {
            player.sendMessage("§aVocê foi curado.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        }
    }
}
