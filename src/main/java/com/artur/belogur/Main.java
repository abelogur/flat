package com.artur.belogur;

import com.artur.belogur.repository.FlatRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FlatRepository flatRepository = new FlatRepository();
        List<Flat> freeFlat = flatRepository.getFreeFlats();
        List<Flat> desiredFlat = flatRepository.getDesiredFlats();
    }
}
