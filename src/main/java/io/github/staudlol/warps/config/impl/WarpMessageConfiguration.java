package io.github.staudlol.warps.config.impl;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;

public class WarpMessageConfiguration extends Configuration {

    @Configurable(path = "database.mongodb.hostname")
    public static String MONGO_HOSTNAME = "";

    @Configurable(path = "database.mongodb.port")
    public static Integer MONGO_PORT = 27017;

    @Configurable(path = "warp.already_exists")
    public static String WARP_ALREADY_EXISTS = ChatColor.RED + "A warp with that name already exists.";

    @Configurable(path = "warp.created")
    public static String WARP_CREATED = ChatColor.GREEN + "You have created a new warp.";

    @Configurable(path = "warp.deleted")
    public static String WARP_DELETED = ChatColor.RED + "You have deleted a warp.";

    @Configurable(path = "warp.location")
    public static String WARP_LOCATION = ChatColor.GREEN + "You have updated the location of a warp.";

    @SneakyThrows
    public WarpMessageConfiguration(ConfigurationFile file) {
        super(file);

        super.load();
        super.save();
    }
}
