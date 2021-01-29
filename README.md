# Monty Hall Problem Simulation
This little program runs a simulation of the Monty Hall Problem http://en.wikipedia.org/wiki/Monty_Hall_problem.

The number of rounds and number of boxes can be sent in as parameters. Default values are 1000 rounds and 3 boxes.

## Preconditions
Java 11

## Build 
    ./mvnw clean verify

## Run
    java -cp target/classes/ se.klosa.montyhall.SimulationRunner [rounds] [boxes]

The default value for rounds is 1000 and can not be smaller than 1.
The default value for boxes is 3 and can not be smaller than 3.

## Examples

### Example 1a
    java -cp target/classes/ se.klosa.montyhall.SimulationRunner 10000
    Number of rounds: 10000
    Number of boxes: 3
    Result = Wins when switching: 6634 (66.34%), Wins when staying: 3366 (33.66%)

### Example 1b
    java -cp target/classes/ se.klosa.montyhall.SimulationRunner 10000
    Number of rounds: 10000
    Number of boxes: 3
    Result = Wins when switching: 6607 (66.07%), Wins when staying: 3393 (33.93%)

### Example 2
    java -cp target/classes/ se.klosa.montyhall.SimulationRunner 2500 4
    Number of rounds: 2500
    Number of boxes: 4
    Result = Wins when switching: 943 (37.72%), Wins when staying: 646 (25.84%)

