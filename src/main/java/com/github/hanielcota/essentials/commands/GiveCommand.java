package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@CommandAlias("give")
public class GiveCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandPermission("essentials.give")
    public void onCommand(Player sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage("§cUtilize /give <nick> <item> <quantidade> para enviar um item a um jogador.");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String targetName = args[0];

        if (target == null || !target.isOnline()) {
            sender.sendMessage("§cJogador '" + targetName + "' não encontrado ou offline.");
            return;
        }

        int quantity = (args.length == 3) ? parseInteger(args[2]) : 1;

        Material material = parseMaterial(args[1]);
        if (material == null) {
            sender.sendMessage("§cItem inválido. Use /give <item> <quantidade>");
            return;
        }

        ItemStack itemStack = new ItemStack(material, quantity);

        if (!hasInventorySpace(target.getInventory(), itemStack)) {
            sender.sendMessage("§cO inventário de " + target.getName() + " está cheio.");
            return;
        }

        if (!sender.equals(target)) {
            target.getInventory().addItem(itemStack);
            sender.sendMessage("§eEnviando " + quantity + "x " + itemStack.getType().name() + " para " + target.getName() + ".");
            target.sendMessage("§aVocê recebeu x" + quantity + " " + itemStack.getType().name() + " de " + sender.getName() + ".");
            return;
        }

        sender.getInventory().addItem(itemStack);
        sender.sendMessage("§eEnviando " + quantity + "x " + itemStack.getType().name() + " para " + target.getName() + ".");
    }

    private int parseInteger(String quantityString) {
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
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
