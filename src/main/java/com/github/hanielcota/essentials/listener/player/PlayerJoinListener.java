package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.utils.OraxenHook;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendPlayerListHeaderAndFooter(
                Component.text("\n\n" + OraxenHook.getGlyphChar("logo") + "\n\n"),
                Component.text("\n" + " ┟ §bᴅɪꜱᴄᴏʀᴅ.ᴀɴᴋᴀʀᴇꜱ.ᴄᴏᴍ ⛞ §fʟᴏᴊᴀ.§eᴀɴᴋᴀʀᴇꜱ§f.ᴄᴏᴍ§f ⤠ §aᴊᴏɢᴀᴅᴏʀᴇꜱ ᴏɴʟɪɴᴇ §f"
                        + Bukkit.getOnlinePlayers().size() + "\n"));

        if (player.hasPermission("ankares.tags.master")) {
            String novoNick = "ꐐ " + player.getName();

            player.displayName(Component.text(novoNick));
            player.playerListName(Component.text(novoNick));
        }

        event.joinMessage(null);
    }
}
