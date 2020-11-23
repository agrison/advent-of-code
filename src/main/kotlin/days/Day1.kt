package days

class Day1 : Day(1) {

    override fun partOne(): String {
        return inputList.take(2)
            .map { it.toUpperCase() }
            .joinToString(" ")
    }

    override fun partTwo(): String {
        return inputString.split("\n")
            .filterNot { it.isEmpty() }
            .map { it.toUpperCase() }
            .last()
    }
}
