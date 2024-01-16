package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("ping")
public class PingCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {
        final int ping = player.getPing();

        if (ping > 200) {
            player.sendMessage("§cSeu ping está péssimo! Ping: " + ping + "ms");
            return;
        }

        if (ping > 130) {
            player.sendMessage("§eSeu ping está ruim, mas ainda jogável. Ping: " + ping + "ms");
            return;
        }

        player.sendMessage("§aSeu ping está bom! Ping: " + ping + "ms");
    }
}
