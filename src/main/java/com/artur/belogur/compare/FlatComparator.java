package com.artur.belogur.compare;

import com.artur.belogur.Flat;
import com.artur.belogur.repository.FlatRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlatComparator {

    private final List<Flat> inputFlats;

    private final List<Flat> freeFlats;
    private final List<Flat> desiredFlats;

    public FlatComparator(FlatRepository flatRepository, List<Flat> inputFlats) {
        this.inputFlats = inputFlats;
        this.freeFlats = flatRepository.getFreeFlats();
        this.desiredFlats = flatRepository.getDesiredFlats();
    }

    public boolean inputFreeEqual() {
        return inputFlats.size() == freeFlats.size();
    }

    public boolean inputFreeMore() {
        return inputFlats.size() > freeFlats.size();
    }

    public List<Flat> getFreeDiff() {
        if (inputFreeEqual()) {
            return Collections.EMPTY_LIST;
        }
        if (inputFreeMore()) {
            return inputFlats.stream()
                    .filter(flat -> !freeFlats.contains(flat))
                    .collect(Collectors.toList());
        } else {
            return freeFlats.stream()
                    .filter(flat -> !inputFlats.contains(flat))
                    .collect(Collectors.toList());
        }
    }

    public boolean allDesiredIncluded() {
        return inputFlats.containsAll(desiredFlats);
    }

    public List<Flat> getNotIncludedDesired() {
        return desiredFlats.stream()
                .filter(flat -> !inputFlats.contains(flat))
                .collect(Collectors.toList());
    }
}
