package de.lall.kata.pricing.pricingrules;

import de.lall.kata.pricing.Item;
import de.lall.kata.pricing.PricePartition;

import java.util.List;
import java.util.stream.Collectors;

public class MultipleItemRule implements PricingRule {
    private final Item targetItem;
    private final int itemCount;
    private final int setValue;

    public MultipleItemRule(Item targetItem, int itemCount, int setValue) {
        this.targetItem = targetItem;
        this.itemCount = itemCount;
        this.setValue = setValue;
    }

    @Override
    public PricePartition apply(List<Item> items) {
        List<Item> matchedItems = match(items);
        int totalValue = matchedItems.size() / itemCount * setValue;
        return new PricePartition(totalValue, matchedItems);
    }

    private List<Item> match(List<Item> items) {
        List<Item> candidates = findCandidates(items);
        return removeExcessiveMatches(candidates);
    }

    private List<Item> findCandidates(List<Item> items) {
        return items.stream()
                .filter(item -> item.equals(targetItem))
                .collect(Collectors.toList());
    }

    private List<Item> removeExcessiveMatches(List<Item> candidates) {
        return candidates.subList(0, candidates.size() / itemCount * itemCount);
    }
}
