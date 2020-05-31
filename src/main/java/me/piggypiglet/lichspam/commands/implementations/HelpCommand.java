package me.piggypiglet.lichspam.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lichspam.commands.CommandHandler;
import me.piggypiglet.lichspam.commands.framework.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class HelpCommand extends Command {
    @Inject private CommandHandler commandHandler;

    public HelpCommand() {
        super("help");
        options
                .usage("")
                .def(true)
                .permissions("lichspam.admin")
                .description("Help command.");
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        sender.sendMessage(commandHandler.getCommands().stream()
                .map(command -> "/ls " + command.getPrefix() + " " + command.getUsage() + " - " + command.getDescription())
                .collect(Collectors.joining("\n")));
        return true;
    }
}
