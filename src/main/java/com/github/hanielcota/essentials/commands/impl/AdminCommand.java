package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import com.github.hanielcota.essentials.EssentialsPlugin;
import org.bukkit.entity.Player;

@CommandAlias("admin")
public class AdminCommand extends BaseCommand {

    @Dependency
    private EssentialsPlugin plugin;

    @Default
    @CommandPermission("essentials.admin")
    public void onCommand(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage("§cSeu inventário está cheio. Limpe espaço antes de receber itens.");
            return;
        }

        boolean toggledAdmin =  plugin.getAdminUtils().toggleAdminMode(player);

        if (toggledAdmin) {
            player.sendMessage("§aVocê entrou no modo Admin.");
            plugin.getAdminUtils().setAdminItems(player);
            return;
        }
        player.sendMessage("§cVocê saiu do modo Admin.");
        plugin.getAdminUtils().clearAdminItems(player);
    }
}
