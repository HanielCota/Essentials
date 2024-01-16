package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.utils.player.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
public class SpawnCommand extends BaseCommand {

    @Default
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {

            PlayerUtils.teleportPlayerToSpawn(player);
            player.sendMessage("§eVocê foi teleportado para o spawn.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 10.0F);
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cO jogador " + args[0] + " não foi encontrado.");
            return;
        }

        PlayerUtils.teleportPlayerToSpawn(target);
        player.sendMessage("§eVocê foi teleportado para o spawn, por um staff " + target.getName());
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 10.0F);
    }
}
