package com.github.hanielcota.essentials.usecases;

import org.bukkit.entity.Player;

public interface TeleportUseCase {
    void teleportPlayer(Player sender, Player target);
}