package days

class Day07 : Day(7) {
    override fun title() = "Handy Haversacks"

    override fun partOne() = bagsWithGold(loadBags().first)

    override fun partTwo() = nestedBags(loadBags().second)

    // recursive
    private fun bagsWithGold(bagsIn: Bags = loadBags().first, color: String = "shiny gold",
                             set: Colors = mutableSetOf()): Int {
        bagsIn.getValue(color).forEach { bagsWithGold(bagsIn, it, set + it) }
        return set.size
    }

    // recursive
    private fun nestedBags(bags: BagsCount, color: String = "shiny gold"): Int =
            bags.getValue(color).scan(0) { acc, it ->
                (acc + it.first) + it.first * nestedBags(bags, it.second)
            }.last()

    private fun loadBags(): Pair<Bags, BagsCount> {
        val bagsIn = mutableMapOf<Color, Colors>().withDefault { mutableSetOf() }
        val bags = mutableMapOf<Color, ColorCounts>().withDefault { mutableListOf() }
        inputList.forEach {
            val (color) = it.split(" bags")
            "(\\d+) (.+?) bags?[,.]".regex().findAll(it.split("contain ")[1]).forEach { b ->
                val (count, colorInside) = b.destructured
                bagsIn[colorInside] = bagsIn.getValue(colorInside) + color
                bags[color] = bags.getValue(color) + Pair(count.toInt(), colorInside)
            }
        }

        return Pair(bagsIn, bags)
    }
}

typealias Color = String
typealias Colors = MutableSet<Color>
typealias ColorCount = Pair<Int, Color>
typealias ColorCounts = MutableList<ColorCount>
typealias Bags = Map<Color, Colors>
typealias BagsCount = Map<Color, ColorCounts>

// Note:
// was using [] with Map but it doesn't work with `withDefault` since it would break the Map contract (returning null if not found)
// still learning Kotlin, lost a good 5 minutes on this :-D
// https://youtrack.jetbrains.com/issue/KT-11851?_ga=2.219652313.1528153521.1607319978-2134806531.1605262314