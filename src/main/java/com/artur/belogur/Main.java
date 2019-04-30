package com.artur.belogur;

import com.artur.belogur.flatclient.FlatClient;

public class Main {

    public static void main(String[] args) {
        String flats = new FlatClient().getFlats();
    }
}
