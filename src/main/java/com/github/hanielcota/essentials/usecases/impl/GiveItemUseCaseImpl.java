package com.github.hanielcota.essentials.usecases.impl;

import com.github.hanielcota.essentials.usecases.GiveItemUseCase;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GiveItemUseCaseImpl implements GiveItemUseCase {

    @Override
    public boolean giveItem(Player sender, Player target, String item, int amount) {
        if (!hasPermission(sender, item)) {
            sender.sendMessage("§cVocê não tem permissão para dar esse item.");
            return false;
        }

        Material material = parseMaterial(item);
        if (material == null) {
            sender.sendMessage("§cItem inválido. Use /give <item> <quantidade>");
            return false;
        }

        ItemStack itemStack = new ItemStack(material, amount);

        if (!hasInventorySpace(target.getInventory(), itemStack)) {
            sender.sendMessage("§cO inventário de " + target.getName() + " está cheio.");
            return false;
        }

        target.getInventory().addItem(itemStack);
        target.sendMessage("§aVocê recebeu " + amount + " " + itemStack.getType().name() + " de " + sender.getName() + ".");
        return true;
    }

    private boolean hasPermission(Player player, String item) {
        String permission = "essentials.give." + item.toLowerCase();
        return player.hasPermission(permission);
    }

    private Material parseMaterial(String item) {
        try {
            return Material.valueOf(item.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private boolean hasInventorySpace(Inventory inventory, ItemStack itemStack) {
        int remainingSpace = 0;

        for (ItemStack stack : inventory.getContents()) {
            if (stack == null || stack.getType() == Material.AIR) {
                remainingSpace += itemStack.getMaxStackSize();
                break;
            }

            if (stack.isSimilar(itemStack) && stack.getAmount() < stack.getMaxStackSize()) {
                remainingSpace += stack.getMaxStackSize() - stack.getAmount();
                break;
            }
        }

        return remainingSpace >= itemStack.getAmount();
    }
}
