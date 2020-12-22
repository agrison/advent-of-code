package me.grison.aoc.y2015

import me.grison.aoc.*
import kotlin.math.*

class Day21 : Day(21, 2015) {
    override fun title() = "RPG Simulator 20XX"

    override fun partOne(): Int {
        var result = Integer.MAX_VALUE
        allBundles().forEach { bundle ->
            player.damage = bundle.damage
            player.armor = bundle.armor
            if (canMove(boss, player))
                result = min(result, bundle.cost)
        }

        return result
    }

    override fun partTwo(): Int {
        var result = 0
        allBundles().forEach { bundle ->
            player.damage = bundle.damage
            player.armor = bundle.armor
            if (!canMove(boss, player))
                result = max(result, bundle.cost)
        }

        return result
    }

    private fun hitAmount(defender: Character, attacker: Character): Double {
        return ceil(defender.health * 1.0 / max(1, attacker.damage - defender.armor))
    }

    private fun canMove(boss: Character, player: Character): Boolean {
        return hitAmount(boss, player) <= hitAmount(player, boss)
    }

    private fun allBundles() = sequence {
        for (weapon in weapons.values) {
            for (armor in armors.values) {
                for (left in rings.values) {
                    for (right in rings.values) {
                        if (right.cost != left.cost) {
                            yield(weapon + armor + left + right)
                        }
                    }
                }
            }
        }
    }

    private val weapons = mapOf(
            "dagger" to Equipment(8, 4, 0),
            "shortsword" to Equipment(10, 5, 0),
            "warhammer" to Equipment(25, 6, 0),
            "longsword" to Equipment(40, 7, 0),
            "greataxe" to Equipment(74, 8, 0)
    )

    private val armors = mapOf(
            "nothing" to Equipment(0, 0, 0),
            "leather" to Equipment(13, 0, 1),
            "chainmail" to Equipment(31, 0, 2),
            "splintmail" to Equipment(53, 0, 3),
            "bandedmail" to Equipment(75, 0, 4),
            "platemail" to Equipment(102, 0, 5)
    )

    private val rings = mapOf(
            "nothing" to Equipment(0, 0, 0),
            "damage+1" to Equipment(25, 1, 0),
            "damage+2" to Equipment(50, 2, 0),
            "damage+3" to Equipment(100, 3, 0),
            "defense+1" to Equipment(20, 0, 1),
            "defense+2" to Equipment(40, 0, 2),
            "defense+3" to Equipment(80, 0, 3),
    )

    private val boss = Character(8, 1, 104)
    private val player = Character(0, 0, 100)

    data class Character(var damage: Int, var armor: Int, var health: Int)

    data class Equipment(val cost: Int, val damage: Int, val armor: Int)

    operator fun Equipment.plus(x: Equipment) =
        Equipment(cost + x.cost, damage + x.damage, armor + x.armor)
}