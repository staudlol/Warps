package io.github.staudlol.warps.config.impl;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import lombok.SneakyThrows;

public class WarpMessageConfiguration extends Configuration {

    @Configurable(path = "database.mongodb.hostname")
    public static String MONGO_HOSTNAME = "";

    @Configurable(path = "database.mongodb.port")
    public static Integer MONGO_PORT = 27017;

    @SneakyThrows
    public WarpMessageConfiguration(ConfigurationFile file) {
        super(file);

        super.load();
        super.save();
    }
}
