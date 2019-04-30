package com.artur.belogur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Flat {
    private int number;
    private int price;
}
