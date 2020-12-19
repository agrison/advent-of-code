package util

import me.grison.aoc.Day
import org.reflections.Reflections
import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

@ExperimentalTime
object Runner {

    private val defaultYear = 2020
    private var reflections = Reflections("me.grison.aoc")
    private var allYears = false


    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty()) {
            val (year, day) = when("/" in args[0]) {
                true -> Pair(args[0].split("/")[0].toInt(), args[0].split("/")[1])
                false -> Pair(defaultYear, args[0])
            }
            println("\uD83C\uDF85 === Advent of Code $year === \uD83C\uDF85\n")

            reflections = Reflections("me.grison.aoc.y$year")

            if (day == "*") {
                val allDayClasses = getAllDayClasses()
                if (allDayClasses != null) {
                    allDayClasses.sortedBy { dayNumber(it.simpleName) }.forEach { printDay(it) }
                }
                else {
                    printError("Couldn't find day classes - make sure you're in the right directory and try building again")
                }
            } else {
                val dayClass = getAllDayClasses() ?.find { dayNumber(it.simpleName) == day.toInt() }
                if (dayClass != null) {
                    printDay(dayClass)
                }
                else {
                    printError("Day $day not found")
                }
            }
        }
        else {
            println("\uD83C\uDF85 === Advent of Code (all years) === \uD83C\uDF85\n")
            allYears = true
            val allDayClasses = getAllDayClasses()
            if (allDayClasses != null) {
                allDayClasses.sortedBy { dayNumber(it.simpleName) }.forEach { printDay(it) }
            }
            else {
                printError("Couldn't find day classes - make sure you're in the right directory and try building again")
            }
        }
    }

    private fun getAllDayClasses(): MutableSet<Class<out Day>>? {
        return reflections.getSubTypesOf(Day::class.java)
    }

    private fun printDay(dayClass: Class<out Day>) {
        val day = dayClass.constructors[0].newInstance() as Day
        println("\n\uD83C\uDF84 --- Day ${if (allYears) day.year.toString() + "/" else ""}${day.dayNumber}: ${day.title()} ---")

        val partOne = measureTimedValue { day.partOne() ?: "empty" }
        val partTwo = measureTimedValue { day.partTwo() ?: "empty" }
        printParts(partOne, partTwo)
    }

    private fun printParts(partOne: TimedValue<Any>, partTwo: TimedValue<Any>) {
        val padding = max(partOne.value.toString().length, partTwo.value.toString().length) + 14        // 14 is 8 (length of 'Part 1: ') + 6 more
        println(" \uD83C\uDF1F Part 1: ${partOne.value}".padEnd(padding, ' ') + "(${partOne.duration})")
        println(" \uD83C\uDF1F Part 2: ${partTwo.value}".padEnd(padding, ' ') + "(${partTwo.duration})")
    }

    private fun printError(message: String) {
        System.err.println("\n=== ERROR ===\n$message")
    }

    private fun dayNumber(dayClassName: String) = dayClassName.replace("Day", "").toInt()
}
