package com.github.hanielcota.essentials.commands;

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

        Location topLocation = new Location(playerLocation.getWorld(),
                playerLocation.getX(),
                playerLocation.getY(),
                playerLocation.getZ());

        topLocation.setY(topLocation.getWorld().getHighestBlockYAt(topLocation) + 1);

        player.teleportAsync(topLocation);
        player.sendMessage("§aTeleportado para o topo.");
    }
}
