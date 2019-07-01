package de.lall.kata.pricing.pricingrules;

import de.lall.kata.pricing.Item;
import de.lall.kata.pricing.PricePartition;

import java.util.List;
import java.util.stream.Collectors;

public class FixedPriceRule implements PricingRule {
    private final Item targetItem;
    private final int itemValue;

    public FixedPriceRule(Item targetItem, int itemValue) {
        this.targetItem = targetItem;
        this.itemValue = itemValue;
    }

    @Override
    public PricePartition apply(List<Item> items) {
        List<Item> matchedItems = match(items);
        return new PricePartition(matchedItems.size() * itemValue, matchedItems);
    }

    private List<Item> match(List<Item> items) {
        return items.stream()
                .filter(item -> item.equals(targetItem))
                .collect(Collectors.toList());
    }
}
