package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("kick")
public class KickCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.kick")
    @CommandCompletion("@players")
    public void onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUtilize /kick <jogador> <motivo> para kickar um jogador.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cO jogador " + args[0] + " não está online!");
            return;
        }

        if (target.equals(player)) {
            player.sendMessage("§cVocê não pode se expulsar do servidor.");
            return;
        }

        String motivo = args.length > 1 ? String.join(" ", args).substring(args[0].length() + 1) : "§cSem motivo informado.";
        if (motivo.length() > 60) {
            player.sendMessage("§cO motivo não pode exceder 60 caracteres.");
            return;
        }

        String serverName = "§c§lANKARES";
        Component kickReason = Component.text(serverName + "\n\n§cVocê foi expulso do servidor. \n\n" + "§c§lMotivo: §c"
                + motivo + "\n\n" + "§c§lAutor: §c"
                + player.getName());

        target.kick(kickReason);

       player.sendMessage(
                "",
                "§eVocê expulsou o jogador " + target.getName() + " do servidor.",
                "§eMotivo: " + motivo,
                "");

        Bukkit.getOnlinePlayers().stream()
                .filter(onlinePlayer -> player.hasPermission("essentials.kick"))
                .forEach(onlinePlayer -> onlinePlayer.sendMessage
                        ("§cO jogador " + target.getName() + " foi expulso do servidor.",
                        "§cMotivo: " + motivo,
                        "",
                        "§cAutor: " + player.getName(), ""));
    }
}
