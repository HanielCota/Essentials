package com.github.hanielcota.essentials;

import com.github.hanielcota.essentials.commands.CommandsRegistry;
import com.github.hanielcota.essentials.listener.ListenerRegistry;
import com.github.hanielcota.essentials.repository.WarpRepository;
import com.github.hanielcota.essentials.repository.impl.YamlWarpRepository;
import com.github.hanielcota.essentials.utils.admin.AdminUtils;
import com.github.hanielcota.essentials.utils.menus.SimplixManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EssentialsPlugin extends JavaPlugin {

    private AdminUtils adminUtils;
    private WarpRepository warpRepository;

    @Override
    public void onEnable() {
        registerCommands();
        setupConfig();
        initializeAdminUtils();
        registerListeners();

        SimplixManager.registerPlugin(this);
    }

    private void registerCommands() {
        new CommandsRegistry(this);
    }

    private void setupConfig() {
        saveDefaultConfig();
    }

    private void initializeAdminUtils() {
        adminUtils = new AdminUtils();
    }

    private void registerListeners() {
        ListenerRegistry listenerRegistry = new ListenerRegistry();
        listenerRegistry.registerListeners(this);
    }

    public WarpRepository getWarpRepository() {
        if (warpRepository == null) {
            initializeWarpRepository();
        }
        return warpRepository;
    }

    private void initializeWarpRepository() {
        warpRepository = new YamlWarpRepository(this, getConfig());
    }
}
