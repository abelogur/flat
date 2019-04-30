package com.artur.belogur.repository;

import com.artur.belogur.Flat;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlatRepository {

    private static final String FREE_FLATS_FILE = "free-flats.txt";
    private static final String DESIRED_FLATS_FILE = "desired-flats.txt";

    @SneakyThrows
    public List<Flat> getFreeFlats() {
        return getFlatsAsLine(FREE_FLATS_FILE);
    }

    @SneakyThrows
    public List<Flat> getDesiredFlats() {
        return getFlatsAsLine(DESIRED_FLATS_FILE);
    }

    @SneakyThrows
    private List<Flat> getFlatsAsLine(String fileName) {
        File file = Optional.ofNullable(getClass().getClassLoader().getResource(fileName))
                .map(url -> new File(url.getFile()))
                .orElseThrow(() -> new IllegalArgumentException("File " + fileName + " doesn't exist"));

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return Arrays.stream(bufferedReader.readLine().split("; "))
                .map(this::mapFlat)
                .collect(Collectors.toList());
    }

    private Flat mapFlat(String flatAsString) {
        String[] flatAsArray = flatAsString.split(" ");
        int number = Integer.valueOf(flatAsArray[0]);
        int price = Integer.valueOf(flatAsArray[1]);
        return new Flat(number, price);
    }
}
