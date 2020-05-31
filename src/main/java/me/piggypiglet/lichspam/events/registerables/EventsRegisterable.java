package me.piggypiglet.lichspam.events.registerables;

import com.google.inject.Inject;
import me.piggypiglet.lichspam.boot.framework.Registerable;
import me.piggypiglet.lichspam.scanning.framework.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class EventsRegisterable extends Registerable {
    @Inject private Scanner scanner;
    @Inject private JavaPlugin main;

    @Override
    protected void execute() {
        scanner.getSubTypesOf(Listener.class).stream()
                .map(injector::getInstance)
                .forEach(listener -> Bukkit.getServer().getPluginManager().registerEvents(listener, main));
    }
}
