package com.artur.belogur.repository;

import com.artur.belogur.Flat;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlatRepository {

    private static final String FREE_FLATS_FILE = "free-flats.txt";
    private static final String DESIRED_FLATS_FILE = "desired-flats.txt";

    public List<Flat> getFreeFlats() {
        return getFlatsAsLine(FREE_FLATS_FILE);
    }

    public List<Flat> getDesiredFlats() {
        return getFlatsAsLine(DESIRED_FLATS_FILE);
    }

    public void saveFreeFlats(List<Flat> flats) {
        writeToFile(flats, FREE_FLATS_FILE);
    }

    public void saveDesiredFlats(List<Flat> flats) {
        writeToFile(flats, DESIRED_FLATS_FILE);
    }

    @SneakyThrows
    private List<Flat> getFlatsAsLine(String fileName) {
        File file = getFileFromResources(fileName);
        @Cleanup BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String data = bufferedReader.readLine();
        return data == null ?
                Collections.EMPTY_LIST :
                Arrays.stream(data.split("; "))
                        .map(this::mapFlat)
                        .collect(Collectors.toList());
    }

    private Flat mapFlat(String flatAsString) {
        String[] flatAsArray = flatAsString.split(" ");
        int number = Integer.valueOf(flatAsArray[0]);
        int price = Integer.valueOf(flatAsArray[1]);
        return new Flat(number, price);
    }

    @SneakyThrows
    private void writeToFile(List<Flat> flats, String fileName) {
        String flatsAsString = flats.stream()
                .map(Flat::toString)
                .collect(Collectors.joining("; "));
        File file = getFileFromResources(fileName);
        @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(flatsAsString);
    }

    @SneakyThrows
    private File getFileFromResources(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
