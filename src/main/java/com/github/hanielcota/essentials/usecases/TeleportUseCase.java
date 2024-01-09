package com.github.hanielcota.essentials.usecases;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface TeleportUseCase {
    void teleportPlayer(Player sender, Player target);
    void teleportPlayerToLocation(Player sender, Location location);
}