package me.grison.aoc.y2020

import me.grison.aoc.Day

class Day21 : Day(21, 2020) {
    override fun title() = "Allergen Assessment"

    override fun partOne(): Any {
        val (allergens, ingredients) = load()

        val allAllergens = allergens.values.fold(setOf<String>()) { a, b -> a.union(b) }
        return (ingredients.keys - allAllergens).sumBy { ingredients[it]!! }
    }

    override fun partTwo(): Any {
        val (allergens, _) = load()

        // keep going until all allergens found
        while (allergens.values.map { it.size }.max()!! > 1) {
            // select allergens where there's only one ingredient
            for (allergen in allergens.filter { it.value.size == 1 }.keys) {
                // for every other allergen
                for (allergen2 in (allergens - allergen).keys) {
                    // remove the already known allergen <-> ingredient
                    allergens[allergen2] = allergens[allergen2]!! - allergens[allergen]!!
                }
            }
        }

        // returns ingredients sorted by allergens
        return allergens.map { (all, ing) -> p(all, ing.first()) }
            .sortedBy { it.first }
            .joinToString(",") { it.second }
    }

    private fun load(): Pair<MutableMap<String, Set<String>>, MutableMap<String, Int>> {
        val allergens = mutableMapOf<String, Set<String>>()
        val ingredients = mutableMapOf<String, Int>()

        inputList.forEach { line ->
            val foodIngredients = line.before(" (").normalSplit(" ").toSet()
            for (ing in foodIngredients) {
                ingredients[ing] = (ingredients[ing] ?: 0) + 1
            }
            val foodAllergens = line.before(")").after(" (contains ").normalSplit(", ")
            for (all in foodAllergens) {
                if (all !in allergens)
                    allergens[all] = foodIngredients
                else
                    allergens[all] = allergens[all]!!.intersect(foodIngredients)
            }
        }

        return p(allergens, ingredients)
    }
}

