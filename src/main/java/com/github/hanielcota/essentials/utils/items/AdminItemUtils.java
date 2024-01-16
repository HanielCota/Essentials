package com.github.hanielcota.essentials.utils.items;

import com.github.hanielcota.essentials.utils.items.ItemBuilder;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class AdminItemUtils {
    public ItemStack createKillAuraItem() {
        return new ItemBuilder(Material.SLIME_BALL)
                .setDisplayName(Component.text("§aKill Aura"))
                .setLore("§7Clique com botão direito em cima do player",
                        "§7para poder ficar invisível e visível por 1 segundo.")
                .build();
    }

    public ItemStack createAntiKBItem() {
        return new ItemBuilder(Material.STICK)
                .setDisplayName(Component.text("§aAnti KB"))
                .setLore("§7Clique para empurrar o player.")
                .build();
    }

    public ItemStack createJailItem() {
        return new ItemBuilder(Material.IRON_BARS)
                .setDisplayName(Component.text("§aJail"))
                .setLore("§7Prenda o player em uma jaula.")
                .build();
    }
}
