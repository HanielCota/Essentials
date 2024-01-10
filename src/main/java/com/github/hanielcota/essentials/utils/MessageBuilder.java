package com.github.hanielcota.essentials.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class MessageBuilder {

    private TextComponent.Builder textBuilder = Component.text();
    private TextComponent.Builder eventTextBuilder = Component.text();

    public MessageBuilder clickEvent(ClickEvent.Action action, String command) {
        TextComponent eventText = Component.text().clickEvent(ClickEvent.clickEvent(action, command)).build();
        eventTextBuilder.append(eventText);
        return this;
    }

    public MessageBuilder message(String message) {
        TextComponent messageText = Component.text(message);
        textBuilder.append(messageText);
        return this;
    }

    public MessageBuilder appendClickable(String clickableText) {
        TextComponent eventText = eventTextBuilder.build();
        eventTextBuilder = Component.text().append(eventText.toBuilder().content(clickableText));
        return this;
    }

    public TextComponent build() {
        return textBuilder.build();
    }

    public void sendToPlayer(Player player) {
        player.sendMessage(build());
    }
}
