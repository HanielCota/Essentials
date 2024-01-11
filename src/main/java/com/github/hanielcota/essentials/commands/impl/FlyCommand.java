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

@CommandAlias("fly")
@AllArgsConstructor
public class FlyCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandPermission("essentials.fly")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            if (shouldDenyFlyInGameMode(player)) {
                player.sendMessage("§cVocê não pode ativar o fly neste modo de jogo.");
                return;
            }
            toggleFlight(player);
            return;
        }

        Player target = Bukkit.getServer().getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cJogador '" + args[0] + "' não encontrado ou offline.");
            return;
        }

        if (target.equals(player)) {
            player.sendMessage("§cVocê não pode alterar o seu próprio modo de fly.");
            return;
        }

        toggleFlight(target);
        player.sendMessage("§eModo de voo do jogador " + target.getName() + ", foi " + (target.getAllowFlight() ? "ativado!" : "desligado!"));
    }

    private void toggleFlight(Player player) {
        boolean isFlying = player.getAllowFlight();
        player.setAllowFlight(!isFlying);
        player.sendMessage(isFlying ? "§cModo de voo desligado!" : "§aModo de voo ativado!");
    }

    private boolean shouldDenyFlyInGameMode(Player player) {
        return player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR;
    }
}