package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("tp")
public class TeleportCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandPermission("essentials.tp")
    public void onTeleportCommand(Player sender, String[] args) {
        if (args.length == 0 || args.length > 3) {
            sender.sendMessage("§cUso incorreto. Utilize /tp <alvo> <destino>");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (target == null) {
            sender.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        if (args.length == 1) {
            if (target.getName().equalsIgnoreCase(sender.getName())) {
                sender.sendMessage("§cVocê não pode se teleportar para si mesmo.");
                return;
            }

            sender.teleportAsync(target.getLocation());
            sender.sendMessage("§aTeleportado para " + target.getName() + ".");
            return;
        }

        Player destination = Bukkit.getPlayerExact(args[1]);

        if (destination == null) {
            sender.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        if (target.getName().equalsIgnoreCase(destination.getName())) {
            sender.sendMessage("§cVocê não pode se teleportar para si mesmo.");
            return;
        }

        target.teleportAsync(destination.getLocation());
        sender.sendMessage("§aTeleportado " + target.getName() + " para " + destination.getName() + ".");
        target.sendMessage("§aTeleportado para " + destination.getName() + ".");
    }
}
