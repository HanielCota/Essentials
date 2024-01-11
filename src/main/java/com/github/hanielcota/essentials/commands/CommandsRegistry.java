package com.github.hanielcota.essentials.commands;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.essentials.commands.impl.*;
import lombok.extern.java.Log;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

@Log
public class CommandsRegistry {

    public CommandsRegistry(JavaPlugin plugin) {
        Logger logger = plugin.getLogger();
        try {

            PaperCommandManager manager = new PaperCommandManager(plugin);

            if (!registerCommands(manager)) {
                logger.warning("No commands registered. Registration skipped.");
                return;
            }

            manager.getLocales().setDefaultLocale(Locales.PORTUGUESE);
            logger.info("§aSuccessfully registered commands!");
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "§cCould not register commands.", e);
        }
    }

    private boolean registerCommands(PaperCommandManager manager) {
        try {
            manager.registerCommand(new GamemodeCommand());
            manager.registerCommand(new KickCommand());
            manager.registerCommand(new HatCommand());
            manager.registerCommand(new TeleportCommand());
            manager.registerCommand(new FlyCommand());
            manager.registerCommand(new KillCommand());
            manager.registerCommand(new HealCommand());
            manager.registerCommand(new NigthCommand());
            manager.registerCommand(new ClearCommand());
            manager.registerCommand(new DayCommand());
            manager.registerCommand(new KillMobsCommand());
            manager.registerCommand(new ClearChatCommand());
            manager.registerCommand(new SpeedCommand());
            manager.registerCommand(new CompactCommand());
            manager.registerCommand(new CompassCommand());
            manager.registerCommand(new LookInventoryCommand());
            manager.registerCommand(new GiveCommand());
            manager.registerCommand(new TopCommand());
            manager.registerCommand(new GodCommand());

            return true;
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "§cError registering commands.", e);
            return false;
        }
    }
}
