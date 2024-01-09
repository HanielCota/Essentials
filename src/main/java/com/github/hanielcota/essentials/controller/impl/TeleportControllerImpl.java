package com.github.hanielcota.essentials.controller.impl;

import com.github.hanielcota.essentials.controller.TeleportController;
import com.github.hanielcota.essentials.usecases.TeleportUseCase;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class TeleportControllerImpl implements TeleportController {

    private final TeleportUseCase teleportUseCase;

    @Override
    public void teleportToPlayer(Player sender, Player target) {
        if (hasPermission(sender)) {
            sender.sendMessage("§cVocê não tem permissão para se teleportar para jogadores.");
            return;
        }

        teleportUseCase.teleportPlayer(sender, target);
    }

    @Override
    public void teleportToLocation(Player sender, double x, double y, double z) {
        if (hasPermission(sender)) {
            sender.sendMessage("§cVocê não tem permissão para se teleportar para coordenadas.");
            return;
        }

        Location location = new Location(sender.getWorld(), x, y, z);
        teleportUseCase.teleportPlayerToLocation(sender, location);
    }

    private boolean hasPermission(Player player) {
        return !player.hasPermission("essentials.teleport");
    }
}
