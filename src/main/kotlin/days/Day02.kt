package days

class Day02 : Day(2) {
    override fun partOne() = countValid(inputList) { l, r, letter, pwd ->
        (l..r).contains(pwd.occurrences(letter))
    }

    override fun partTwo() = countValid(inputList) { l, r, letter, pwd ->
        pwd.at(l - 1).eq(letter) != pwd.at(r - 1).eq(letter)
    }

    private fun countValid(passwords: List<String>,
                           policy: (a: Int, b: Int, letter: Char, pwd: String) -> Boolean): Int {
        val regex = Regex("(\\d+)-(\\d+) ([a-z]): ([a-z]+)")
        return passwords.count {
            val (l, r, letter, pwd) = regex.find(it)!!.destructured
            policy(l.toInt(), r.toInt(), letter[0], pwd)
        }
    }
}


