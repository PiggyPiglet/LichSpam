package me.piggypiglet.lichspam.utils.files;

import com.google.common.io.Resources;
import me.piggypiglet.lichspam.boot.LichSpamBootstrap;
import me.piggypiglet.lichspam.utils.files.exceptions.DirectoryCreationException;
import me.piggypiglet.lichspam.utils.files.exceptions.FileCreationException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileUtils {
    private static final Class<LichSpamBootstrap> MAIN = LichSpamBootstrap.class;

    private FileUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    public static File createFile(@NotNull final String internalPath, @NotNull final String externalPath) throws Exception {
        final File file = new File(externalPath);

        if (file.exists()) return file;
        if (!file.getParentFile().mkdirs()) throw new DirectoryCreationException(file.getParentFile());
        if (!file.createNewFile()) throw new FileCreationException(file);

        exportResource(internalPath, externalPath);
        return file;
    }

    public static void exportResource(@NotNull final String internalPath, @NotNull final String externalPath) throws Exception {
        Files.copy(MAIN.getResourceAsStream(internalPath), Paths.get(externalPath),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @SuppressWarnings("UnstableApiUsage")
    @NotNull
    public static String readFile(@NotNull final File file) throws Exception {
        return String.join("\n", com.google.common.io.Files.readLines(file, StandardCharsets.UTF_8));
    }
}
