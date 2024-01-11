package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@AllArgsConstructor
public class GamemodeCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandPermission("essentials.gamemode")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUtilize /gamemode <modo> para alterar seu modo de jogo.");
            return;
        }

        String gm = args[0];
        GameMode mode = parseGamemode(gm);

        if (mode == null) {
            player.sendMessage("§cModo de jogo inválido. Use /gamemode <0, 1, 2 ou 3>");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (args.length == 2) {
            if (target == null) {
                player.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
                return;
            }

            if (target.getGameMode() == mode) {
                player.sendMessage("§cO jogador " + target + " já está nesse modo de jogo.");
                return;
            }

            target.setGameMode(mode);
            target.sendMessage("§aModo de jogo alterado para " + mode.name() + ".");
            player.sendMessage("§eModo de jogo do jogador " +  targetName + ", foi alterado para " + mode.name() + ".");
            return;
        }

        if (player.getGameMode() == mode) {
            player.sendMessage("§cVocê já está nesse modo de jogo.");
            return;
        }

        player.setGameMode(mode);
        player.sendMessage("§aModo de jogo alterado para " + mode.name() + ".");
    }

    private GameMode parseGamemode(String gamemode) {
        return switch (gamemode.toLowerCase()) {
            case "0", "survival" -> GameMode.SURVIVAL;
            case "1", "creative" -> GameMode.CREATIVE;
            case "2", "adventure" -> GameMode.ADVENTURE;
            case "3", "spectator" -> GameMode.SPECTATOR;
            default -> null;
        };
    }
}
