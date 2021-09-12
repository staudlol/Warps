package io.github.staudlol.warps.module;

import lombok.Getter;
import java.util.*;

public class ModuleHandler {

    @Getter
    private static ModuleHandler instance;
    private final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    /**
     * Constructor for creating a new ModuleHandler class
     *
     * @param modules the array of modules to automatically register
     */
    public ModuleHandler(Module... modules) {
        instance = this;
        Arrays.stream(modules).forEach(this::register);
    }

    /**
     * Register a {@link Module} to the list of modules
     *
     * @param module the module to register
     * @param <T>    the type of the module
     */
    public <T extends Module> void register(T module) {
        if(module == null) {
            throw new IllegalArgumentException("Provided module is null @ " + System.currentTimeMillis());
        }

        this.modules.put(module.getClass(), module);
    }

    /**
     * Find a {@link Module} by a {@link Class}
     *
     * @param clazz the class of the module
     * @param <T>   the type of the module
     * @return the module or null
     */
    public <T extends Module> T find(Class<? extends T> clazz) {
        return clazz.cast(this.modules.get(clazz));
    }

    /**
     * Load all registered modules
     *
     * @return whether this task was successful or not
     */
    public boolean loadAll() {
        try {
            this.modules.values().forEach(Module::load);
        } catch (Exception ignored) {
            this.unloadAll();

            return false;
        }

        return true;
    }

    /**
     * Unload all registered modules
     *
     * @return whether the task was successful or not
     */
    public boolean unloadAll() {
        try {
            this.modules.values().forEach(Module::unload);
        } catch (Exception ignored) {
            return false;
        }

        return true;
    }
}