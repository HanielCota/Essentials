package com.github.hanielcota.essentials.utils.player;

import com.github.hanielcota.essentials.utils.oraxen.OraxenHook;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@UtilityClass
public class PlayerUtils {
    public void processJoin(Player player) {
        sendPlayerListHeaderAndFooter(player);

        if (player.hasPermission("ankares.tags.master")) {
            applyMasterTag(player);
        }

        teleportPlayerToSpawn(player);
    }

    private void sendPlayerListHeaderAndFooter(Player player) {
        Component header = Component.text("\n\n" + OraxenHook.getGlyphChar("logo") + "\n\n");
        Component footer = Component.text("\n" + " " + "┟ §bᴅɪꜱᴄᴏʀᴅ.ᴀɴᴋᴀʀᴇꜱ.ᴄᴏᴍ "
                + "⛞ §fʟᴏᴊᴀ.§eᴀɴᴋᴀʀᴇꜱ§f.ᴄᴏᴍ§f "
                + "⤠ §aᴊᴏɢᴀᴅᴏʀᴇꜱ ᴏɴʟɪɴᴇ §f"
                + Bukkit.getOnlinePlayers().size()
                + "\n");

        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    private void applyMasterTag(Player player) {
        String novoNick = "ꐐ " + player.getName();

        player.displayName(Component.text(novoNick));
        player.playerListName(Component.text(novoNick));
    }

    public void teleportPlayerToSpawn(Player player) {
        World world = Bukkit.getWorld("world");

        if (world == null) {
            Bukkit.getLogger().warning("The world 'world' was not found!");
            return;
        }

        Location spawnLocation = new Location(world, 453.452, 130, 34.607);

        player.teleportAsync(spawnLocation);
    }
}