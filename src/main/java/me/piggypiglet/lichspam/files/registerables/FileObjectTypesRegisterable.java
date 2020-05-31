package me.piggypiglet.lichspam.files.registerables;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import me.piggypiglet.lichspam.boot.framework.Registerable;
import me.piggypiglet.lichspam.scanning.framework.Scanner;
import me.piggypiglet.lichspam.utils.annotation.wrapper.AnnotationRules;
import me.piggypiglet.lichspam.utils.files.annotations.File;
import me.piggypiglet.lichspam.utils.files.annotations.binding.FileObjectTypes;
import me.piggypiglet.lichspam.utils.files.annotations.binding.Files;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class FileObjectTypesRegisterable extends Registerable {
    @Inject private Scanner scanner;

    @Override
    protected void execute() {
        final Set<Class<?>> fileClasses = scanner.getClassesAnnotatedWith(AnnotationRules.hasAnnotation(File.class));

        addBinding(new TypeLiteral<Set<Class<?>>>() {}, Files.class, fileClasses);
        addBinding(new TypeLiteral<Map<Class<?>, Object>>() {}, FileObjectTypes.class,
                fileClasses.stream()
                        .collect(Collectors.toMap(clazz -> clazz, injector::getInstance)));
    }
}
