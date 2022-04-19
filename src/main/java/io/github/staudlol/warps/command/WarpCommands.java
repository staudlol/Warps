package io.github.staudlol.warps.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Help;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.command.exception.ConditionFailedException;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.util.CC;
import io.github.staudlol.warps.util.CommandExecutorUtil;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import io.github.staudlol.warps.warp.menu.WarpsMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarpCommands {

    private final WarpModule warpModule;

    @Command(label = "warps", userOnly = true)
    public void warps(BukkitCommandExecutor executor) {
        new WarpsMenu(executor.getPlayer(), this.warpModule).updateMenu();
    }

    @Help
    @Command(label = "warp", permission = "warp.command.help", description = "Displays the help message")
    public void help(BukkitCommandExecutor executor) {}

    @Subcommand(label = "create", parentLabel = "warp", permission = "warp.command.create", description = "Create a warp")
    public void create(BukkitCommandExecutor executor, String name) throws ConditionFailedException {
        if (this.warpModule.findWarpByName(name) != null) {
            throw new ConditionFailedException(WarpMessageConfiguration.WARP_ALREADY_EXISTS);
        }

        executor.sendMessage(WarpMessageConfiguration.WARP_CREATED
                .replace("%warp%", name));

        final Warp warp = new Warp(name, executor.getPlayer().getLocation());

        this.warpModule.registerWarp(warp);
        this.warpModule.saveWarps(warp);
    }

    @Subcommand(label = "delete", parentLabel = "warp", permission = "warp.command.delete", description = "Delete a warp", weight = 1)
    public void delete(BukkitCommandExecutor executor, Warp warp) {
        this.warpModule.deleteWarp(warp);

        executor.sendMessage(WarpMessageConfiguration.WARP_DELETED
                .replace("%warp%", warp.getName()));
    }

    @Subcommand(label = "setlocation", parentLabel = "warp", permission = "warp.command.setlocation", description = "Set the location of a warp", weight = 2)
    public void setLocation(BukkitCommandExecutor executor, Warp warp) {
       warp.setSpawn(executor.getPlayer().getLocation());

       executor.sendMessage(WarpMessageConfiguration.WARP_LOCATION
               .replace("%warp%", warp.getName()));

       this.warpModule.saveWarps(warp);
    }

    @Subcommand(label = "setpermission", parentLabel = "warp", permission = "warp.command.setpermission", description = "Set the permission of a warp", weight = 3)
    public void setPermission(BukkitCommandExecutor executor, Warp warp, String permission) {
        warp.setPermission(permission);

        executor.sendMessage(WarpMessageConfiguration.WARP_PERMISSION
                .replace("%warp%", warp.getName()));

        this.warpModule.saveWarps(warp);
    }

    @Subcommand(label = "info", parentLabel = "warp", permission = "warp.command.info", description = "Display the information of a warp", weight = 4)
    public void information(BukkitCommandExecutor executor, Warp warp) {
        CommandExecutorUtil.sendMessage(executor, new String[] {
                CC.translate("&7&m----------------------------------------------------"),
                CC.translate("&eDisplaying information of &a" + warp.getName()),
                CC.translate(""),
                CC.translate("&ePermission: &a" + warp.getPermission()),
                CC.translate(""),
                CC.translate("&aX: &b" + warp.getX()),
                CC.translate("&aY: &b" + warp.getY()),
                CC.translate("&aZ: &b" + warp.getZ()),
                CC.translate("&aPitch: &b" + warp.getPitch()),
                CC.translate("&aYaw: &b" + warp.getYaw()),
                CC.translate("&7&m----------------------------------------------------"),
        });
    }
}
