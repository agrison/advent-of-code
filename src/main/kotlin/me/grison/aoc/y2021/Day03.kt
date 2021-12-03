package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.at
import me.grison.aoc.binary
import me.grison.aoc.p

class Day03 : Day(3, 2021) {
    override fun title() = "Binary Diagnostic"

    override fun partOne(): Int {
        var (gamma, epsilon) = p("", "")
        for (bit in 0.until(inputList[0].length)) {
            val counts = inputList.groupingBy { s -> s.at(bit) }.eachCount()

            gamma += if (counts['0'] ?: 0 > counts['1'] ?: 0) '0' else '1'
            epsilon += if (counts['0'] ?: 0 > counts['1'] ?: 0) '1' else '0'
        }

        return gamma.binary() * epsilon.binary()
    }

    override fun partTwo(): Int {
        var (oxygenInputs, co2Inputs) = p(inputList, inputList)

        var (oxygen, co2) = p("", "")
        for (bit in 0.until(inputList[0].length)) {
            if (oxygenInputs.isNotEmpty()) {
                oxygenInputs = filterDiagnostics(oxygenInputs, bit, arrayOf('0', '1'))
                if (oxygenInputs.isNotEmpty())
                    oxygen = oxygenInputs[0]
            }

            if (co2Inputs.isNotEmpty()) {
                co2Inputs = filterDiagnostics(co2Inputs, bit, arrayOf('1', '0'))
                if (co2Inputs.isNotEmpty())
                    co2 = co2Inputs[0]
            }
        }

        return oxygen.binary() * co2.binary()
    }

    fun filterDiagnostics(list: List<String>, position: Int, expected: Array<Char>): List<String> =
        list.groupingBy { s -> s.at(position) }.eachCount().let {
            return if (it['0'] ?: 0 > it['1'] ?: 0) {
                list.filter { s -> s.at(position) == expected[0] }
            } else {
                list.filter { s -> s.at(position) == expected[1] }
            }
        }

}

