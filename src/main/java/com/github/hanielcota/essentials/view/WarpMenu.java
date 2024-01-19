package com.github.hanielcota.essentials.view;

import com.github.hanielcota.essentials.repository.WarpRepository;
import com.github.hanielcota.essentials.repository.impl.WarpLocation;
import com.github.hanielcota.essentials.utils.ItemBuilder;
import com.github.hanielcota.essentials.utils.menus.SimplixMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WarpMenu extends SimplixMenu {

    private final WarpRepository warpRepository;

    public WarpMenu(WarpRepository warpRepository) {
        super(54, "Warps");
        this.warpRepository = warpRepository;
    }

    public void openWarpMenu(Player player) {
        List<String> warpNames = warpRepository.getAllWarps();

        if (warpNames.isEmpty()) {
            player.sendMessage(Component.text("§cNão há warps disponíveis."));
            return;
        }

        int[] slots = {14, 15, 16, 23, 24, 25, 32, 33, 34};
        int slotIndex = 0;

        for (String warpName : warpNames) {
            if (slotIndex >= slots.length) {
                break;
            }

            int currentSlot = slots[slotIndex];
            ItemStack warpItem = createWarpItem(warpName);

            setItem(currentSlot, warpItem, e -> {
                warpPlayerToLocation(player, warpName);
            });

            slotIndex++;
        }

        open(player);
    }

    private ItemStack createWarpItem(String warpName) {
        return new ItemBuilder(Material.SLIME_BALL)
                .setDisplayName(Component.text("§a" + warpName))
                .build();
    }

    private void warpPlayerToLocation(Player player, String warpName) {
        WarpLocation warpLocation = warpRepository.getWarp(warpName);

        if (warpLocation == null) {
            player.sendMessage(Component.text("§cFalha ao obter a localização da warp."));
            return;
        }

        player.teleportAsync(warpLocation.getLocation());
        player.sendMessage(Component.text("§aTeleportado para a warp '" + warpName + "'."));
    }
}
