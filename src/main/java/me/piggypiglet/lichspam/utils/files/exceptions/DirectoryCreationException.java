package me.piggypiglet.lichspam.utils.files.exceptions;

import org.jetbrains.annotations.NotNull;

import java.io.File;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DirectoryCreationException extends RuntimeException {
    public DirectoryCreationException(@NotNull final File file) {
        super("Something went wrong when trying to create directory: " + file.getPath());
    }
}
