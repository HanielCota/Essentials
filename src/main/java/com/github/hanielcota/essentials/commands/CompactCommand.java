package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@CommandAlias("compactar")
public class CompactCommand extends BaseCommand {

    @Default
    @CommandCompletion("@materials")
    @CommandPermission("essentials.compactar")
    public void onCompact(Player player, String[] args) {
        if (args.length > 1) {
            player.sendMessage("§cUso incorreto. Utilize /compactar segurando o item que deseja compactar.");
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        int amount = getAmount(itemInHand, player.getInventory());

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

        Material compactBlockType = getCompactBlockType(itemInHand.getType());
        if (compactBlockType == null) {
            player.sendMessage("§cVocê não pode compactar este item.");
            return;
        }

        int totalBlocks = amount / 9;
        int leftoverItems = amount % 9;

        player.getInventory().remove(itemInHand.getType());
        player.getInventory().addItem(new ItemStack(compactBlockType, totalBlocks));

        sendCompactMessage(player, itemInHand.getType(), compactBlockType, leftoverItems, totalBlocks);

        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);

        if (leftoverItems > 0) {
            player.getInventory().addItem(new ItemStack(itemInHand.getType(), leftoverItems));
        }
    }

    private Material getCompactBlockType(Material itemType) {
        return switch (itemType) {
            case SLIME_BALL -> Material.SLIME_BLOCK;
            case GOLD_NUGGET -> Material.GOLD_INGOT;
            case LAPIS_LAZULI -> Material.LAPIS_BLOCK;
            case IRON_INGOT -> Material.IRON_BLOCK;
            case GOLD_INGOT -> Material.GOLD_BLOCK;
            case DIAMOND -> Material.DIAMOND_BLOCK;
            case COAL -> Material.COAL_BLOCK;
            case EMERALD -> Material.EMERALD_BLOCK;
            case REDSTONE -> Material.REDSTONE_BLOCK;
            default -> null;
        };
    }

    private void sendCompactMessage(Player player, Material originalItemType, Material compactBlockType, int leftoverItems, int totalBlocks) {
        player.sendMessage("", "   §eVocê compactou " + leftoverItems + " " + originalItemType.name() + " em " + totalBlocks + " "
                               + compactBlockType.name() + ".", "");
    }

    private boolean equals(ItemStack one, ItemStack two) {
        return one.isSimilar(two);
    }

    private int getAmount(ItemStack item, Inventory inventory) {
        if (!inventory.contains(item.getType())) {
            return 0;
        }

        return inventory.all(item.getType()).values().stream()
                .filter(iStack -> equals(iStack, item))
                .mapToInt(ItemStack::getAmount)
                .sum();
    }
}
