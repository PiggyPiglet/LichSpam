package me.piggypiglet.lichspam.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.lichspam.commands.framework.Command;
import me.piggypiglet.lichspam.files.FileManager;
import me.piggypiglet.lichspam.files.registerables.FilesRegisterable;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends Command {
    @Inject private FilesRegisterable filesRegisterable;

    public ReloadCommand() {
        super("reload");
        options
                .usage("")
                .description("Reload the plugin")
                .permissions("lichspam.admin", "lichspam.reload");
    }

    @Override
    public boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args) {
        //noinspection ConstantConditions
        filesRegisterable.run(null);
        sender.sendMessage("Successfully reloaded the config(s).");
        return true;
    }
}
