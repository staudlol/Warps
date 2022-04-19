package io.github.staudlol.warps.config.impl;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import io.github.staudlol.warps.util.CC;
import lombok.SneakyThrows;

public class WarpMessageConfiguration extends Configuration {

    @Configurable(path = "database.mongodb.hostname")
    public static String MONGO_HOSTNAME = "127.0.0.1";

    @Configurable(path = "database.mongodb.port")
    public static Integer MONGO_PORT = 27017;

    @Configurable(path = "warp.already_exists")
    public static String WARP_ALREADY_EXISTS = CC.translate("&cA warp with that name already exists.");

    @Configurable(path = "warp.created")
    public static String WARP_CREATED = CC.translate("&aYou have created a new warp");

    @Configurable(path = "warp.deleted")
    public static String WARP_DELETED = CC.translate("&cYou have deleted a warp");

    @Configurable(path = "warp.location")
    public static String WARP_LOCATION = CC.translate("&aYou have updated the location of a warp.");

    @Configurable(path = "warp.teleport")
    public static String WARP_TELEPORT = CC.translate("&aYou have teleported to the warp.");

    @Configurable(path = "warp.permission")
    public static String WARP_PERMISSION = CC.translate("&aYou have updated the permission of a warp");

    @SneakyThrows
    public WarpMessageConfiguration(ConfigurationFile file) {
        super(file);

        super.load();
        super.save();
    }
}
