package io.github.staudlol.warps.warp;

import io.github.staudlol.warps.WarpsConstants;
import io.github.staudlol.warps.module.Module;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WarpModule implements Module {

    private final List<Warp> warps = new ArrayList<>();

    @Override
    public void load() {
        WarpsConstants.WARP_STORAGE
                .fetchAllEntries()
                .whenCompleteAsync((warps, throwable) -> this.warps.addAll(warps.values()));
    }

    @Override
    public void unload() {
        for (Warp warp : this.warps) {
            this.saveWarps(warp);
        }
    }

    /**
     * Register a new {@link Warp}
     *
     * @param warp the warp to register.
     */

    public void registerWarp(Warp warp) {
        this.warps.add(warp);
    }

    /**
     * Remove a {@link Warp)
     *
     * @param warp the warp to delete.
     */

    public void deleteWarp(Warp warp) {
        this.warps.remove(warp);

        WarpsConstants.WARP_STORAGE.removeEntry(warp.getName());
    }

    /**
     * Save warps to the database.
     *
     * @param warp the warp to save.
     */

    public void saveWarps(Warp warp) {
        if (warp == null) {
            throw new IllegalArgumentException("The provided warp to save is null");
        }
    }

    /**
     * Find a {@link Warp} by a name.
     *
     * @param name the name.
     * @return the found warp or null.
     */

    public Warp find(String name) {
        return this.warps.stream()
                .filter(warp -> warp.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
