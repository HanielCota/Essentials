package com.github.hanielcota.essentials.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.utils.CompactUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("compactar")
public class CompactCommand extends BaseCommand {

    @Default
    @CommandPermission("essentials.compactar")
    public void onCompact(Player player, String[] args) {
        if (args.length > 1) {
            player.sendMessage("§cUso incorreto. Utilize /compactar segurando o item que deseja compactar.");
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        int amount = CompactUtils.getAmount(itemInHand, player.getInventory());

        if (itemInHand.getType() == Material.AIR) {
            player.sendMessage("§cVocê precisa de um item em sua mão.");
            return;
        }

        if (amount <= 8) {
            player.sendMessage("§cVocê não possui itens suficientes para compactar.");
            return;
        }

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage("§cVocê precisa ter pelo menos um slot vazio no inventário para poder compactar.");
            return;
        }

        Material compactBlockType = CompactUtils.getCompactBlockType(itemInHand.getType());
        if (compactBlockType == null) {
            player.sendMessage("§cVocê não pode compactar este item.");
            return;
        }

        int totalBlocks = amount / 9;
        int leftoverItems = amount % 9;

        player.getInventory().remove(itemInHand.getType());
        player.getInventory().addItem(new ItemStack(compactBlockType, totalBlocks));

        sendCompactMessage(player, itemInHand.getType(), compactBlockType, amount, totalBlocks);

        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);

        if (leftoverItems > 0) {
            player.getInventory().addItem(new ItemStack(itemInHand.getType(), leftoverItems));
        }
    }


    private void sendCompactMessage(Player player, Material originalItemType, Material compactBlockType, int leftoverItems, int totalBlocks) {
        player.sendMessage(
                "",
                "  §eVocê compactou " + leftoverItems + " " + originalItemType.name() +
                " em " + totalBlocks + " " + compactBlockType.name() + ".",
                ""
        );
        Location headLocation = player.getEyeLocation().add(0, 1, 0);
        player.spawnParticle(Particle.HEART, headLocation, 100);

    }
}
