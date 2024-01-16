package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.events.PlayerToggleInvisibilityEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@CommandAlias("vanish|v")
public class VanishCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.vanish")
    public void onCommand(Player player, String[] args) {
        if (args.length != 0) {
            player.sendMessage("§cUso incorreto. Utilize /vanish para ativar ou desativar a invisibilidade.");
            return;
        }

        boolean isVanished = player.isInvisible();
        player.setInvisible(!isVanished);

        if (!isVanished) {
            player.sendMessage("", "§aModo Vanish ativado com sucesso.", "§aAgora você está invisível para outros jogadores.", "");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);

            callVanishEvent(player);
            return;
        }

        callVanishEvent(player);
        player.sendMessage("§cModo Vanish desativado.");
    }

    private void callVanishEvent(Player player) {
        final PlayerToggleInvisibilityEvent event = new PlayerToggleInvisibilityEvent(player);
        Bukkit.getPluginManager().callEvent(event);
    }
}
