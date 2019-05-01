package com.artur.belogur.notification;

import com.artur.belogur.compare.FlatPriceDiff;

import java.util.Arrays;
import java.util.List;

public class SendersHolder implements Notifiable {

    private final List<Notifiable> senders;

    public SendersHolder(Notifiable ...notifiables) {
        senders = Arrays.asList(notifiables);
    }

    @Override
    public void sendFlatNumberNotChanged() {
        senders.forEach(Notifiable::sendFlatNumberNotChanged);
    }

    @Override
    public void sendFreeFlatInfo(boolean more, List<Integer> diff) {
        senders.forEach(notifiable -> notifiable.sendFreeFlatInfo(more, diff));
    }

    @Override
    public void sendDesiredFlatInfo(List<Integer> diff) {
        senders.forEach(notifiable -> notifiable.sendDesiredFlatInfo(diff));
    }

    @Override
    public void sendPriceInfo(List<FlatPriceDiff> priceDiffs) {
        senders.forEach(notifiable -> notifiable.sendPriceInfo(priceDiffs));
    }
}
