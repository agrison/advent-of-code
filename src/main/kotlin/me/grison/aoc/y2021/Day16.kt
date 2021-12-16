package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.product
import me.grison.aoc.toInt
import me.grison.aoc.toLong
import java.math.BigInteger

class Day16 : Day(16, 2021) {
    override fun title() = "Packet Decoder"

    override fun partOne() = versionSum

    override fun partTwo() = value

    private var pointer = 0
    private var versionSum = 0
    private val stack = loadInput()
    private val value = evaluateBITS()

    private fun loadInput(): String {
        return BigInteger(inputString, 16).toString(2).padStart(inputString.length * 4, '0')
    }

    private fun evaluateBITS(): Long {
        val version = read(3)
        val packetTypeId = read(3)
        versionSum += version

        // literal value
        if (packetTypeId == 4)
            return BigInteger(readPacket(), 2).toLong()

        val subPackets = mutableListOf<Long>()
        when (next()) {
            0 -> readTotalLength(subPackets)
            1 -> readNumberOfSubPackets(subPackets)
        }

        return when (packetTypeId) {
            0 -> subPackets.sum()
            1 -> subPackets.product()
            2 -> subPackets.min()!!
            3 -> subPackets.max()!!
            5 -> (subPackets[0] > subPackets[1]).toLong()
            6 -> (subPackets[0] < subPackets[1]).toLong()
            7 -> (subPackets[0] == subPackets[1]).toLong()
            else -> -1
        }
    }

    private fun readTotalLength(subPackets: MutableList<Long>) {
        val length = read(15)
        val subPacketEnd = pointer + length
        while (pointer < subPacketEnd) {
            subPackets.add(evaluateBITS())
        }
    }

    private fun readNumberOfSubPackets(subPackets: MutableList<Long>) {
        repeat(read(11)) {
            subPackets.add(evaluateBITS())
        }
    }

    private fun read(n: Int): Int {
        val s = stack.substring(pointer, n + pointer)
        pointer += n
        return s.toInt(2)
    }

    private fun next() = stack[pointer++].toString().toInt(2)

    private fun readPacket(): String {
        val sb = StringBuilder("0")
        while (true) {
            val bit = next()
            val s = stack.substring(pointer, pointer + 4)
            pointer += 4
            sb.append(s)
            if (bit == 0) break
        }
        return sb.toString()
    }
}