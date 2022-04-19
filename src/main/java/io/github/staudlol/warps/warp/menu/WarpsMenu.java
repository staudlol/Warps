package io.github.staudlol.warps.warp.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.util.CC;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;


public class WarpsMenu extends PaginatedMenu {

    private final WarpModule warpModule;

    public WarpsMenu(Player player, WarpModule warpModule) {
        super(player, "Warps", 36);

        this.warpModule = warpModule;
    }

    /**
     * The method to get the buttons for the current inventory tick
     * <p>
     * Use {@code this.buttons[index] = Button} to assign
     * a button to a slot.
     */

    @Override
    public void tick() {
        for (int i = 0; i < this.warpModule.getWarps().size(); i++) {

            final Warp warp = this.warpModule.getWarps().get(i);
            final Location location = warp.getSpawn();

            final boolean hasPermission = this.getPlayer().hasPermission(warp.getPermission());
            final String permission = warp.getPermission();

            this.buttons[i] = new Button(!hasPermission ? Material.BARRIER : Material.EMERALD)
                    .setDisplayName(CC.translate("&e" + warp.getName()))
                    .setLore(this.getWarpLore(warp))
                    .setClickAction(event -> {
                        event.setCancelled(true);
                        if (hasPermission) {
                            if (permission == null) {
                            } else {
                                this.getPlayer().teleport(location);
                                this.getPlayer().sendMessage(WarpMessageConfiguration.WARP_TELEPORT
                                        .replace("%warp%", warp.getName()));
                            }
                        }
                    });
        }
    }

    private String[] getWarpLore(Warp warp) {
        return new String[] {
                CC.translate("&7&m---------------------------"),
                CC.translate("&aClick to teleport to &3" + warp.getName()),
                CC.translate("&7&m---------------------------"),
        };
    }
}
