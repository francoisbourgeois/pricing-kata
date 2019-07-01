package de.lall.kata.pricing;

import de.lall.kata.pricing.pricingrules.FixedPriceRule;
import de.lall.kata.pricing.pricingrules.MultipleItemRule;
import de.lall.kata.pricing.pricingrules.PricingRule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CheckOutJUnitTest {
    private List<PricingRule> rules;

    @Before
    public void setup() {
        rules = new ArrayList<PricingRule>() {{
            add(new MultipleItemRule(new Item("A"), 3, 100));
            add(new MultipleItemRule(new Item("B"), 2, 80));
            add(new FixedPriceRule(new Item("A"), 40));
            add(new FixedPriceRule(new Item("B"), 50));
            add(new FixedPriceRule(new Item("C"), 25));
            add(new FixedPriceRule(new Item("D"), 20));
        }};
    }


    // the following is a code copy from the exercise pdf...

    public int calculatePrice(String goods) {
        CheckOut co = new CheckOut(rules);
        for(int i=0; i<goods.length(); i++) {
            co.scan(String.valueOf(goods.charAt(i)));
        }
        return co.total();
    }

    @Test
    public void totals() {
        assertEquals(0, calculatePrice(""));
        assertEquals(40, calculatePrice("A"));
        assertEquals(90, calculatePrice("AB"));
        assertEquals(135, calculatePrice("CDBA"));
        assertEquals(80, calculatePrice("AA"));
        assertEquals(100, calculatePrice("AAA"));
        assertEquals(140, calculatePrice("AAAA"));
        assertEquals(180, calculatePrice("AAAAA"));
        assertEquals(200, calculatePrice("AAAAAA"));
        assertEquals(150, calculatePrice("AAAB"));
        assertEquals(180, calculatePrice("AAABB"));
        assertEquals(200, calculatePrice("AAABBD"));
        assertEquals(200, calculatePrice("DABABA"));
    }

    @Test
    public void incremental() {
        CheckOut co = new CheckOut(rules);
        assertEquals(0, co.total());
        co.scan("A");
        assertEquals(40, co.total());
        co.scan("B");
        assertEquals(90, co.total());
        co.scan("A");
        assertEquals(130, co.total());
        co.scan("A");
        assertEquals(150, co.total());
        co.scan("B");
        assertEquals(180, co.total());
    }
}