package days

class Day02 : Day(2) {
    override fun partOne(): Int {
        return countValid(inputList) { low: Int, high: Int, letter: Char, password: String ->
            (low..high).contains(password.occurrences(letter))
        }
    }

    override fun partTwo(): Int {
        return countValid(inputList) { pos1: Int, pos2: Int, letter: Char, password: String ->
            runCatching {
                (password[pos1 - 1] + password[pos2 - 1]).occurrences(letter) == 1
            }.getOrDefault(false)
        }
    }

    private fun countValid(passwords: List<String>,
                           policy: (a: Int, b: Int, letter: Char, password: String) -> Boolean): Int {
        val regex = Regex("(\\d+)-(\\d+) ([a-z]): ([a-z]+)")
        return passwords.sumBy {
            val (a, b, letter, password) = regex.find(it)!!.destructured
            policy(a.toInt(), b.toInt(), letter[0], password).toInt()
        }
    }
}