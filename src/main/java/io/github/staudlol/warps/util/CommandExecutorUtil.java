package io.github.staudlol.warps.util;

import io.github.nosequel.command.executor.CommandExecutor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandExecutorUtil {

    public void sendMessage(CommandExecutor executor, String[] messages) {
        for (String message : messages) {
            executor.sendMessage(message);
        }
    }
}