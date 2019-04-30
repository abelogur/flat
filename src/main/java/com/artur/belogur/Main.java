package com.artur.belogur;

import com.artur.belogur.flatclient.FlatClient;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flat> flats = new FlatClient().getFlats();
    }
}
