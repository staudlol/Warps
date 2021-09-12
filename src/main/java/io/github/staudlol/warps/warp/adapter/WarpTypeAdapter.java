package io.github.staudlol.warps.warp.adapter;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.staudlol.warps.warp.Warp;
import io.github.staudlol.warps.warp.WarpModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarpTypeAdapter implements TypeAdapter<Warp> {

    private final WarpModule warpModule;

    /**
     * Convert a {@link String} to the {@link Warp} object type
     *
     * @param executor the executor of the command
     * @param source   the source to convert
     * @return the converted object
     */
    @Override
    public Warp convert(CommandExecutor executor, String source) throws Exception {
        return this.warpModule.find(source);
    }
}
