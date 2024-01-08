package com.github.hanielcota.essentials.usecases;

import org.bukkit.entity.Player;

public interface GiveItemUseCase {
    boolean giveItem(Player sender, Player target, String item, int amount);
}