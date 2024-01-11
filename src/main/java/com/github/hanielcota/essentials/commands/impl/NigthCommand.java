package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.World;
import org.bukkit.entity.Player;
@CommandAlias("noite|nigth")
public class NigthCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.noite")
    public void onCommand(Player player) {

        World world = player.getWorld();
        world.setTime(19000L);
        player.sendMessage("§eVocê alterou o tempo deste mundo para: NOITE.");
    }
}
