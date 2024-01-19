package com.github.hanielcota.essentials.repository.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@Data
@Slf4j

public class WarpLocation {

    private String world;
    private Location location;

    public WarpLocation(String world, double x, double y, double z, float pitch, float yaw) {
        this.world = world;
        World bukkitWorld = Bukkit.getWorld(world);
        this.location = new Location(bukkitWorld, x, y, z, yaw, pitch);
    }

    public static WarpLocation fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length == 6) {
            try {
                String world = parts[0];
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                float pitch = Float.parseFloat(parts[4]);
                float yaw = Float.parseFloat(parts[5]);

                return new WarpLocation(world, x, y, z, pitch, yaw);
            } catch (NumberFormatException e) {
                log.error("Falha ao converter String para WarpLocation", e);
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f,%.2f",
                world,
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getYaw());
    }
}
