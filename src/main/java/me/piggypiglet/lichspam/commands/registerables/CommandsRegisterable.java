package me.piggypiglet.lichspam.commands.registerables;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import me.piggypiglet.lichspam.boot.framework.Registerable;
import me.piggypiglet.lichspam.commands.CommandHandler;
import me.piggypiglet.lichspam.commands.framework.Command;
import me.piggypiglet.lichspam.scanning.framework.Scanner;

import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class CommandsRegisterable extends Registerable {
    @Inject private Scanner scanner;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        commandHandler.populate(scanner.getSubTypesOf(Command.class).stream()
                .map(injector::getInstance)
                .collect(Collectors.toSet()));
    }
}
