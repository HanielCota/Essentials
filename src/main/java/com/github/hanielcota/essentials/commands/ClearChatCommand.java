package com.github.hanielcota.essentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.github.hanielcota.essentials.utils.MessageBuilder;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;

@CommandAlias("clearchat|cc")
public class ClearChatCommand extends BaseCommand {

    @Default
    public void onCommand(Player player) {

        new MessageBuilder()
                .clickEvent(ClickEvent.Action.OPEN_URL, "http://localhost")
                .message("§aClique ")
                .appendClickable("§a§lAQUI")
                .message("§a para executar.")
                .sendToPlayer(player);
    }
}
