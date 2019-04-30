package com.artur.belogur.notification;

import com.artur.belogur.compare.FlatPriceDiff;

import java.util.List;

public interface Notifiable {
    void sendFlatNumberNotChanged();
    void sendFreeFlatInfo(boolean more, List<Integer> diff);
    void sendDesiredFlatInfo(List<Integer> diff);
    void sendPriceInfo(List<FlatPriceDiff> priceDiffs);
}
