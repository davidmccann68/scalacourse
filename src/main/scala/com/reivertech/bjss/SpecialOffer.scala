package com.reivertech.bjss

import java.time.LocalDate

class SpecialOffer(val name: String,
                   val calc: (List[Item]) => BigDecimal,
                   val from: LocalDate = LocalDate.MIN,
                   val until: LocalDate = LocalDate.MAX) {

    def isValid(): Boolean = {
        val today = LocalDate.now()
        !from.isAfter(today) && !until.isBefore(today)
    }

    def apply(items: List[Item]): BigDecimal = {
        calc.apply(items).setScale(2)
    }
}
