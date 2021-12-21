package me.grison.aoc.y2021

import io.vavr.collection.Vector
import me.grison.aoc.*
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.collections.ArrayDeque
import kotlin.streams.toList

class Day19 : Day(19, 2021) {
    override fun title() = "Beacon Scanner"

    private val referenceScanner = findReferenceScanner()

    override fun partOne(): Int {
        return referenceScanner.map { it.first }.flatten().toSet().size
    }

    override fun partTwo(): Int {
        return Vector.ofAll(referenceScanner.map { it.second })
            .combinations(2)
            .maxOf { it[0].manhattan(it[1]) }
    }

    private fun loadScanners() : ConcurrentLinkedDeque<Scanner> {
        return ConcurrentLinkedDeque<List<Point3D>>().let { scanners ->
            inputGroups.forEach { group ->
                group.lines().tail().map { it.allInts() }.map { Point3D(it) }
                    .also { scanners.add(it) }
            }
            scanners
        }
    }

    private fun findReferenceScanner(): List<ScannerAndOrigin> {
        val scanners = loadScanners()

        val referenceScanner = mutableListOf(p(scanners.shift(), Point3D.ZERO))
        while (scanners.isNotEmpty()) {
            scanners.parallelStream().filter { scanner ->
                scannersOverlap(referenceScanner, orientations(scanner))?.let {
                    referenceScanner.add(it)
                    true
                } ?: false
            }.also { scanners.removeAll(it.toList()) }
        }

        return referenceScanner
    }

    private fun orientations(scanner: Scanner) = sequence {
        // keep original
        yield(scanner)
        // rotate
        yield(scanner.map { (x, y, z) -> Point3D(y, z, x) })
        yield(scanner.map { (x, y, z) -> Point3D(z, x, y) })

        // rotate + negate 2nd, 3rd axis
        yield(scanner.map { (x, y, z) -> Point3D(x, -y, -z) })
        yield(scanner.map { (x, y, z) -> Point3D(y, -z, -x) })
        yield(scanner.map { (x, y, z) -> Point3D(z, -x, -y) })

        // rotate + negate 1st, 3rd axis
        yield(scanner.map { (x, y, z) -> Point3D(-x, y, -z) })
        yield(scanner.map { (x, y, z) -> Point3D(-y, z, -x) })
        yield(scanner.map { (x, y, z) -> Point3D(-z, x, -y) })

        // rotate + negate 1st, 2nd axis
        yield(scanner.map { (x, y, z) -> Point3D(-x, -y, z) })
        yield(scanner.map { (x, y, z) -> Point3D(-y, -z, x) })
        yield(scanner.map { (x, y, z) -> Point3D(-z, -x, y) })

        // change basis + negate all axis
        yield(scanner.map { (x, y, z) -> Point3D(-x, -z, -y) })
        yield(scanner.map { (x, y, z) -> Point3D(-y, -x, -z) })
        yield(scanner.map { (x, y, z) -> Point3D(-z, -y, -x) })

        // change basis + negate 1st axis
        yield(scanner.map { (x, y, z) -> Point3D(-x, z, y) })
        yield(scanner.map { (x, y, z) -> Point3D(-y, x, z) })
        yield(scanner.map { (x, y, z) -> Point3D(-z, y, x) })

        // change basis + negate 2nd axis
        yield(scanner.map { (x, y, z) -> Point3D(x, -z, y) })
        yield(scanner.map { (x, y, z) -> Point3D(y, -x, z) })
        yield(scanner.map { (x, y, z) -> Point3D(z, -y, x) })

        // change basis + negate 3rd axis
        yield(scanner.map { (x, y, z) -> Point3D(x, z, -y) })
        yield(scanner.map { (x, y, z) -> Point3D(y, x, -z) })
        yield(scanner.map { (x, y, z) -> Point3D(z, y, -x) })
    }

    private fun scannersOverlap(referenceScanner: List<ScannerAndOrigin>, orientations: Sequence<Scanner>): ScannerAndOrigin? {
        orientations.forEach { differentScanner ->
            referenceScanner.map { it.first }.forEach { scanner ->
                scanner.forEach { point1 ->
                    differentScanner.forEach { point2 ->
                        val differentOrigin = point1 - point2
                        if (differentScanner.count { scanner.contains(it + differentOrigin) } >= 12)
                            return p(differentScanner.map { it + differentOrigin }, differentOrigin)
                    }
                }
            }
        }
        return null
    }

}

typealias Scanner = List<Point3D>
typealias ScannerAndOrigin = Pair<Scanner, Point3D>
