package com.rm.retty.api.controller;

import com.google.gson.Gson;

public class MessageConverter<T> {
    public T fromJson(String body, Class<T> clazz) {
        return new Gson().fromJson(body, clazz);
    }
}