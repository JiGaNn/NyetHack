package com.bignerdranch.nyethack

fun main() {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) swordsJuggling = 2

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("Your juggle $swordsJuggling swords!")
}
fun juggleSwords(swordsJuggling: Int) {
    require(swordsJuggling >= 3) {"Juggle at least 3 swords to be exciting"}
}
fun proficiencyCheck(swordsJuggling: Int?) {
//    swordsJuggling ?: throw com.bignerdranch.nyethack.UnskilledSwordJugglerException()
    checkNotNull(swordsJuggling) { "com.bignerdranch.nyethack.Player cannot juggle swords" }
}
class UnskilledSwordJugglerException() :
    IllegalStateException("com.bignerdranch.nyethack.Player cannot juggle swords")