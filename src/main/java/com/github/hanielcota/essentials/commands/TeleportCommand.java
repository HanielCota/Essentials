package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.TeleportController;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@AllArgsConstructor
@CommandAlias("tp")
public class TeleportCommand extends BaseCommand {

    private final TeleportController teleportController;

    @Default
    @CommandCompletion("@players")
    public void onTeleportCommand(Player sender, String[] args) {
        if (args.length == 0 || args.length > 3) {
            sender.sendMessage("§cUso incorreto. Utilize /tp <alvo> <destino>|<x> <y> <z>");
            return;
        }

        if (args.length == 3) {
            double x = parseCoordinate(args[0]);
            double y = parseCoordinate(args[1]);
            double z = parseCoordinate(args[2]);

            teleportController.teleportToLocation(sender, x, y, z);
            return;
        }

        if (args.length == 2) {
            String targetName = args[0];
            Player target = Bukkit.getPlayerExact(targetName);

            if (target == null || !target.isOnline()) {
                sender.sendMessage("§cJogador " + targetName + " não encontrado ou offline.");
                return;
            }

            String destinationName = args[1];
            Player destination = Bukkit.getPlayerExact(destinationName);

            if (destination == null || !destination.isOnline()) {
                sender.sendMessage("§cJogador " + destinationName + " não encontrado ou offline.");
                return;
            }

            teleportController.teleportToPlayer(destination, target);
            return;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayerExact(targetName);

        if (target == null || !target.isOnline()) {
            sender.sendMessage("§cJogador " + targetName + " não encontrado ou offline.");
            return;
        }

        teleportController.teleportToPlayer(sender, target);
    }

    private double parseCoordinate(String coordinate) {
        try {
            return Double.parseDouble(coordinate);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
