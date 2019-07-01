package de.lall.kata.pricing.pricingrules;

import de.lall.kata.pricing.Item;
import de.lall.kata.pricing.PricePartition;

import java.util.List;

public interface PricingRule {
    PricePartition apply(List<Item> items);
}
