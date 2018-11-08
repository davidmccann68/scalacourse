package com.reivertech.bjss

import java.time.LocalDate


class ShoppingTripMain {

    def main(args: Array[String]): Unit = {
        price(args, LocalDate.now())
    }

    /**
      * Returns the total price of the shopping trip by appling current discounts to a basket, but
      * also prints output to the console as a side-effect (quel horreur!).
      *
      * @param args names of items to put in the basket
      * @param date date the basket is created
      * @return     the total price of the basket
      */
    def price(args: Array[String], date: LocalDate) : BigDecimal = {
        val basket = new PriceBasket(args)

        val applesOffer = new SpecialOffer("Apples 10% off", tenPercentOffApples)
        val twoSoupOffer = new SpecialOffer("Half price loaf with 2 soups", buyTwoSoupGetHalfPriceBread)

        for(item <- basket.items()) {
            println(item.name + ":\t\t" + fmt(item.price()))
        }

        val subtotal = basket.subtotal()
        var total = subtotal

        println("Subtotal:\t" + fmt(subtotal))

        var offersApplied = false

        for(offer <- Seq(applesOffer, twoSoupOffer)) {

            if (offer.valid(date)) {
                val discount = offer.apply(basket.items())

                if(discount < BigDecimal.valueOf(0)) {
                    println(offer.name + ":\t" + fmt(discount))
                    offersApplied = true
                }

                total += discount
            }
        }

        if(!offersApplied) {
            println("(no offers available)")
        }

        println("Total:\t\t" + fmt(total))
        total
    }

    /**
      * Calculate a 10% discount off apples.
      *
      * @param items    items to process
      * @return         the discount, if any
      */
    def tenPercentOffApples(items: List[Item]): BigDecimal = {
        var discount = BigDecimal.valueOf(0)

        for(item <- items) {
            if(item.name.equals("Apples")) {
                discount -= item.price * BigDecimal.valueOf(0.1)
            }
        }

        discount
    }

    /**
      * Each 2 soups bought mean a 50% discount on one loaf of bread.
      *
      * @param items    items to process
      * @return         the discount, if any
      */
    def buyTwoSoupGetHalfPriceBread(items: List[Item]): BigDecimal = {
        val soupCount = items.count(i => i.name.equals("Soup"))
        val breadItems = items.filter(i => i.name.equals("Bread"))

        val discountedBread = Math.min(soupCount / 2, breadItems.length)

        var discount = BigDecimal.valueOf(0)

        for(i <- 0 until discountedBread) {
            discount -= breadItems(i).price / 2
        }

        discount
    }

    def fmt(value: BigDecimal): String = {
        "£%.2f".format(value)
    }
}
