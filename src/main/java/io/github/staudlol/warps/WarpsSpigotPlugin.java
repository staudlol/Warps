package io.github.staudlol.warps;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.menu.MenuHandler;
import io.github.staudlol.warps.command.WarpCommands;
import io.github.staudlol.warps.config.WarpsConfigurationFile;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.module.ModuleHandler;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import io.github.staudlol.warps.warp.adapter.WarpTypeAdapter;
import io.github.staudlol.warps.warp.handler.WarpHelpHandler;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

@Getter
public class WarpsSpigotPlugin extends JavaPlugin {

    private final ModuleHandler handler = new ModuleHandler();

    @SneakyThrows
    @Override
    public void onEnable() {
        new WarpMessageConfiguration(new WarpsConfigurationFile(new File(this.getDataFolder(), "messages.yml")));

        this.handler.register(new WarpModule());
        this.handler.loadAll();

        new MenuHandler(this);

        final CommandHandler commandHandler = new BukkitCommandHandler("warp");

        commandHandler.setHelpHandler(new WarpHelpHandler());

        commandHandler.registerTypeAdapter(Warp.class, new WarpTypeAdapter(this.handler.find(WarpModule.class)));
        commandHandler.registerCommand(new WarpCommands(this.handler.find(WarpModule.class)));
    }

    @Override
    public void onDisable() {
        this.handler.unloadAll();
    }
}
