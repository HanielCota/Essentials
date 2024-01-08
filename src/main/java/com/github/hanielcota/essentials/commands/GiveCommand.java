package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.controller.GiveController;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("give")
@AllArgsConstructor
public class GiveCommand extends BaseCommand {

    private final GiveController giveController;

    @Default
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§cUso correto: /give <player> <item> <quantidade>");
            return;
        }

        String targetName = args[0];
        String item = args[1];
        int amount;

        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cA quantidade deve ser um número válido.");
            return;
        }

        giveController.giveItem(player, targetName, item, amount);
    }
}
