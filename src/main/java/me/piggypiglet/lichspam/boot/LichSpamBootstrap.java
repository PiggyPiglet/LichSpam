package me.piggypiglet.lichspam.boot;

import com.google.inject.Injector;
import me.piggypiglet.lichspam.boot.framework.Registerable;
import me.piggypiglet.lichspam.commands.registerables.CommandHandlerRegisterable;
import me.piggypiglet.lichspam.commands.registerables.CommandsRegisterable;
import me.piggypiglet.lichspam.events.registerables.EventsRegisterable;
import me.piggypiglet.lichspam.files.registerables.FileObjectTypesRegisterable;
import me.piggypiglet.lichspam.files.registerables.FilesRegisterable;
import me.piggypiglet.lichspam.guice.modules.DynamicModule;
import me.piggypiglet.lichspam.guice.modules.InitialModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LichSpamBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> REGISTERABLES = Arrays.asList(
            FileObjectTypesRegisterable.class, FilesRegisterable.class, CommandsRegisterable.class,
            CommandHandlerRegisterable.class, EventsRegisterable.class
    );

    @Override
    public void onEnable() {
        final AtomicReference<Injector> injectorReference = new AtomicReference<>(
                new InitialModule(this).createInjector()
        );

        for (final Class<? extends Registerable> registerableClass : REGISTERABLES) {
            final Injector injector = injectorReference.get();
            final Registerable registerable = injector.getInstance(registerableClass);
            registerable.run(injector);

            if (!registerable.getBindings().isEmpty() || registerable.getStaticInjections().length > 0) {
                injectorReference.set(injector.createChildInjector(new DynamicModule(
                        registerable.getBindings(), registerable.getStaticInjections()
                )));
            }
        }
    }
}
