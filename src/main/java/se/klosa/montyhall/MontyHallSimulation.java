package se.klosa.montyhall;

import java.util.Random;

/**
 * Simulation of the Monty Hall Problem
 */
public class MontyHallSimulation {
    private static final int DEFAULT_NUMBER_OF_DOORS = 3;
    private static final int DEFAULT_NUMBER_OF_TRIES = 1000;

    private final int numberOfTries;
    private final int numberOfDoors;

    public MontyHallSimulation() {
        this(DEFAULT_NUMBER_OF_TRIES, DEFAULT_NUMBER_OF_DOORS);
    }

    public MontyHallSimulation(final int numberOfTries) {
        this(numberOfTries, DEFAULT_NUMBER_OF_DOORS);
    }

    public MontyHallSimulation(final int numberOfTries, final int numberOfDoors) {
        this.numberOfTries = numberOfTries;
        this.numberOfDoors = numberOfDoors;
    }

    public Result run() {
        final Result result = new Result(numberOfTries);
        final Random generator = new Random();

        for (int i = 0; i < this.numberOfTries; i++) {
            runOneRound(result, generator);
        }

        return result;
    }

    //
    // private helper methods
    //

    private void runOneRound(final Result result, final Random generator) {
        boolean[] doors = new boolean[numberOfDoors];
        final int winningDoor = pickDoor(generator);
        doors[winningDoor] = true;
        int chosenDoor = pickDoor(generator);
        int shownDoor = pickDoor(generator, winningDoor, chosenDoor);
        int switchDoor = pickDoor(generator, chosenDoor, shownDoor);

        if (doors[chosenDoor]) {
            result.incrementStayWins();
        } else if (doors[switchDoor]) {
            result.incrementSwitchWins();
        }
    }

    private int pickDoor(final Random generator) {
        return generator.nextInt(numberOfDoors);
    }

    private int pickDoor(final Random generator, final int excludeDoor1, final int excludeDoor2) {
        int door;
        do {
            door = pickDoor(generator);
        } while (excludeDoor1 == door || excludeDoor2 == door);

        return door;
    }

    //
    // Getters
    //

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Helper class holding the results of the simulation
     */
    public static class Result {
        private int switchWins;
        private int stayWins;
        private final int tries;

        public Result(final int tries) {
            this.tries = tries;
        }

        public void incrementSwitchWins() {
            switchWins++;
        }

        public void incrementStayWins() {
            stayWins++;
        }

        public int getSwitchWins() {
            return switchWins;
        }

        public int getStayWins() {
            return stayWins;
        }

        @Override
        public String toString() {
            return "Result = " +
                    "Wins when switching: " + this.switchWins + " (" + (this.switchWins * 100.0 / this.tries) + "%)" +
                    ", Wins when staying: " + this.stayWins + " (" + (this.stayWins * 100.0 / this.tries) + "%)";
        }
    }
}
