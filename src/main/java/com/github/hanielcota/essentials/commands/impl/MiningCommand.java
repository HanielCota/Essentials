package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@CommandAlias("minerar")
public class MiningCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {
        Location location = new Location(player.getWorld(), 1287.474 ,201, 689.428);
        player.teleportAsync(location);

        player.sendMessage("§eVocê foi teleportado para a area de mineração.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 10.0F);
    }
}
