package com.github.hanielcota.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.essentials.commands.*;
import com.github.hanielcota.essentials.listener.PlayerJoinListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        PaperCommandManager commandManager = new PaperCommandManager(this);
        registerCommands(commandManager);
        registerListeners(new PlayerJoinListener());
    }

    private void registerCommands(PaperCommandManager commandManager) {
        registerCommand(commandManager, new GamemodeCommand());
        registerCommand(commandManager, new TeleportCommand());
        registerCommand(commandManager, new FlyCommand());
        registerCommand(commandManager, new CompassCommand());
        registerCommand(commandManager, new GiveCommand());
        registerCommand(commandManager, new HealCommand());
        registerCommand(commandManager, new KillCommand());
        registerCommand(commandManager, new ClearChatCommand());
        registerCommand(commandManager, new NigthCommand());
        registerCommand(commandManager, new DayCommand());
        registerCommand(commandManager, new HatCommand());
        registerCommand(commandManager, new TopCommand());
        registerCommand(commandManager, new SpeedCommand());
        registerCommand(commandManager, new LookInventoryCommand());
        registerCommand(commandManager, new ClearCommand());
        registerCommand(commandManager, new CompactCommand());
    }

    private void registerCommand(PaperCommandManager commandManager, BaseCommand command) {
        commandManager.registerCommand(command);
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }

        getServer().getLogger().info("Registered listeners...");
    }
}
