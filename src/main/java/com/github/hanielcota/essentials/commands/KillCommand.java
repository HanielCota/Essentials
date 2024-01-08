package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.KillController;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("kill")
@AllArgsConstructor
public class KillCommand extends BaseCommand {

    private final KillController killController;

    @Default
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            killController.killPlayer(player);
            return;
        }

        if (args.length == 1) {

            String targetName = args[0];
            Player target = player.getServer().getPlayerExact(targetName);

            if (target == null || !target.isOnline()) {
                player.sendMessage("§cJogador " + targetName + " não encontrado ou offline.");
                return;
            }

            killController.killPlayer(target);
            player.sendMessage("§aVocê matou " + target.getName() + ".");
            return;
        }

        player.sendMessage("§cUso correto: /kill <player>");
    }
}
