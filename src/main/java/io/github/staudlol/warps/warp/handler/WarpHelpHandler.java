package io.github.staudlol.warps.warp.handler;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.help.DefaultBukkitHelpHandler;
import io.github.nosequel.command.data.CommandData;
import io.github.nosequel.command.data.impl.BaseCommandData;
import io.github.nosequel.command.data.impl.SubcommandData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarpHelpHandler extends DefaultBukkitHelpHandler {

    /**
     * Get the help message to send to a player for help message
     *
     * @param commandData the command data to get the help data from
     * @return the help message to send
     */
    @Override
    public String getHelpMessage(BaseCommandData commandData) {
        final String label = commandData.getCommand().label();

        final List<String> strings = new ArrayList<>(Collections.singletonList(
                "&a=== &eShowing help for &f/" + label + " &a=== &7(" + (commandData.getSubcommandData().size() + 1) + " results)"
        ));

        for (SubcommandData subcommandDatum : commandData.getSubcommandData()) {
            strings.add(this.getUsage(subcommandDatum) + " &7- " + subcommandDatum.getCommand().description());
        }

        return String.join("\n", strings);
    }

    /**
     * Get the command usage of the specific command data
     *
     * @param commandData the command data to get the usage from
     * @return the displayed usage
     */
    private String getUsage(CommandData<?> commandData) {
        final StringBuilder builder = new StringBuilder("&a/");

        if (commandData instanceof BaseCommandData) {
            final BaseCommandData baseCommandData = (BaseCommandData) commandData;
            final Command command = baseCommandData.getCommand();

            builder.append(command.label());
        } else if (commandData instanceof SubcommandData) {
            final SubcommandData subcommandData = (SubcommandData) commandData;
            final Subcommand command = subcommandData.getCommand();

            builder
                    .append(command.parentLabel())
                    .append(" ")
                    .append(command.label());
        }

        if (!commandData.getArgumentUsage().equals("")) {
            builder
                    .append("&e ")
                    .append(commandData.getArgumentUsage());

        }
        return builder.toString();
    }
}