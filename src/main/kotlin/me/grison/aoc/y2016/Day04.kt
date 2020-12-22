package me.grison.aoc.y2016

import me.grison.aoc.*

class Day04 : Day(4, 2016) {
    override fun title() = "Security Through Obscurity"

    private val rooms = inputList.map {
        Triple(
            it.substringBeforeLast("-").replace("-", ""),
            it.allInts().first(),
            it.after("[").before("]")
        )
    }

    override fun partOne() = findRooms().first

    override fun partTwo() = findRooms().second?.first

    private fun findRooms(): Pair<Int, Pair<Int, String>?> {
        var total = 0
        var wanted: Pair<Int, String>? = null
        for ((name, sector, checksum) in rooms) {
            if (mostCommon(name) == checksum) {
                total += sector
                val decrypted = name.map { letter -> (((letter.toInt() - 97 + sector) % 26) + 97).toChar() }.join()
                if (decrypted.startsWith("northpole")) {
                    wanted = p(sector, decrypted)
                }
            }
        }
        return p(total, wanted)
    }

    private fun mostCommon(name: String): String {
        return name.toSet().map { letter -> p(0 - name.count { it == letter }, letter) }
            .sortedWith(compareBy({ it.first }, { it.second }))
            .slice(0..4)
            .map { it.second }.join()
    }
}
