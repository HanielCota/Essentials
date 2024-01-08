package com.github.hanielcota.essentials.usecases;


import org.bukkit.entity.Player;

public interface ToggleFlyUseCase {
    void toggleFly(Player player);
    void toggleFly(Player sender, Player target);
}
