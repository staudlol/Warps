package io.github.staudlol.warps.warp;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Data
public class Warp {

    private final String name;
    private final Location spawn;

    private String worldName;

    private int x;
    private int y;
    private int z;
    private float yaw;
    private float pitch;

    /**
     * Constructor for creating a new {@link Warp}
     *
     * @param name the name of the warp.
     * @param spawn the spawn of the warp.
     */

    public Warp(String name, Location spawn) {
        this.name = name;
        this.spawn = spawn;
    }

    /**
     * Get the location of a {@link Warp}
     *
     * @return the location of the warp.
     */

    public Location getSpawn() {
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    /**
     * Update the {@link Location} of the {@link Warp}
     *
     * This sets all the individual fields to the
     * data of the location.
     *
     * @param location the location to update it to.
     */

    public void setSpawn(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.worldName = location.getWorld().getName();
    }

}
