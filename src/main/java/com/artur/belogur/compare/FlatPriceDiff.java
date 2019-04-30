package com.artur.belogur.compare;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FlatPriceDiff {
    private int number;
    private int oldPrice;
    private int newPrice;
}
