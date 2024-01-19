package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.github.hanielcota.essentials.repository.WarpRepository;
import com.github.hanielcota.essentials.repository.impl.WarpLocation;
import com.github.hanielcota.essentials.view.WarpMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("warp")
@RequiredArgsConstructor
public class WarpCommands extends BaseCommand {

    private final WarpRepository warpRepository;

    @Default
    @CommandPermission("essentials.warps")
    public void onWarp(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUsage: /warp <nome>");
            new WarpMenu(warpRepository).openWarpMenu(player);
            return;
        }

        String warpName = args[0];
        WarpLocation warpLocation = warpRepository.getWarp(warpName);

        if (warpLocation == null) {
            player.sendMessage("§cWarp '" + warpName + "' não encontrada.");
            return;
        }

        player.teleportAsync(warpLocation.getLocation());
        player.sendMessage("§aTeleportado para a warp '" + warpName + "'.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 10.0F);
    }

    @Subcommand("set")
    @CommandPermission("essentials.warps")
    public void onSetWarp(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUsage: /warp set <nome>");
            return;
        }

        String warpName = args[0];
        WarpLocation warpLocation = new WarpLocation(
                player.getWorld().getName(),
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ(),
                player.getLocation().getPitch(),
                player.getLocation().getYaw());

        warpRepository.createWarp(warpName, warpLocation);
        player.sendMessage("§aWarp '" + warpName + "' criada com sucesso!");
    }

    @Subcommand("delete|del")
    @CommandPermission("essentials.warps")
    public void onDeleteWarp(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§cUsage: /warp delete <nome>");
            return;
        }

        String warpName = args[0];
        WarpLocation warpLocation = warpRepository.getWarp(warpName);

        if (warpLocation == null) {
            player.sendMessage("§cWarp '" + warpName + "' não encontrada.");
            return;
        }

        warpRepository.deleteWarp(warpName);
        player.sendMessage("§aWarp '" + warpName + "' deletada com sucesso!");
    }

    @Subcommand("list")
    @CommandPermission("essentials.warps")
    public void onListWarps(Player player) {
        List<String> warpNames = warpRepository.getAllWarps();

        if (warpNames.isEmpty()) {
            player.sendMessage("§cNão há warps registradas.");
            return;
        }
        player.sendMessage("§aWarps disponíveis: §f" + String.join(", ", warpNames));
    }
}
