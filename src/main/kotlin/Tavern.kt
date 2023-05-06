import java.util.*

fun main() {
//     var beverage = readlnOrNull()?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//    var beverage = readLine()?.let {
//        if (it.isNotBlank()) it.capitalize()
//        else "Buttered Ale"
//    }
//    beverage = null
//    var beverage = readlnOrNull()!!.capitalize() // ТОЛЬКО КОГДА УВЕРЕН, ЧТО NULL НЕ БУДЕТ, ИНАЧЕ ВЫБРОСИТСЯ NULLPOINTEREXCEPTION
    var beverage = readLine()
    beverage?.let {
        beverage = it.capitalize()
    } ?: println("I can't do that without crashing - beverage was null!")

    var beverageServed: String = beverage ?: "Buttered Ale"

     println(beverage)
 }