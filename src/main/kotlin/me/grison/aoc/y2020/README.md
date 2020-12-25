# Advent Of Code 2020 in Kotlin

![Advent Of Code 2020](https://github.com/agrison/advent-of-code-2020/workflows/Advent%20Of%20Code%202020/badge.svg)

## Goal

After the 5 last years with Java, Go (finished with Java), OCaml (finished with Java), Python and Clojure (+ some Java), this year I'll be solving the
[Advent of Code](https://adventofcode.com/2020) with **Kotlin** (hopefully this time I won't need Java).

1. A deep dive into Kotlin.
2. Write fast and idiomatic solutions.
3. Using extensions to make code shorter (see [Day.kt](https://github.com/agrison/advent-of-code-2020/blob/master/src/main/kotlin/days/Day.kt)).

## 2020 Output

```text
🎅 === Advent of Code 2020 === 🎅

🎄 --- Day 1: Report Repair --- 
 🌟 Part 1: 1003971       (65ms)
 🌟 Part 2: 84035952      (1.51s)

🎄 --- Day 2: Password Philosophy --- 
 🌟 Part 1: 645      (11.4ms)
 🌟 Part 2: 737      (2.47ms)

🎄 --- Day 3: Toboggan Trajectory --- 
 🌟 Part 1: 156             (687us)
 🌟 Part 2: 3521829480      (584us)

🎄 --- Day 4: Passport Processing --- 
 🌟 Part 1: 242      (14.4ms)
 🌟 Part 2: 186      (14.7ms)

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
 🌟 Part 1: 2414            (734us)
 🌟 Part 2: 21156911906816  (327us)

🎄 --- Day 11: Seating System ---
 🌟 Part 1: 2283  (381ms)
 🌟 Part 2: 2054  (976ms)

🎄 --- Day 12: Rain Risk ---
 🌟 Part 1: 1007   (48.5us)
 🌟 Part 2: 41212  (461us)

🎄 --- Day 13: Shuttle Search ---
 🌟 Part 1: 5257             (47.9us)
 🌟 Part 2: 538703333547789  (40.5ms)

🎄 --- Day 14: Docking Data ---
 🌟 Part 1: 13865835758282  (3.60ms)
 🌟 Part 2: 4195339838136   (57.4ms)

🎄 --- Day 15: Rambunctious Recitation ---
 🌟 Part 1: 319   (1.45ms)
 🌟 Part 2: 2424  (725ms)

🎄 --- Day 16: Ticket Translation ---
 🌟 Part 1: 19087          (8.19ms)
 🌟 Part 2: 1382443095281  (701ms)

🎄 --- Day 17: Conway Cubes ---
 🌟 Part 1: 362   (11.1ms)
 🌟 Part 2: 1980  (121ms)

🎄 --- Day 18: Operation Order ---
 🌟 Part 1: 7293529867931   (10.7ms)
 🌟 Part 2: 60807587180737  (5.21ms)

🎄 --- Day 19: Monster Messages ---
 🌟 Part 1: 279  (32.1ms)
 🌟 Part 2: 384  (204ms)
 
🎄 --- Day 20: Jurassic Jigsaw ---
 🌟 Part 1: 64802175715999  (11.7ms)
 🌟 Part 2: 2146            (94.6ms)
 
🎄 --- Day 21: Allergen Assessment ---
 🌟 Part 1: 2798                                       (2.36ms)
 🌟 Part 2: gbt,rpj,vdxb,dtb,bqmhk,vqzbq,zqjm,nhjrzzj  (1.77ms)

🎄 --- Day 22: Crab Combat ---
 🌟 Part 1: 32598  (3.67ms)
 🌟 Part 2: 35836  (292ms)

🎄 --- Day 23: Crab Cups ---
 🌟 Part 1: 53248976      (1.20ms)
 🌟 Part 2: 418819514477  (1.91s)
 
🎄 --- Day 24: Lobby Layout ---
 🌟 Part 1: 266   (20.7ms)
 🌟 Part 2: 3627  (141ms)

🎄 --- Day 25: Combo Breaker ---
 🌟 Part 1: 7936032                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      (90.7ms)
 🌟 Part 2: 
    |,\/,| |[_' |[_]) |[_]) \\//
    ||\/|| |[_, ||'\, ||'\,  ||

            ___ __ __ ____  __  __  ____  _  _    __    __
           // ' |[_]| |[_]) || ((_' '||' |,\/,|  //\\  ((_'
           \\_, |[']| ||'\, || ,_))  ||  ||\/|| //``\\ ,_))
                                                               

                                         ,;7,
                                       _ ||:|,
                     _,---,_           )\'  '|
                   .'_.-.,_ '.         ',')  j
                  /,'   ___}  \        _/   /
      .,         ,1  .''  =\ _.''.   ,`';_ |
    .'  \        (.'T ~, (' ) ',.'  /     ';',
    \   .\(\O/)_. \ (    _Z-'`>--, .'',      ;
     \  |   I  _|._>;--'`,-j-'    ;    ',  .'
    __\_|   _.'.-7 ) `'-' "       (      ;'
  .'.'_.'|.' .'   \ ',_           .'\   /
  | |  |.'  /      \   \          l  \ /
  | _.-'   /        '. ('._   _ ,.'   \i
,--' ---' / k  _.-,.-|__L, '-' ' ()    ;
 '._     (   ';   (    _-}             |
  / '     \   ;    ',.__;         ()   /
 /         |   ;    ; ___._._____.: :-j
|           \,__',-' ____: :_____.: :-\
|               F :   .  ' '        ,  L
',             J  |   ;             j  |
  \            |  |    L            |  J
   ;         .-F  |    ;           J    L
    \___,---' J'--:    j,---,___   |_   |
              |   |'--' L       '--| '-'|
               '.,L     |----.__   j.__.'
                | '----'   |,   '-'  }
                j         / ('-----';
               { "---'--;'  }       |
               |        |   '.----,.'
               ',.__.__.'    |=, _/
                |     /      |    '.
                |'= -x       L___   '--,
                L   __\          '-----'
                 '.____)  
```

### Running

Project is already setup with gradle. To run the app:

* Navigate to top-level directory on the command line
* Run `./gradlew run` to run all days for all years
* Run `./gradlew run --args $DAY` to run a specific day in the current year (*2020*, ex: `./gradlew run --args 8` to run the day 8 of *2020*)
* Run `./gradlew run --args $YEAR/*` to run all days in a specific year (ex: `./gradlew run --args 2015/*` to run the year *2015*)
* Run `./gradlew run --args $YEAR/$DAY` to run a specific year & day (ex: `./gradlew run --args 2015/7` to run the day *7* of *2015*)

### Testing

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
