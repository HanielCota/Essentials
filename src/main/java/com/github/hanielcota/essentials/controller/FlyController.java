package com.github.hanielcota.essentials.controller;


import org.bukkit.entity.Player;

public interface FlyController {
    void toggleFly(Player player);
    void toggleFly(Player sender, Player target);
}
