package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.utils.OraxenHook;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        sendPlayerListHeaderAndFooter(player);

        if (player.hasPermission("ankares.tags.master")) {
            applyMasterTag(player);
        }

        teleportPlayerToSpawn(player);

        event.joinMessage(null);
    }

    private void sendPlayerListHeaderAndFooter(Player player) {
        player.sendPlayerListHeaderAndFooter(
                Component.text("\n\n" + OraxenHook.getGlyphChar("logo") + "\n\n"),
                Component.text("\n" + " ┟ §bᴅɪꜱᴄᴏʀᴅ.ᴀɴᴋᴀʀᴇꜱ.ᴄᴏᴍ ⛞ §fʟᴏᴊᴀ.§eᴀɴᴋᴀʀᴇꜱ§f.ᴄᴏᴍ§f ⤠ §aᴊᴏɢᴀᴅᴏʀᴇꜱ ᴏɴʟɪɴᴇ §f"
                               + Bukkit.getOnlinePlayers().size() + "\n"));
    }

    private void applyMasterTag(Player player) {
        String novoNick = "ꐐ " + player.getName();
        player.displayName(Component.text(novoNick));
        player.playerListName(Component.text(novoNick));
    }

    private void teleportPlayerToSpawn(Player player) {
        World world = Bukkit.getWorld("world");

        if (world == null) {
            Bukkit.getLogger().warning("The world 'world' was not found!");
            return;
        }

        Location spawnLocation = new Location(world, 453.452, 130, 34.607);

        player.teleportAsync(spawnLocation);
    }
}
