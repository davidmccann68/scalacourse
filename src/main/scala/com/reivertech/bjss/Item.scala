package com.reivertech.bjss

/**
  * Simple POSO class which defines an item in a shopping basket.
  *
  * @param name     item name, e.g. "Apples"
  * @param _price   standard item price
  */
class Item(val name: String, private val _price: BigDecimal) {

    def price(): BigDecimal = {
        _price.setScale(2)
    }
}
