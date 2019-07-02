package de.lall.kata.pricing.pricingrules;

import de.lall.kata.pricing.Item;
import de.lall.kata.pricing.PricePartition;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public class EnforceRuleSetCompletenessRule implements PricingRule {
    @Override
    public PricePartition apply(List<Item> items) {
        if (!items.isEmpty()) {
            throw new IllegalStateException(format("Found %d unhandled items", items.size()));
        }

        return new PricePartition(0, Collections.<Item>emptyList());
    }
}
