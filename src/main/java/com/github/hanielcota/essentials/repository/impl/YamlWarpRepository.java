package com.github.hanielcota.essentials.repository.impl;


import com.github.hanielcota.essentials.repository.WarpRepository;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class YamlWarpRepository implements WarpRepository {

    private final JavaPlugin plugin;
    private final FileConfiguration config;

    @Override
    public void createWarp(String warpName, WarpLocation location) {
        config.set("warps." + warpName, location.toString());
        plugin.saveConfig();
    }

    @Override
    public WarpLocation getWarp(String warpName) {
        String locationString = config.getString("warps." + warpName);
        if (locationString != null) {
            return WarpLocation.fromString(locationString);
        }
        return null;
    }

    @Override
    public void updateWarp(String warpName, WarpLocation location) {
        if (getWarp(warpName) != null) {
            createWarp(warpName, location);
        }
    }

    @Override
    public List<String> getAllWarps() {
        if (config.isConfigurationSection("warps")) {
            ConfigurationSection warpsSection = config.getConfigurationSection("warps");

            if (warpsSection != null) {
                Set<String> warpKeys = warpsSection.getKeys(false);
                return new ArrayList<>(warpKeys);
            }
        }

        return new ArrayList<>();
    }


    @Override
    public void deleteWarp(String warpName) {
        config.set("warps." + warpName, null);
        plugin.saveConfig();
    }
}