package io.github.staudlol.warps;

import io.github.nosequel.storage.mongo.MongoStorageHandler;
import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import io.github.nosequel.storage.mongo.settings.impl.NoAuthMongoSettings;
import io.github.staudlol.warps.config.impl.WarpMessageConfiguration;
import io.github.staudlol.warps.warp.Warp;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WarpsConstants {

    private static final MongoStorageHandler STORAGE_HANDLER = new MongoStorageHandler(new NoAuthMongoSettings(WarpMessageConfiguration.MONGO_HOSTNAME, WarpMessageConfiguration.MONGO_PORT, "warps"));
    public static final MongoStorageProvider<Warp> WARP_STORAGE = createStorageProvider("warps", Warp.class);

    /**
     * Create a new {@link MongoStorageProvider} object from the provided
     * arguments, and using the fields within the {@link WarpsConstants} class.
     *
     * @param collectionName the name of the collection to write to/read from
     * @param clazz          the class of type to store within the database
     * @param <T>            the type itself
     * @return the newly created mongo storage provider
     */
    private static <T> MongoStorageProvider<T> createStorageProvider(String collectionName, Class<T> clazz) {
        return new MongoStorageProvider<>(
                collectionName,
                STORAGE_HANDLER,
                clazz
        );
    }
}
