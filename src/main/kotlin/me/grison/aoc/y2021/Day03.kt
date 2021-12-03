package me.grison.aoc.y2021

import me.grison.aoc.*

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
        val oxygenInputs: MutableCollection<String> = inputList.toMutableList()
        val co2Inputs: MutableCollection<String> = inputList.toMutableList()
        var (oxygen, co2) = p("", "")

        for (bit in 0.until(inputList[0].length)) {
            filterDiagnostics(oxygenInputs, bit, arrayOf('0', '1')).head()?.let { oxygen = it }
            filterDiagnostics(co2Inputs, bit, arrayOf('1', '0')).head()?.let { co2 = it }
        }

        return oxygen.binary() * co2.binary()
    }

    private fun filterDiagnostics(
        list: MutableCollection<String>,
        position: Int,
        expected: Array<Char>
    ): MutableCollection<String> {
        list.groupingBy { s -> s.at(position) }.eachCount().let {
            return list.reset(if (it['0'] ?: 0 > it['1'] ?: 0) {
                list.filter { s -> s.at(position) == expected[0] }
            } else {
                list.filter { s -> s.at(position) == expected[1] }
            })
        }
    }
}
