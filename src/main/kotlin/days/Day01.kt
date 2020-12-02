package days

import io.vavr.collection.List

class Day01 : Day(1) {
    override fun partOne() = solve(inputAsVavrInts, 2)

    override fun partTwo() = solve(inputAsVavrInts, 3)

    private fun solve(lines: List<Int>, size: Int): Int =
            lines.combinations(size)
                    .filter { it.sumValues() == 2020 }
                    .map { it.mulValues() }
                    .head()
}

// --- using ArrowKt ---
//    override fun partOne(): Int {
//        val nums = inputList.map { it.toInt() }
//        return nums.product(nums)
//                .filter { it.sum() == 2020 }
//                .map { it.mul() }
//                .first()
//    }
//
//    override fun partTwo(): Int {
//        val nums = inputList.map { it.toInt() }
//        return nums.product(nums).product(nums)
//                .filter { it.sum() == 2020 }
//                .map { it.mul() }
//                .first()
//    }


