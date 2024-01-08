package com.github.hanielcota.essentials.usecases.impl;


import com.github.hanielcota.essentials.usecases.HealPlayerUseCase;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class HealPlayerUseCaseImpl implements HealPlayerUseCase {

    @Override
    public boolean healPlayer(Player player) {
        if (!hasPermission(player)) {
            player.sendMessage("§cVocê não tem permissão para se curar.");
            return false;
        }

        if (player.getHealth() == getMaxHealth(player)) {
            player.sendMessage("§cVocê já está com a saúde completa.");
            return false;
        }

        player.setHealth(getMaxHealth(player));
        player.setFoodLevel(20);
        player.setSaturation(20.0f);
        return true;
    }

    private double getMaxHealth(Player player) {
        Attribute attribute = Attribute.GENERIC_MAX_HEALTH;
        AttributeInstance attributeInstance = player.getAttribute(attribute);

        if (attributeInstance != null) {
            return attributeInstance.getValue();
        }

        return 20.0;
    }

    private boolean hasPermission(Player player) {
        String permission = "essentials.heal";
        if (!player.hasPermission(permission)) {
            player.sendMessage("§cVocê não tem permissão para se curar.");
            return false;
        }
        return true;
    }
}
