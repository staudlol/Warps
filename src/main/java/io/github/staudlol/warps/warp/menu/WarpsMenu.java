package io.github.staudlol.warps.warp.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.function.Consumer;

@Getter @Setter
public class WarpsMenu extends PaginatedMenu {

    private final WarpModule warpModule;

    public WarpsMenu(Player player, WarpModule warpModule) {
        super(player, "Warps", 36);

        this.warpModule = warpModule;

        this.setPreviousPageButton(new Button(Material.CARPET)
                .setDisplayName(ChatColor.RED + "Previous Page")
                .setData(DyeColor.RED.getWoolData()));
        this.setNextPageButton(new Button(Material.CARPET)
                .setDisplayName(ChatColor.GREEN + "Next Page")
                .setData(DyeColor.GREEN.getWoolData()));
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

            final String[] lore = new String[] {
                    ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 52),
                    ChatColor.GRAY + "",
                    ChatColor.GREEN + "Click to teleport to " + warp.getName(),
                    ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 52),
            };

            final Consumer<InventoryClickEvent> action = type -> {
                this.getPlayer().teleport(location);
                this.getPlayer().sendMessage(WarpMessageConfiguration.WARP_TELEPORT
                        .replace("warp%", warp.getName()));
            };

            this.buttons[i] = new Button(Material.EMERALD)
                    .setDisplayName(ChatColor.YELLOW + warp.getName())
                    .setLore(lore)
                    .setClickAction(action);
        }
    }
}
