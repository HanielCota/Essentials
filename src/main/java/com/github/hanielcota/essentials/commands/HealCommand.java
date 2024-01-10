package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("heal|curar")
public class HealCommand extends BaseCommand {


    @Default
    @CommandPermission("essentials.heal")
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length > 1) {
            player.sendMessage("§cUso incorreto. Utilize /heal <alvo>");
            return;
        }

        if (args.length == 0) {
            player.setHealth(20.0D);
            player.sendMessage("§aVocê foi curado.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (target == null) {
            player.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        if (player.equals(target)) {
            player.sendMessage("§cVocê não pode se curar assim!");
            return;
        }

        target.setHealth(20.0D);
        player.sendMessage("§eVocê curou o jogador " + target.getName() + "§e.");
    }


}
