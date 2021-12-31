package me.grison.aoc

import kotlin.math.abs

data class Point3D(val x: Int, val y : Int, val z : Int) {
    companion object {
        val ZERO = Point3D(0, 0, 0)
    }

    constructor(coordinates: List<Int>) : this(coordinates[0], coordinates[1], coordinates[2])

    operator fun plus(p : Point3D) = Point3D(x + p.x, y + p.y, z + p.z)

    operator fun minus(p : Point3D) = Point3D(x - p.x, y - p.y, z - p.z)

    operator fun times(p : Point3D) = Point3D(x * p.x, y * p.y, z * p.z)

    fun manhattan(p : Point3D) = abs(x - p.x) + abs(y - p.y) + abs(z - p.z)
}