package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("kill")
public class KillCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.kill")
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length > 1) {
            player.sendMessage("§cUso incorreto. Utilize /kill <alvo>");
            return;
        }

        if (args.length == 0) {
            player.setHealth(0.0D);
            player.sendMessage("§aVocê tirou sua própria vida!");
            return;
        }

        String targetName = args[0];
        Player target = Bukkit.getServer().getPlayerExact(targetName);

        if (target == null) {
            player.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        if (player.equals(target)) {
            player.sendMessage("§cVocê não pode se matar.");
            return;
        }

        target.setHealth(0.0D);
        player.sendMessage("§eVocê tirou a vida de " + target.getName() + "!");
    }
}
