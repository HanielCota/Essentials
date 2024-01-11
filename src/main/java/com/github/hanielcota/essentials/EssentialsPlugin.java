package com.github.hanielcota.essentials;

import com.github.hanielcota.essentials.commands.CommandsRegistry;
import com.github.hanielcota.essentials.listener.ListenerRegistry;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        new CommandsRegistry(this);

        final ListenerRegistry listenerRegistry = new ListenerRegistry();
        listenerRegistry.registerListeners(this);
    }
}
