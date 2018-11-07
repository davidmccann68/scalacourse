package com.reivertech.bjss

import org.scalatest.FunSuite

class PriceBasketTest extends FunSuite {

    test("subtotal works") {
        val basket = new PriceBasket(Seq("Apples", "Bread", "Apples"))
        assert(basket.subtotal() == BigDecimal.valueOf(2.8))
    }

    test("applying discount works") {
        val basket = new PriceBasket(Seq("Apples", "Bread", "Apples"))
        val total = basket.total(Seq(new SpecialOffer("Half Price", halfPriceOnEverything)))

        assert(total == BigDecimal.valueOf(1.4))
    }

    def halfPriceOnEverything(items: List[Item]): BigDecimal = {
        var discount = BigDecimal.valueOf(0)

        for(item <- items) {
            discount -= item.price / 2
        }

        discount
    }
}
