package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("compass")
public class CompassCommand extends BaseCommand {

    @Default
    public void onCommand(Player player, String[] args) {
        float yaw = (player.getLocation().getYaw() + 360) % 360;

        String[] directions = {"Norte", "Nordeste", "Leste", "Sudeste", "Sul", "Sudoeste", "Oeste", "Noroeste"};

        int index = (int) Math.floor((yaw / 45) + 0.5) % 8;
        String direction = directions[index];

        player.sendMessage("§cVocê está olhando para o " + direction);
    }
}
