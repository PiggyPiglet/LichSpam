package me.piggypiglet.lichspam.config.serializer;

import com.google.gson.*;
import sh.okx.timeapi.TimeAPI;

import java.lang.reflect.Type;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class TimeAdapter implements JsonDeserializer<Long> {
    @Override
    public Long deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        return new TimeAPI(json.getAsString()).getSeconds();
    }
}
