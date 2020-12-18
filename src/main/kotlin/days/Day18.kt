package days

import java.util.*

class Day18 : Day(18) {
    override fun title() = "Operation Order"

    override fun partOne() = inputList.map { evaluate(parse(it)) }.sum()

    override fun partTwo() = inputList.map { evaluate(parse(it, part2 = true)) }.sum()

    private fun evaluate(tokens: List<String>) = Stack<Long>().let { stack ->
        tokens.forEach { token ->
            stack += when (token) {
                "+" -> stack.pop() + stack.pop()
                "*" -> stack.pop() * stack.pop()
                else -> token.toLong()
            }
        }
        stack.last()
    }

    private fun parse(value: String, part2: Boolean = false): List<String> {
        val (expression, stack) = p(mutableListOf<String>(), Stack<String>())

        value.replace(" ", "").stringList().forEach { token ->
            when (token) {
                "(" -> stack += token
                in "0".."9" -> expression += token
                "+", "*" -> {
                    while (!stack.isEmpty() && stack.last() != "(" &&
                            (!part2 || (stack.last() != "*" || token != "+")))
                        expression += stack.pop()
                    stack += token
                }
                ")" -> {
                    while (stack.last() != "(")
                        expression += stack.pop()
                    stack.popIf("(")
                }
            }
        }

        expression.addAll(stack.reversed())
        return expression
    }
}

