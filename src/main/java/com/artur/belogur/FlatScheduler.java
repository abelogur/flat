package com.artur.belogur;

import com.artur.belogur.compare.FlatComparator;
import com.artur.belogur.compare.FlatPriceDiff;
import com.artur.belogur.flatclient.FlatClient;
import com.artur.belogur.notification.LogSender;
import com.artur.belogur.notification.Notifiable;
import com.artur.belogur.repository.FlatRepository;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FlatScheduler {

    public void run() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(task(), 0, 5, TimeUnit.SECONDS);
    }

    private Runnable task() {
        return () -> {
            FlatClient client = new FlatClient();
            List<Flat> inputFlats = client.getFlats();

            FlatRepository flatRepository = new FlatRepository();
            FlatComparator comparator = new FlatComparator(flatRepository, inputFlats);

            Notifiable sender = new LogSender();

            if (comparator.inputFreeEqual()) {
                sender.sendFlatNumberNotChanged();
            } else {
                sender.sendFreeFlatInfo(comparator.inputFreeMore(), getFlatsPrice(comparator.getFreeDiff()));
            }

            if (!comparator.allDesiredIncluded()) {
                sender.sendDesiredFlatInfo(getFlatsPrice(comparator.getNotIncludedDesired()));
            }

            List<FlatPriceDiff> flatPriceDiffs = comparator.getDiffPrices();
            sender.sendPriceInfo(flatPriceDiffs.stream()
                    .filter(flatPriceDiff -> flatPriceDiff.getOldPrice() != flatPriceDiff.getNewPrice())
                    .collect(Collectors.toList()));

            flatRepository.saveFreeFlats(inputFlats);
            flatRepository.saveDesiredFlats(flatPriceDiffs.stream()
                    .map(flatPriceDiff -> new Flat(flatPriceDiff.getNumber(), flatPriceDiff.getNewPrice()))
                    .collect(Collectors.toList()));
        };
    }

    private List<Integer> getFlatsPrice(List<Flat> flats) {
        return flats.stream().map(Flat::getNumber).collect(Collectors.toList());
    }
}
