package com.artur.belogur.compare;

import com.artur.belogur.Flat;
import com.artur.belogur.repository.FlatRepository;

import java.util.List;

public class FlatComparator {

    private final List<Flat> inputFlats;
    private final List<Flat> freeFlats;
    private final List<Flat> desiredFlats;

    public FlatComparator(FlatRepository flatRepository, List<Flat> inputFlats) {
        this.inputFlats = inputFlats;
        this.freeFlats = flatRepository.getFreeFlats();
        this.desiredFlats = flatRepository.getDesiredFlats();
    }
}
