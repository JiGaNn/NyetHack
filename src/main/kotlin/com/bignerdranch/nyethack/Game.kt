package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player()
    player.castFireball()
    printPlayerStatus(player)
}
private fun printPlayerStatus(
    player: Player
) {
    println("Aura: ${player.auraColor()}\nBlessed: ${if (player.isBlessed) "YES" else "NO"}")
    println("${player.name} ${player.formatHealthStatus()}")
}