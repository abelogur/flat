package com.artur.belogur;

import com.artur.belogur.compare.FlatComparator;
import com.artur.belogur.flatclient.FlatClient;
import com.artur.belogur.notification.LogSender;
import com.artur.belogur.notification.Notifiable;
import com.artur.belogur.repository.FlatRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        FlatClient client = new FlatClient();
        List<Flat> inputFlats = client.getFlats();

        FlatRepository flatRepository = new FlatRepository();
        FlatComparator comparator = new FlatComparator(flatRepository, inputFlats);

        Notifiable sender = new LogSender();

        if (comparator.inputFreeEqual()) {
            sender.sendFlatNumberNotChanged();
        }
        else {
            sender.sendFreeFlatInfo(comparator.inputFreeMore(), getFlatsPrice(comparator.getFreeDiff()));
        }

        if (!comparator.allDesiredIncluded()) {
            sender.sendDesiredFlatInfo(getFlatsPrice(comparator.getNotIncludedDesired()));
        }
    }

    private static List<Integer> getFlatsPrice(List<Flat> flats) {
        return flats.stream().map(Flat::getNumber).collect(Collectors.toList());
    }
}
