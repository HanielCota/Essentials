package com.github.hanielcota.essentials.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
@UtilityClass
public class CompactUtils {

    public Material getCompactBlockType(Material itemType) {
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

    public int getAmount(ItemStack item, Inventory inventory) {
        if (!inventory.contains(item.getType())) {
            return 0;
        }

        return inventory.all(item.getType()).values().stream()
                .filter(iStack -> iStack.equals(item))
                .mapToInt(ItemStack::getAmount)
                .sum();
    }
}
