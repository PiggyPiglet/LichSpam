package me.piggypiglet.lichspam.utils.files.exceptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileCreationException extends RuntimeException {
    public FileCreationException(@NotNull final File file) {
        super("Something went wrong when trying to create file: " + file.getPath());
    }
}
