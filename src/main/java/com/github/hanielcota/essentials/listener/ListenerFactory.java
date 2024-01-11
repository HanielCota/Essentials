package com.github.hanielcota.essentials.listener;

import org.bukkit.event.Listener;

public interface ListenerFactory {
    Listener createListener();
}