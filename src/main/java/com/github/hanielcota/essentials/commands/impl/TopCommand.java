package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("top")
public class TopCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.top")
    public void onCommand(Player player) {
        Location playerLocation = player.getLocation();

        if (playerLocation.getWorld() == null) {
            player.sendMessage("§cErro: Mundo inválido.");
            return;
        }

        Location topLocation = playerLocation.getWorld().getHighestBlockAt(playerLocation).getLocation();

        topLocation.add(0, 1, 0);

        player.teleportAsync(topLocation);
        player.sendMessage("§aTeleportado para o topo.");
    }

}
