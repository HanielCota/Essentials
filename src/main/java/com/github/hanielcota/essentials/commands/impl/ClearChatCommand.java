package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@CommandAlias("clearchat|cc")
public class ClearChatCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.clearchat")
    public void onCommand(Player player) {

        IntStream.range(0, 100).forEach(i -> Bukkit.getServer().broadcast(Component.text("")));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = now.format(formatter);

        Bukkit.getServer().broadcast(Component.text(
                "§eA limpeza do chat foi realizada por " + player.getName() + " em " + formattedDate + "."
        ));
    }
}
