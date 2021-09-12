package io.github.staudlol.warps.module;


public interface Module {

    default void load() {}

    default void unload() {}
}