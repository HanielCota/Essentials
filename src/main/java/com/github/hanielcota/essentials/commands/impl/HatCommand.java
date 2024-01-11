package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("hat|chapeu")
public class HatCommand extends BaseCommand {
    @Default
    @CommandPermission("essentials.hat")
    public void onCommand(Player player, String[] args) {
        if (args.length != 0) {
            player.sendMessage("§cUso incorreto. Utilize /hat segurando um item na mão.");
            return;
        }

        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage("§cVocê precisa segurar um item em sua mão!");
            return;
        }

        ItemStack currentHelmet = player.getInventory().getHelmet();

        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
        player.getInventory().setItemInMainHand(currentHelmet != null ? currentHelmet : new ItemStack(Material.AIR));
        player.sendMessage("§aAproveite o seu novo chapéu!");
    }
}
