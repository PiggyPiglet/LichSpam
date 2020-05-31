package me.piggypiglet.lichspam.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lichspam.commands.CommandHandler;
import me.piggypiglet.lichspam.commands.framework.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class HelpCommand extends Command {
    @Inject private CommandHandler commandHandler;

    public HelpCommand() {
        super("help");
        options.def(true);
    }

    @Override
    public boolean execute(final @NotNull CommandSender sender, @NotNull final String[] args) {
        return true;
    }
}
