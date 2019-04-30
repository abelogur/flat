package com.artur.belogur.notification;

import com.artur.belogur.compare.FlatPriceDiff;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LogSender implements Notifiable {


    @Override
    public void sendFlatNumberNotChanged() {
        log.info("Number of flats haven't changed");
    }

    @Override
    public void sendFreeFlatInfo(boolean more, List<Integer> diff) {
        if (diff.size() == 0) {
            log.info("Number of flats haven't changed");
        }
        if (more) {
            log.info("Plus {}", diff);
        } else {
            log.info("Minus {}", diff);
        }
    }

    @Override
    public void sendDesiredFlatInfo(List<Integer> diff) {
        log.info("Desired flats have left {}", diff);
    }

    @Override
    public void sendPriceInfo(List<FlatPriceDiff> priceDiffs) {
        if (priceDiffs.size() == 0) return;
        priceDiffs.forEach(diff ->
                log.info("Flat number: {}. {} -> {}", diff.getNumber(), diff.getOldPrice(), diff.getNewPrice()));
    }
}
