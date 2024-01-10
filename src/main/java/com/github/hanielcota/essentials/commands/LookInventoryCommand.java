package com.github.hanielcota.essentials.commands;

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
    @CommandPermission("essentials.")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUtilize /inverse <jogador> para ver o inventário de um jogador.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (target == null) {
            player.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        PlayerInventory inv = target.getInventory();
        player.openInventory(inv);
    }
}
