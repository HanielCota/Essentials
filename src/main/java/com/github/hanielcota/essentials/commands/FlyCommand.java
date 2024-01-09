package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.FlyController;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("fly")
@AllArgsConstructor
public class FlyCommand extends BaseCommand {

    private final FlyController flyController;

    @Default
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            flyController.toggleFly(player);
            return;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso incorreto. Utilize /fly [nick]");
            return;
        }

        String targetName = args[0];
        Player target = player.getServer().getPlayerExact(targetName);

        if (target == null || !target.isOnline()) {
            player.sendMessage("§cJogador " + targetName + " não encontrado ou offline.");
            return;
        }

        if (target.equals(player)) {
            player.sendMessage("§cVocê não pode alterar o modo de voo para si mesmo.");
            return;
        }

        flyController.toggleFly(player, target);
    }

}