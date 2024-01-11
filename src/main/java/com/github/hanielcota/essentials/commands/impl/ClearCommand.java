package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("clear")
public class ClearCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.clear")
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length > 1) {
            player.sendMessage("§cUtilize /clear <nick> para limpar o inventário de outro jogador.");
            return;
        }

        if (args.length == 0) {
            player.getInventory().clear();
            player.sendMessage("§aSeu inventário foi limpo.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage("§cJogador '" + args[0] + "' não encontrado ou offline.");
            return;
        }

        target.getInventory().clear();
        player.sendMessage("§eInventário do jogador %s foi limpo", args[0]);
    }
}