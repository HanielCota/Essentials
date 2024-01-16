package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@CommandAlias("top")
public class TopCommand extends BaseCommand {


    @Default
    @CommandPermission("essentials.top")
    public void onCommand(Player player) {
        World world = player.getWorld();

        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();

        int topY = world.getMaxHeight();
        Location topLocation = new Location(world, playerX + 0.5, topY + 1, playerZ + 0.5);

        player.teleportAsync(topLocation);

        player.sendMessage("§aTeleportado para o topo.");
    }
}