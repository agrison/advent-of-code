# Advent Of Code 2020

## Goal

After the 5 last years with Java, Go (finished with Java), OCaml (finished with Java), Python and Clojure (+ some Java), this year I'll be solving the 
[Advent of Code](https://adventofcode.com/2020) with **Kotlin** (hopefully this time I won't need Java). 

1. A deep dive into Kotlin. 
2. Write fast and idiomatic solutions.
3. Using extensions to make code shorter (see [Day.kt](https://github.com/agrison/advent-of-code-2020/blob/master/src/main/kotlin/days/Day.kt)).

## Output

```text
🎅 === Advent of Code 2020 === 🎅

🎄 --- Day 1: Report Repair --- 
 🌟 Part 1: 1003971       (75.7ms)
 🌟 Part 2: 84035952      (1.60s)

🎄 --- Day 2: Password Philosophy --- 
 🌟 Part 1: 645      (11.4ms)
 🌟 Part 2: 737      (3.18ms)

🎄 --- Day 3: Toboggan Trajectory --- 
 🌟 Part 1: 156             (687us)
 🌟 Part 2: 3521829480      (584us)

🎄 --- Day 4: Passport Processing --- 
 🌟 Part 1: 242      (19.9ms)
 🌟 Part 2: 186      (28.5ms)

🎄 --- Day 5: Binary Boarding --- 
 🌟 Part 1: 974      (6.53ms)
 🌟 Part 2: 646      (6.95ms)

🎄 --- Day 6: Custom Customs --- 
 🌟 Part 1: 6630      (10.6ms)
 🌟 Part 2: 3437      (12.2ms)

🎄 --- Day 7: Handy Haversacks --- 
 🌟 Part 1: 287        (12.9ms)
 🌟 Part 2: 48160      (4.73ms)

 🎄 --- Day 8: Handheld Halting --- 
 🌟 Part 1: 1262      (1.87ms)
 🌟 Part 2: 1643      (33.5ms)

🎄 --- Day 9: Encoding Error --- 
 🌟 Part 1: 41682220      (13.6ms)
 🌟 Part 2: 5388976       (19.2ms)

🎄 --- Day 10: Adapter Array ---
 🌟 Part 1: 2414            (1.70ms)
 🌟 Part 2: 21156911906816  (351us)
```

### Running

Project is already setup with gradle. To run the app:

* Navigate to top-level directory on the command line
* Run `./gradlew run` to run all days
* Run `./gradlew run --args $DAY` where `$DAY` is an integer to run a specific day

### Testing

Project includes Junit and Hamcrest and a stub unit test to get you going. To run all tests:

* Navigate to top-level directory on the command line
* Run `./gradlew test`
* Add `--info`, `--debug` or `--stacktrace` flags for more output

##### Test input

By default, instantiations of `Day` classes in tests will use the input files in `src/test/resources`, _not_ those in `src/main/resources`.
This hopefully gives you flexibility - you could either just copy the real input into `src/test/resources` if you want to test
the actual answers, or you could add a file of test data based on the examples given on the Advent of Code description for the day.
The stub `Day01Test` class shows a test of the functionality of `Day01` where the test input differs from the actual input.

### Architecture

* Inputs go into `src/main/resources` and follow the naming convention `X.txt` where X is like `01`, `02` and so on
* Solutions go into `src/main/kotlin/days` and extend the `Day` abstract class, calling its constructor with their day number 
* Solutions follow the naming convention `DayX`
* It is assumed all solutions will have two parts but share the same input
* Input is exposed in the solution classes in two forms - `inputList` and `inputString`
* Day 1 solution class and input file are stubbed as a guide on how to extend the project,
and how you can use the `inputList` and `inputString` mentioned above
* To get started simply replace `src/main/01.txt` with the real input and the solutions in `Day01` with your own
* A Day 1 test class also exists, mostly to show a few hamcrest matchers, and how test input files can differ from actual ones (see **Test input** section above).
To get started with testing you can edit this class, and the input file at `src/test/resources/01.txt`
