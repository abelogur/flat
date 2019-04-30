package com.artur.belogur;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Flat {
    private int number;
    private int price;

    @Override
    public boolean equals(Object o) {
        return this.number == ((Flat) o).getNumber();
    }
}
