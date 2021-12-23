package me.grison.aoc.y2021

import me.grison.aoc.*
import me.grison.aoc.y2021.Day23.Amphipod.*
import java.util.*
import kotlin.math.abs

class Day23 : Day(23, 2021) {
    override fun title() = "Amphipod"

    enum class Amphipod { A, B, C, D }
    private val roomExits = listOf(2, 4, 6, 8)

    override fun partOne() = organizeAmphipods(loadRooms(0), 2)

    override fun partTwo() = organizeAmphipods(loadRooms(1), 4)

    private fun loadRooms(problem: Int): Rooms {
        return inputGroups[problem].lines()
            .map { it.filter { it in 'A'..'D' }.stringList() }
            .filter { it.isNotEmpty() }
            .transpose()
            .map { it.reversed() }
            .map { it.map { Amphipod.valueOf(it).ordinal } }
    }

    private fun organizeAmphipods(rooms: List<List<Int>>, roomSize: Int): Int {
        val queue = PriorityQueue<Situation>()
        queue.add(Situation(roomSize, 0, rooms))

        val visited = mutableSetOf<Pair<Rooms, Hallway>>()
        while (queue.isNotEmpty()) {
            val situation = queue.poll()
            if (situation.isOrganized())
                return situation.energy

            if (situation.currentState() in visited)
                continue
            visited.add(situation.currentState())

            // first move amphipods from rooms to hallway
            for ((roomNumber, currentRoom) in situation.rooms.withIndex()) {
                if (currentRoom.isNotEmpty() && !currentRoom.all { it == roomNumber }) {
                    val amphipod = currentRoom.last()
                    val left = (roomExits[roomNumber] downTo 0) - roomExits
                    val right = (roomExits[roomNumber]..10) - roomExits
                    for (direction in listOf(left, right)) {
                        for (position in direction) {
                            if (situation.hallwayIsOccupied(position)) break
                            val steps = roomSize - currentRoom.size + abs(roomExits[roomNumber] - position) + 1
                            Situation(roomSize,
                                situation.energy + steps * cost(amphipod),
                                situation.rooms.update(roomNumber, currentRoom.butLast()),
                                situation.hallway.update(position, amphipod)
                            ).also { if (it.currentState() !in visited) queue.add(it) }
                        }
                    }
                }
            }

            // then move amphipods back to room where possible
            for ((index, amphipod) in situation.hallway.withIndex()) {
                if (cannotEnterRoom(situation, index, amphipod))
                    continue

                val steps = roomSize - situation.occupants(amphipod!!) + abs(roomExits[amphipod] - index)
                Situation(roomSize,
                    situation.energy + steps * cost(amphipod),
                    situation.rooms.update(amphipod, situation.rooms[amphipod] + amphipod),
                    situation.hallway.update(index, null)
                ).also { if (it.currentState() !in visited) queue.add(it) }
            }
        }

        return -1
    }

    private fun cannotEnterRoom(situation: Situation, index: Int, amphipod: Int?): Boolean {
        return situation.hallwayIsFree(index) ||
                index < roomExits[amphipod!!] && !situation.hallwayIsFree(index + 1, roomExits[amphipod]) ||
                index > roomExits[amphipod] && !situation.hallwayIsFree(roomExits[amphipod] + 1, index) ||
                situation.rooms[amphipod].any { it != amphipod }
    }

    private fun cost(amphipod: Int): Int {
        return when (Amphipod.values()[amphipod]) {
            A -> 1
            B -> 10
            C -> 100
            D -> 1000
        }
    }

    data class Situation(
        val size: Int,
        val energy: Int, val rooms: Rooms,
        val hallway: Hallway = List(11) { null }
    ) : Comparable<Situation> {
        override fun compareTo(other: Situation) = energy.compareTo(other.energy)

        fun currentState() = p(rooms, hallway)
        fun occupants(amphipod: Int) = rooms[amphipod].size
        fun hallwayIsOccupied(i: Int) = hallway[i] != null
        fun hallwayIsFree(i: Int) = hallway[i] == null
        fun hallwayIsFree(from: Int, to: Int) = hallway.subList(from, to).all { it == null }
        fun isOrganized(): Boolean {
            return hallway.all { it == null } &&
                    rooms.all { it.size == size } &&
                    rooms.withIndex().all { it.value.isOrganized(it.index) }
        }
    }
}

private typealias Room = List<Int>

fun Room.isOrganized(roomNumber: Int) = isNotEmpty() && all { it == roomNumber }
private typealias Rooms = List<Room>
private typealias Hallway = List<Int?>