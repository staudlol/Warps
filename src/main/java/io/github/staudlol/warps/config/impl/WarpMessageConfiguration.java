package io.github.staudlol.warps.config.impl;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import lombok.SneakyThrows;

public class WarpMessageConfiguration extends Configuration {

    @SneakyThrows
    public WarpMessageConfiguration(ConfigurationFile file) {
        super(file);

        super.load();
        super.save();
    }
}
