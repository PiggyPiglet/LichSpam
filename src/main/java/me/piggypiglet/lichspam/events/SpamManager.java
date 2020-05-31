package me.piggypiglet.lichspam.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.lichspam.config.Config;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class SpamManager implements Listener {
    @Inject private Config config;

    // multimap > this, but expiring list implementations are scarce, let alone one incorporated into a multimap.
    private final Map<UUID, Map<String, Integer>> watching = ExpiringMap.builder()
            .expiration(10, TimeUnit.SECONDS)
            .expirationPolicy(ExpirationPolicy.ACCESSED)
            .build();

    @EventHandler
    public void onChat(@NotNull final AsyncPlayerChatEvent event) {
        final UUID sender = event.getPlayer().getUniqueId();
        final String message = event.getMessage();

        if (!watching.containsKey(sender)) {
            final Map<String, Integer> map = ExpiringMap.builder()
                    .expiration(config.getTimeLimitSeconds(), TimeUnit.SECONDS)
                    .expirationPolicy(ExpirationPolicy.ACCESSED)
                    .build();
            map.put(message, 0);
            watching.put(sender, map);

            return;
        }

        final Map<String, Integer> map = watching.get(sender);

        // use get instead of containskey so the expiration policy is honoured
        if (map.get(message) == null) {
            map.put(message, 0);
            return;
        }

        event.setCancelled(true);
    }
}
