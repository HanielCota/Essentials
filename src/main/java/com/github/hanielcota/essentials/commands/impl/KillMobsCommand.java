package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@CommandAlias("killmobs")
public class KillMobsCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {
        player.getWorld().getEntities().stream()
                .filter(entity -> entity.getType().isAlive() && !(entity instanceof Player))
                .forEach(Entity::remove);

        player.sendMessage("§aVocê matou todos os mobs vivos neste mundo.");
    }
}
