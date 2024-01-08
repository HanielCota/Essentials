package com.github.hanielcota.essentials.controller;

import org.bukkit.entity.Player;

public interface GiveController {
    void giveItem(Player sender, String targetName, String item, int amount);
}