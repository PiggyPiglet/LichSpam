package me.piggypiglet.lichspam.config;

import com.google.gson.annotations.JsonAdapter;
import com.google.inject.Singleton;
import me.piggypiglet.lichspam.config.serializer.TimeAdapter;
import me.piggypiglet.lichspam.utils.files.annotations.File;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@File(internalPath = "/config.yml", externalPath = "config.yml")
@Singleton
public final class Config {
    private int configVersion;
    @JsonAdapter(TimeAdapter.class) private long timeLimit;

    public int getVersion() {
        return configVersion;
    }

    public long getTimeLimitSeconds() {
        return timeLimit;
    }

    @Override
    public String toString() {
        return "Config{" +
                "configVersion=" + configVersion +
                ", timeLimit='" + timeLimit + '\'' +
                '}';
    }
}
