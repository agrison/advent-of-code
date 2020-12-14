package days

class Day14 : Day(14) {
    override fun title() = "Docking Data"

    override fun partOne() = runEmulator { memory, mask, addr, value ->
        memory[addr] = value or mask.replace("X", "0").binaryLong()
        memory[addr] = memory[addr]!! and mask.replace("X", "1").binaryLong()
    }

    override fun partTwo() = runEmulator { memory, mask, addr, value ->
        generateAddresses(addr, mask).forEach { memory[it] = value }
    }

    private fun runEmulator(version: (mem: MutableMap<Long, Long>, m: String, a: Long, v: Long) -> Unit): Long {
        return mutableMapOf<Long, Long>().let { memory ->
            var mask = ""
            inputList.forEach { l ->
                when (l.substring(0, 3)) {
                    "mas" -> mask = l.afterLast(" ")
                    "mem" -> version(memory, mask, l.between("[", "]").toLong(), l.afterLast(" ").toLong())
                }
            }
            memory.values.sum()
        }
    }

    private fun generateAddresses(addr: Long, mask: String): Sequence<Long> = sequence {
        if (mask.isNotEmpty()) {
            generateAddresses(addr / 2, mask.butLast()).forEach { a ->
                when (mask.last()) {
                    'X' -> yieldAll(2 * a..2 * a + 1) // floating, may be 0 or 1
                    '1' -> yield(2 * a + 1)           // overwrite with 1
                    '0' -> yield(2 * a + addr % 2)    // keep current value
                }
            }
        } else yield(0L) // stop
    }
}
