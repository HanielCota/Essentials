package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.GamemodeController;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@AllArgsConstructor
public class GamemodeCommand extends BaseCommand {

    private final GamemodeController gamemodeController;

    @Default
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length != 1 && args.length != 2) {
            player.sendMessage("§cUso: /gamemode <modo> <jogador>");
            return;
        }

        String gamemode = args[0];
        String playerName = args.length == 2 ? args[1] : player.getName();

        if (!isValidPlayerName(playerName)) {
            player.sendMessage("§cO nome do jogador '" + playerName + "' é inválido.");
            return;
        }

        Player targetPlayer = Bukkit.getPlayerExact(playerName);

        if (targetPlayer == null) {
            player.sendMessage("§cO jogador " + playerName + " não foi encontrado.");
            return;
        }

        if (player.equals(targetPlayer)) {
            gamemodeController.changeGamemode(player, gamemode);
            return;
        }

        gamemodeController.changeGamemode(targetPlayer, gamemode);
        player.sendMessage("§aModo de jogo de " + targetPlayer.getName() + " alterado para " + gamemode);
    }

    private boolean isValidPlayerName(String playerName) {
        return playerName != null && !playerName.isEmpty() && playerName.matches("\\w{2,16}");
    }
}
