package com.artur.belogur.notification;

import com.artur.belogur.compare.FlatPriceDiff;
import lombok.SneakyThrows;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.util.List;

public class TelegramSender implements Notifiable {

    private final FlatBot flatBot;

    @SneakyThrows
    public TelegramSender() {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        this.flatBot = new FlatBot();
        botsApi.registerBot(flatBot);
    }

    @Override
    public void sendFlatNumberNotChanged() {
        flatBot.sendMessage("Количество квартир не изменилось");
    }

    @Override
    public void sendFreeFlatInfo(boolean more, List<Integer> diff) {
        if (diff.size() == 0) {
            flatBot.sendMessage("Количество квартир не изменилось");
        }
        if (more) {
            flatBot.sendMessage("Добавились квартиры " + diff.toString());
        } else {
            flatBot.sendMessage("Пропали квартиры " + diff.toString());
        }
    }

    @Override
    public void sendDesiredFlatInfo(List<Integer> diff) {
        flatBot.sendMessage("Пропали желанные квартиры " + diff.toString());
    }

    @Override
    public void sendPriceInfo(List<FlatPriceDiff> priceDiffs) {
        if (priceDiffs.size() == 0) return;
        priceDiffs.forEach(diff -> flatBot.sendMessage(String.format("Цена изменилась: %s. %s -> %s",
                diff.getNumber(), diff.getOldPrice(), diff.getNewPrice())));
    }
}
