package com.github.hanielcota.essentials.listener;

import com.github.hanielcota.essentials.EssentialsPlugin;
import com.github.hanielcota.essentials.listener.player.*;
import com.github.hanielcota.essentials.listener.server.*;
import com.github.hanielcota.essentials.utils.admin.AdminUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

@Log
@RequiredArgsConstructor
public class ListenerRegistry {

    private final List<ListenerFactory> listenerFactories = Arrays.asList(
            VoidListener::new,
            VehicleListener::new,
            SmeltSnowListener::new,
            NaturalSpawnListener::new,
            PlantationDamageListener::new,
            FreezeListener::new,
            FoodWorldListener::new,
            FireListener::new,
            FireEntityListener::new,
            ExplodeItemListener::new,
            DecayListener::new,
            WeatherListener::new,
            FallDamageListener::new,
            ExplosionsListener::new,
            PlayerJoinListener::new,
            PlayerDeathListener::new,
            JumpPlantationListener::new,
            PlayerRespawnListener::new);

    public void registerListeners(EssentialsPlugin plugin) {
        val pluginManager = Bukkit.getPluginManager();
        List<Listener> listeners = createListeners();

        if (listeners.isEmpty()) {
            log.warning("No listeners created. Registration skipped.");
            return;
        }

        pluginManager.registerEvents(new PlayerVanishListener(plugin), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(plugin), plugin);
        pluginManager.registerEvents(new AdminListener(plugin), plugin);

        listeners.forEach(listener -> pluginManager.registerEvents(listener, plugin));
        log.info("§aSuccessfully registered " + listeners.size() + " listeners!");
    }

    private List<Listener> createListeners() {
        return listenerFactories.stream().map(ListenerFactory::createListener).toList();
    }
}
