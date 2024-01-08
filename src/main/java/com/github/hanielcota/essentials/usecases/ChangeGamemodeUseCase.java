package com.github.hanielcota.essentials.usecases;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public interface ChangeGamemodeUseCase {
    boolean changeGamemode(Player player, GameMode gamemode);
}