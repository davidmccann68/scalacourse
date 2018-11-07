package com.reivertech.bjss

/**
  * Simple POSO class which defines an item in a shopping basket.
  *
  * @param name     item name, e.g. "Apples"
  * @param price    standard item price
  */
class Item(val name: String, val _price: BigDecimal) {

    def price(): BigDecimal = {
        _price.setScale(2)
    }
}
