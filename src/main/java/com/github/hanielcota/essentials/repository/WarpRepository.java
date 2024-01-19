package com.github.hanielcota.essentials.repository;

import com.github.hanielcota.essentials.repository.impl.WarpLocation;

import java.util.List;

public interface WarpRepository {
    void createWarp(String warpName, WarpLocation location);

    WarpLocation getWarp(String warpName);

    void updateWarp(String warpName, WarpLocation location);
    List<String> getAllWarps();
    void deleteWarp(String warpName);
}
