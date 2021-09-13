package io.github.staudlol.warps.config;

import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.FileNotFoundException;

public class WarpsConfigurationFile extends BukkitConfigurationFile {

    public WarpsConfigurationFile(File file) throws FileNotFoundException {
        super(ensureFileExistence(file), YamlConfiguration.loadConfiguration(file));
    }

    @SneakyThrows
    private static File ensureFileExistence(File file) {
        if (!file.getParentFile().exists() && file.getParentFile().mkdirs()) {
            System.out.println("Created parent files.");
        }

        if (!file.exists() && file.createNewFile()) {
            System.out.println("Creating new configuration with name \"" + file.getName() + "\"");
        }
        return file;
    }
}
