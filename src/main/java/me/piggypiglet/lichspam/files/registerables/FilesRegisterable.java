package me.piggypiglet.lichspam.files.registerables;

import com.google.inject.Inject;
import me.piggypiglet.lichspam.boot.framework.Registerable;
import me.piggypiglet.lichspam.files.FileManager;
import me.piggypiglet.lichspam.files.exceptions.ConfigLoadException;
import me.piggypiglet.lichspam.utils.files.annotations.File;
import me.piggypiglet.lichspam.utils.files.annotations.binding.Files;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FilesRegisterable extends Registerable {
    @Inject @Files private Set<Class<?>> fileClasses;
    @Inject private FileManager fileManager;

    @Override
    protected void execute() {
        fileClasses.forEach(clazz -> {
            final File info = clazz.getAnnotation(File.class);

            try {
                fileManager.loadConfig(clazz, info.internalPath(), info.externalPath());
            } catch (final Exception exception) {
                throw new ConfigLoadException("Something went wrong when loading: " + clazz);
            }
        });
    }
}
