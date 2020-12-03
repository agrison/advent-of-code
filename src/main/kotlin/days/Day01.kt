package days

class Day01 : Day(1) {
    override fun title() = "Report Repair"

    override fun partOne() = solve(2)

    override fun partTwo() = solve(3)

    private fun solve(size: Int): Int =
            inputAsVavrInts.combinations(size)
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

// -- also part 1 can be 7 times faster using a Set --
//    override fun partOne(): Int {
//        val input = inputList.map{it.toInt()}.toSet()
//        return input.mapNotNull { v -> if (input.contains(2020 - v)) v * (2020 - v) else null }
//                .first()
//    }

