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

    public void registerListeners(EssentialsPlugin plugin) {
        registerSpecificListeners(plugin);
        List<Listener> listeners = createListeners();

        if (listeners.isEmpty()) {
            log.warning("No listeners created. Registration skipped.");
            return;
        }

        registerGenericListeners(plugin, listeners);
        log.info("§aSuccessfully registered " + listeners.size() + " listeners!");
    }

    private void registerSpecificListeners(EssentialsPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new PlayerVanishListener(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new AdminListener(plugin), plugin);
    }

    private void registerGenericListeners(EssentialsPlugin plugin, List<Listener> listeners) {
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }

    private List<Listener> createListeners() {
        return Arrays.asList(
                new VoidListener(),
                new VehicleListener(),
                new SmeltSnowListener(),
                new NaturalSpawnListener(),
                new PlantationDamageListener(),
                new FreezeListener(),
                new FoodWorldListener(),
                new FireListener(),
                new FireEntityListener(),
                new ExplodeItemListener(),
                new DecayListener(),
                new WeatherListener(),
                new FallDamageListener(),
                new ExplosionsListener(),
                new PlayerJoinListener(),
                new PlayerDeathListener(),
                new JumpPlantationListener(),
                new PlayerRespawnListener()
        );
    }
}
