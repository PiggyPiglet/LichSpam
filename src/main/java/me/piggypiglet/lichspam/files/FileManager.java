package me.piggypiglet.lichspam.files;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.lichspam.utils.files.FileUtils;
import me.piggypiglet.lichspam.utils.files.annotations.binding.FileObjectTypes;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class FileManager {
    private static final Yaml YAML = new Yaml();

    private final String dataFolder;
    private final Gson gson;

    @Inject
    public FileManager(@NotNull final JavaPlugin main, @NotNull @FileObjectTypes final Map<Class<?>, Object> types) {
        this.dataFolder = main.getDataFolder().getPath();

        final AtomicReference<GsonBuilder> builder = new AtomicReference<>(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES));

        types.forEach((clazz, instance) -> builder.set(builder.get()
                .registerTypeAdapter(clazz, instanceCreator(instance))));

        gson = builder.get().create();
    }

    @NotNull
    private <T> InstanceCreator<T> instanceCreator(@NotNull final T instance) {
        return type -> instance;
    }

    public void loadConfig(@NotNull final Class<?> type, @NotNull final String internalPath,
                           @NotNull final String externalPath) throws Exception {
        final Map<String, Object> config = YAML.load(FileUtils.readFile(createFile(internalPath, externalPath)));
        gson.fromJson(gson.toJsonTree(config), type);
    }

    @NotNull
    private File createFile(@NotNull final String internalPath, @NotNull final String externalPath) throws Exception {
        return FileUtils.createFile(internalPath, dataFolder + '/' + externalPath);
    }
}
