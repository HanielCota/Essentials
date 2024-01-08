package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.HealController;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("heal|curar")
@AllArgsConstructor
public class HealCommand extends BaseCommand {

    private final HealController healController;

    @Default
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            healController.healPlayer(player);
            return;
        }

        if (args.length == 1) {
            String targetName = args[0];
            Player target = player.getServer().getPlayerExact(targetName);

            if (target == null || !target.isOnline()) {
                player.sendMessage("§cJogador não encontrado ou offline.");
                return;
            }

            healController.healPlayer(target);
            player.sendMessage("§aVocê curou " + target.getName() + ".");
        }

        player.sendMessage("§cUso correto: /heal <player>");
    }
}
