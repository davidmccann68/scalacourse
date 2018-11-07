package com.reivertech.bjss

import scala.collection.mutable.ListBuffer

/**
  * Defines a basket of goods for the purpose of this tutorial. The price of goods is currently
  * hard-coded here.
  *
  * @param args basket items, which for the sake of this tutorial are just names
  */
class PriceBasket(val args: Seq[String]) {

    var _items = new ListBuffer[Item]

    for(arg <- args) {
        arg match {
            case "Apples" => _items += new Item(arg, BigDecimal.apply("1.00"))
            case "Bread" => _items += new Item(arg, BigDecimal.apply("0.80"))
            case "Milk" => _items += new Item(arg, BigDecimal.apply("1.30"))
            case "Soup" => _items += new Item(arg, BigDecimal.apply("0.65"))

            // Unrecognized item type intentionally a no-op
        }
    }

    def items(): List[Item] = {
        _items.toList
    }

    /**
      * Returns the subtotal of all items, i.e. without any special offers applied.
      *
      * @return the basket subtotal
      */
    def subtotal(): BigDecimal = {
        var retVal = BigDecimal.valueOf(0)

        for(item <- items) {
            retVal += item.price
        }

        return retVal
    }

    /**
      * Returns the actual total of all items, applying the special offers passed in.
      *
      * @param specialOffers    special offers which may apply
      * @return                 the basket total
      */
    def total(specialOffers: Seq[SpecialOffer]): BigDecimal = {
        var retVal = subtotal()

        for(offer <- specialOffers) {
            retVal += offer.apply(items)
        }

        return retVal
    }
}
