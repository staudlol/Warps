package io.github.staudlol.warps.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Help;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.command.exception.ConditionFailedException;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarpCommands {

    private final WarpModule warpModule;

    @Help
    @Command(label = "warp", permission = "warp.command.help", description = "Displays the help message")
    public void help(BukkitCommandExecutor executor) {

    }

    @Subcommand(label = "create", parentLabel = "warp", permission = "warp.command.create", description = "Create a warp")
    public void create(BukkitCommandExecutor executor, String name) throws ConditionFailedException {
        if (warpModule.find(name) != null) {
            throw new ConditionFailedException(WarpMessageConfiguration.WARP_ALREADY_EXISTS);
        }

        executor.sendMessage(WarpMessageConfiguration.WARP_CREATED);

        final Warp warp = new Warp(name, executor.getPlayer().getLocation());

        this.warpModule.registerWarp(warp);
        this.warpModule.saveWarps(warp);
    }

    @Subcommand(label = "delete", parentLabel = "warp", permission = "warp.command.delete", description = "Delete a warp", weight = 1)
    public void delete(BukkitCommandExecutor executor, Warp warp) {
        this.warpModule.deleteWarp(warp);

        executor.sendMessage(WarpMessageConfiguration.WARP_DELETED);
    }

    @Subcommand(label = "setlocation", parentLabel = "warp", permission = "warp.command.setlocation", description = "Set the location of a warp", weight = 2)
    public void setLocation(BukkitCommandExecutor executor, Warp warp) {
       warp.setSpawn(executor.getPlayer().getLocation());

       executor.sendMessage(WarpMessageConfiguration.WARP_LOCATION);
       this.warpModule.saveWarps(warp);
    }
}
