package com.reivertech.bjss

import org.scalatest.FunSuite

class ShoppingTripTest extends FunSuite {

    val trip = new ShoppingTripMain

    test("test shopping trip with apple discount") {
        val price = trip.price(Array("Apples", "Milk", "Bread"))
        assert(price == BigDecimal.valueOf(3.0))
    }

    test("buy 2 soups get one half-price loaf") {
        val basket = new PriceBasket(Array("Apples", "Soup", "Bread", "Bread", "Soup"))

        val discount = trip.buyTwoSoupGetHalfPriceBread(basket.items.toList)
        assert(discount == BigDecimal.valueOf(-0.4))
    }

    test("buy 4 soups get two half-price loaves") {
        val basket = new PriceBasket(Array("Apples", "Soup", "Bread", "Bread", "Soup", "Soup", "Soup"))

        val discount = trip.buyTwoSoupGetHalfPriceBread(basket.items.toList)
        assert(discount == BigDecimal.valueOf(-0.8))
    }

    test("buy 4 soups get one half-price loaf") {
        val basket = new PriceBasket(Array("Apples", "Soup", "Bread", "Soup", "Soup", "Soup"))

        val discount = trip.buyTwoSoupGetHalfPriceBread(basket.items.toList)
        assert(discount == BigDecimal.valueOf(-0.4))
    }

    test("test shopping trip with no discounts") {
        val price = trip.price(Array("Soup", "Milk", "Bread"))
        assert(price == BigDecimal.valueOf(2.75))
    }
}
