import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

val patronList = mutableListOf("Eli", "Mordoc", "Sophie", "Quill")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins", "Martins", "Hellraiser")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
                    .readLines()

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

//    placeOrder("shandy,Dragon's Breath,5.91")

//    println(patronList)
//    patronList.apply {
//        remove("Eli")
//        add("Alex")
//        add(0, "Alex")
//    }.run(::println)
//    val readOnlyPatronList = patronList.toList()
//    patronList[0] = "Alexis"
//    println(patronList)

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }
}
private fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}
private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}
private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

//    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath")
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    else
        "$patronName says: Thanks for the $name."
    println(phrase)
    println()
//    println(toDragonSpeak("DRAGON'S BREATH: IT'S GOT WHAT ADVENTURES CRAVE"))
}
private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when(it.value.lowercase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }