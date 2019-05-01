package com.artur.belogur.notification;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FlatBot extends TelegramLongPollingBot {

    private final String CHAT_ID = "-1001232399209";

    @Override
    public String getBotUsername() {
        return "ArturBelogurBot";
    }

    @Override
    public String getBotToken() {
        return "422235621:AAFuw93PTmN_MvYWM7bbuGWdIa4058qaNvA";
    }

    @SneakyThrows
    public void sendMessage(String message) {
        execute(new SendMessage(CHAT_ID, message));
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
