package com.github.hanielcota.essentials;

import com.github.hanielcota.essentials.commands.CommandsRegistry;
import com.github.hanielcota.essentials.listener.ListenerRegistry;
import com.github.hanielcota.essentials.listener.player.AdminListener;
import com.github.hanielcota.essentials.utils.admin.AdminUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EssentialsPlugin extends JavaPlugin {

    private AdminUtils adminUtils;

    @Override
    public void onEnable() {
        new CommandsRegistry(this);

        adminUtils = new AdminUtils();

        ListenerRegistry listenerRegistry = new ListenerRegistry();
        listenerRegistry.registerListeners(this);

    }
}
