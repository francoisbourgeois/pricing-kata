package de.lall.kata.pricing;

import java.util.Collections;
import java.util.List;

public class PricePartition {
    private final int value;
    private final List<Item> items;

    public PricePartition(int value, List<Item> items) {
        this.value = value;
        this.items = Collections.unmodifiableList(items);
    }

    public int getValue() {
        return value;
    }

    public List<Item> getItems() {
        return items;
    }
}
