package com.github.hanielcota.essentials.utils;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

@Getter
public class ConfigUtil {

    private final Plugin plugin;
    private final String fileName;
    private final File configFile;
    private YamlConfiguration config;

    public ConfigUtil(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.configFile = new File(plugin.getDataFolder(), fileName);

        if (configFile.exists()) {
            return;
        }

        plugin.saveResource(fileName, false);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Erro ao salvar o arquivo de configuração", e);
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
