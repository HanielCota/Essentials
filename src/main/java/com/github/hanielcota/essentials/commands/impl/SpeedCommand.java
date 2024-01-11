package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("speed")
public class SpeedCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.speed")
    @CommandCompletion("reset")
    public void onCommand(Player player, String[] args) {
        if (args.length != 1) {
            player.sendMessage("§cUtilize /speed <quantidade> para alterar sua velocidade ao voar.");
            return;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            resetSpeed(player);
            player.sendMessage("§aSpeed resetado para o padrão.");
            return;
        }

        if (!isInt(args[0])) {
            player.sendMessage("§cVocê inseriu um número inválido.");
            return;
        }

        int quaint = Integer.parseInt(args[0]);

        if (quaint < 1 || quaint > 10) {
            player.sendMessage("§cA quantidade deve estar entre 1 e 10.");
            return;
        }

        float speed = quaint * 0.1F;
        player.setFlySpeed(speed);
        player.sendMessage("§aVocê alterou o speed ao voar para " + quaint + ".");
    }

    private void resetSpeed(Player player) {
        player.setFlySpeed(0.1F);
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
