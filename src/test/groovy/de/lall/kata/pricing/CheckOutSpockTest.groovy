package de.lall.kata.pricing

import de.lall.kata.pricing.pricingrules.EnforceRuleSetCompletenessRule
import de.lall.kata.pricing.pricingrules.FixedPriceRule
import de.lall.kata.pricing.pricingrules.MultipleItemRule
import spock.lang.Specification
import spock.lang.Unroll

class CheckOutSpockTest extends Specification {
    CheckOut checkOut = new CheckOut([
            new MultipleItemRule(new Item("A"), 3, 100),
            new MultipleItemRule(new Item("B"), 2, 80),
            new FixedPriceRule(new Item("A"), 40),
            new FixedPriceRule(new Item("B"), 50),
            new FixedPriceRule(new Item("C"), 25),
            new FixedPriceRule(new Item("D"), 20),
            new EnforceRuleSetCompletenessRule()
    ])

    @Unroll
    void "the items '#scannedItemIds' have a price of #expectedResult"() {
        given:
        scannedItemIds.each{ checkOut.scan(new Item(it)) }

        expect:
        checkOut.total() == expectedResult

        where:
        expectedResult | scannedItemIds
        0              | ""
        40             | "A"
        90             | "AB"
        135            | "CDBA"
        80             | "AA"
        100            | "AAA"
        140            | "AAAA"
        180            | "AAAAA"
        200            | "AAAAAA"
        150            | "AAAB"
        180            | "AAABB"
        200            | "AAABBD"
        200            | "DABABA"
    }

    void "calculate correct intermediate results, if items are added incrementally"() {
        when: checkOut.scan(new Item("A"))
        then: checkOut.total() == 40

        when: checkOut.scan(new Item("B"))
        then: checkOut.total() == 90

        when: checkOut.scan(new Item("A"))
        then: checkOut.total() == 130

        when: checkOut.scan(new Item("A"))
        then: checkOut.total() == 150

        when: checkOut.scan(new Item("B"))
        then: checkOut.total() == 180
    }

    void "throws an exception, if there are items unmatched by any rule"() {
        given:
        checkOut.scan(new Item("A"))
        checkOut.scan(new Item("E"))
        checkOut.scan(new Item("F"))

        when:
        checkOut.total()

        then:
        def e = thrown(IllegalStateException)
        e.message == "Found 2 unhandled items"
    }
}
