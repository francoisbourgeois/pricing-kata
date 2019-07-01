package de.lall.kata.pricing;

import de.lall.kata.pricing.pricingrules.PricingRule;

import java.util.ArrayList;
import java.util.List;

public class CheckOut {
    private List<PricingRule> rules;
    private List<Item> items;

    public CheckOut(List<PricingRule> rules) {
        this.rules = rules;
        items = new ArrayList<>();
    }

    @Deprecated
    public void scan(String itemId) {
        scan(new Item(itemId));
    }

    public void scan(Item item) {
        items.add(item);
    }

    public int total() {
        List<Item> workingItems = new ArrayList<>(items);
        return rules.stream()
                .map(rule -> applyRule(rule, workingItems))
                .map(PricePartition::getValue)
                .reduce(0, Integer::sum);
    }

    private PricePartition applyRule(PricingRule rule, List<Item> items) {
        PricePartition ruleResult = rule.apply(items);
        removeSettledItems(items, ruleResult);
        return ruleResult;
    }

    private void removeSettledItems(List<Item> items, PricePartition ruleResult) {
        ruleResult.getItems().forEach(items::remove);
    }
}
