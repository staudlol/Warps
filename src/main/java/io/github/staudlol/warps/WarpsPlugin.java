package io.github.staudlol.warps;

import io.github.staudlol.warps.config.WarpsConfigurationFile;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.module.ModuleHandler;
import io.github.staudlol.warps.warp.WarpModule;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

@Getter
public class WarpsPlugin extends JavaPlugin {

    private final ModuleHandler handler = new ModuleHandler();

    @SneakyThrows
    @Override
    public void onEnable() {
        new WarpMessageConfiguration(new WarpsConfigurationFile(new File(this.getDataFolder(), "messages.yml")));

        this.handler.register(new WarpModule());
        this.handler.loadAll();
    }

    @Override
    public void onDisable() {
        this.handler.unloadAll();
    }
}
