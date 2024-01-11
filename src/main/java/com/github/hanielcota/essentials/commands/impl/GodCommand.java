package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("deus")
public class GodCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.god")
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            boolean status = player.isInvulnerable();
            player.setInvulnerable(!status);
            player.sendMessage((status ? "§c" : "§a") + "Modo Deus " + (status ? "desativado." : "ativado."));
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cO jogador " + args[0] + " não está online.");
            return;
        }

        boolean status = target.isInvulnerable();
        target.setInvulnerable(!status);
        target.sendMessage((status ? "§c" : "§a") + "Modo Deus " + (status ? "desativado." : "ativado."));
    }

}