package io.github.staudlol.warps;

import io.github.staudlol.warps.config.WarpsConfigurationFile;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class WarpsPlugin extends JavaPlugin {

    @SneakyThrows
    @Override
    public void onEnable() {
        new WarpMessageConfiguration(new WarpsConfigurationFile(new File(this.getDataFolder(), "messages.yml")));
    }

    @Override
    public void onDisable() {

    }
}
