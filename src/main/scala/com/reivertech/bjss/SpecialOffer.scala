package com.reivertech.bjss

import java.time.LocalDate

/**
  * Defines special offer discounts for shopping items, if applicable.
  *
  * @param name     name of the offer
  * @param calc     function which calculates the discount
  * @param from     optional date from which the offer will apply
  * @param until    optional date until which the offer will apply
  */
class SpecialOffer(val name: String,
                   val calc: List[Item] => BigDecimal,
                   val from: LocalDate = LocalDate.MIN,
                   val until: LocalDate = LocalDate.MAX) {

    def valid(day: LocalDate): Boolean = {
        !from.isAfter(day) && !until.isBefore(day)
    }

    def apply(items: List[Item]): BigDecimal = {
        calc.apply(items).setScale(2)
    }
}
