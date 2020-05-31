package me.piggypiglet.lichspam.scanning.framework;

import me.piggypiglet.lichspam.utils.annotation.wrapper.AnnotationRules;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface Scanner {
    <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type);

    Set<Class<?>> getClassesAnnotatedWith(@NotNull final AnnotationRules rules);
}
