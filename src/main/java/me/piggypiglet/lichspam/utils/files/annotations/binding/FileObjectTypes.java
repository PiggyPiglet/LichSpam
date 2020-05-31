package me.piggypiglet.lichspam.utils.files.annotations.binding;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@BindingAnnotation
@Target({ElementType.PARAMETER, ElementType.FIELD}) @Retention(RetentionPolicy.RUNTIME)
public @interface FileObjectTypes {
}
