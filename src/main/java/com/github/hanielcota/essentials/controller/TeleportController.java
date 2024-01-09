package com.github.hanielcota.essentials.controller;

import org.bukkit.entity.Player;

public interface TeleportController {
    void teleportToPlayer(Player sender, Player target);
    void teleportToLocation(Player sender, double x, double y, double z);

}
