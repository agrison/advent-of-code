package me.grison.aoc

import util.CYAN
import util.RESET

val LETTERS_TO_FORM = mapOf(
    "A" to " ## \n#  #\n#  #\n####\n#  #\n#  #",
    "B" to "### \n#  #\n### \n#  #\n#  #\n### ",
    "C" to " ## \n#  #\n#   \n#   \n#  #\n ## ",
    "D" to "### \n#  #\n#  #\n#  #\n#  #\n### ",
    "E" to "####\n#   \n### \n#   \n#   \n####",
    "F" to "####\n#   \n### \n#   \n#   \n#   ",
    "G" to " ## \n#   \n#   \n# ##\n#  #\n ## ",
    "H" to "#  #\n#  #\n####\n#  #\n#  #\n#  #",
    "J" to "  ##\n   #\n   #\n   #\n#  #\n ## ",
    "K" to "#  #\n# # \n##  \n# # \n# # \n#  #",
    "L" to "#   \n#   \n#   \n#   \n#   \n####",
    "O" to " ## \n#  #\n#  #\n#  #\n#  #\n ## ",
    "P" to "### \n#  #\n### \n#   \n#   \n#   ",
    "P2" to "### \n#  #\n#  #\n### \n#   \n#   ",
    "R" to "### \n#  #\n#  #\n### \n#  #\n#  #",
    "R2" to "### \n#  #\n#  #\n### \n# # \n#  #",
    "S" to " ###\n#   \n ## \n   #\n   #\n### ",
    "U" to "#  #\n#  #\n#  #\n#  #\n#  #\n ## ",
    "Z" to "####\n   #\n  # \n #  \n#   \n####",
)

val FORMS_TO_LETTER = LETTERS_TO_FORM.entries.associate { (k, v) -> v to k }


fun String.ocrExtract(height: Int = 5): String {
    val display = replace("$CYAN#$RESET", "#")
    val cols = display.indexOf('\n')
    return (0 .. (cols / height)).fold("") { acc, col ->
        val (start, end) = p(col * height, col * height + (height-1))
        val form = display.allLines().map { it.substring(start, end) }.join("\n")
        acc + (FORMS_TO_LETTER[form] ?: "").replace("\\d".regex(), "")
    }
}
