package com.github.hanielcota.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.essentials.commands.*;
import com.github.hanielcota.essentials.controller.*;
import com.github.hanielcota.essentials.controller.impl.*;
import com.github.hanielcota.essentials.listener.PlayerJoinListener;
import com.github.hanielcota.essentials.usecases.*;
import com.github.hanielcota.essentials.usecases.impl.*;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class EssentialsPlugin extends JavaPlugin {

    private GamemodeController gamemodeController;
    private TeleportController teleportController;
    private FlyController flyController;
    private GiveController giveController;
    private HealController healController;
    private KillController killController;

    private ChangeGamemodeUseCase gamemodeUseCase;
    private TeleportUseCase teleportUseCase;
    private ToggleFlyUseCase flyUseCase;
    private GiveItemUseCase giveItemUseCase;
    private HealPlayerUseCase healPlayerUseCase;
    private KillPlayerUseCase killPlayerUseCase;

    @Override
    public void onEnable() {
        initializeUseCases();
        initializeControllers();

        PaperCommandManager commandManager = new PaperCommandManager(this);
        registerCommands(commandManager);
        registerListeners(new PlayerJoinListener());
    }

    private void initializeUseCases() {
        gamemodeUseCase = new ChangeGamemodeUseCaseImpl();
        teleportUseCase = new TeleportPlayerUseCase();
        flyUseCase = new ToggleFlyUseCaseImpl();
        giveItemUseCase = new GiveItemUseCaseImpl();
        healPlayerUseCase = new HealPlayerUseCaseImpl();
        killPlayerUseCase = new KillPlayerUseCaseImpl();
    }

    private void initializeControllers() {
        gamemodeController = new GamemodeControllerImpl(gamemodeUseCase);
        teleportController = new TeleportControllerImpl(teleportUseCase);
        flyController = new FlyControllerImpl(flyUseCase);
        giveController = new GiveControllerImpl(giveItemUseCase);
        healController = new HealControllerImpl(healPlayerUseCase);
        killController = new KillControllerImpl(killPlayerUseCase);
    }

    private void registerCommands(PaperCommandManager commandManager) {
        registerCommand(commandManager, new GamemodeCommand(gamemodeController));
        registerCommand(commandManager, new TeleportCommand(teleportController));
        registerCommand(commandManager, new FlyCommand(flyController));
        registerCommand(commandManager, new CompassCommand());
        registerCommand(commandManager, new GiveCommand(giveController));
        registerCommand(commandManager, new HealCommand(healController));
        registerCommand(commandManager, new KillCommand(killController));
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
