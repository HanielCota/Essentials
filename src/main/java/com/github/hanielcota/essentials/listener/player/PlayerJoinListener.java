package com.github.hanielcota.essentials.listener.player;

import com.github.hanielcota.essentials.utils.player.PlayerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class PlayerJoinListener implements Listener {


    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        PlayerUtils.processJoin(event.getPlayer());
        event.joinMessage(null);
    }
}