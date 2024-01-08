package com.github.hanielcota.essentials.usecases.impl;

import com.github.hanielcota.essentials.usecases.TeleportUseCase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportPlayerUseCase implements TeleportUseCase {

    @Override
    public void teleportPlayer(Player sender, Player target) {
        try {
            if (target == null || !target.isOnline()) {
                sender.sendMessage("§cJogador não encontrado ou offline.");
                return;
            }

            if (sender.equals(target)) {
                sender.sendMessage("§cVocê não pode se teleportar para si mesmo.");
                return;
            }

            sender.teleportAsync(target.getLocation());
            sender.sendMessage("§aTeleportado para " + target.getName());

            target.sendMessage("§aVocê foi teleportado para o " + sender.getName());
        } catch (Exception e) {
            Bukkit.getLogger().info("" + e);
        }
    }
}
