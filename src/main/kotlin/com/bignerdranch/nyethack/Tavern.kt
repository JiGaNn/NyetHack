package com.bignerdranch.nyethack
import com.bignerdranch.nyethack.extensions.random

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie", "Quill")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins", "Martins", "Hellraiser")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
                    .readLines()
val patronGold = mutableMapOf<String, Double>()

fun main() {

    if (patronList.contains("Eli"))
        println("The tavern master says: Eli's in the back playing cards.")
    else
        println("The tavern master says: Eli isn't here.")

    if (patronList.containsAll(listOf("Mordoc", "Sophie")))
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    else
        println("The tavern master says: Nay, they departed hours ago.")
    println()

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    val menuDrinks = mutableListOf<String>()
    val drinkTypes = mutableSetOf<String>()
    menuList.forEach {
        val (type, name, _) = it.split(",")
        menuDrinks += name.lowercase().capitalize()
        drinkTypes += type
    }

    var max = 0
    menuDrinks.forEach {
        val length = it.length
        if (length > max)
            max = length
    }
    println("\n\t\t\t~~ $TAVERN_NAME price list ~~")
    drinkTypes.forEach {
        println("\t\t\t\t\t\t$it")
        menuList.forEachIndexed { index, drink ->
            if(drink.contains(it)) {
                val name = menuDrinks[index]
                val price = drink.split(",").last()
                var points = 30
                points += (max - name.length - price.substring(1, price.indexOf('.')).length)
                println("$name${".".repeat(points)}$price$")
            }
        }
    }
    println()

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.random(), menuList.random())
        orderCount++
    }
    displayPatronBalances()
    bouncer()
}
private fun bouncer() {
    val deletePatrons = mutableListOf<String>()
    patronGold.forEach { (patron, balance) ->
        if (balance < 0)
            deletePatrons += patron
    }
    uniquePatrons -= deletePatrons
    patronGold -= deletePatrons
    println("Hey, ${ deletePatrons.reduce { it, next -> "$it, $next"} }, get out of here! You're don't have money!")
    println(uniquePatrons)
    println(patronGold)
}
private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}
private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}
private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath")
        "$patronName exclaims: ${"Ah, delicious $name!".toDragonSpeak()}"
    else
        "$patronName says: Thanks for the $name."
    println(phrase)
    println()
}
private fun String.toDragonSpeak() =
    this.replace(Regex("[aeiouAEIOU]")) {
        when (it.value.lowercase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }