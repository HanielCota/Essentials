package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

@CommandAlias("invsee")
public class LookInventoryCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandPermission("essentials.invsee")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUtilize /invsee <jogador> para ver o inventário de um jogador.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (target == null) {
            player.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        if (player.equals(target)) {
            player.sendMessage("§cVocê não pode ver seu próprio inventário.");
            return;
        }

        PlayerInventory inv = target.getInventory();
        player.openInventory(inv);
    }
}
