package se.klosa.montyhall;

import java.util.Random;

/**
 * Simulation of the Monty Hall Problem
 */
public class MontyHallSimulation {
    private static final int DEFAULT_NUMBER_OF_BOXES = 3;
    private static final int DEFAULT_NUMBER_OF_ROUNDS = 1000;

    private static final int MIN_NUMBER_OF_BOXES = 3;
    private static final int MIN_NUMBER_OF_ROUNDS = 1;

    private final int numberOfRounds;
    private final int numberOfBoxes;

    final Random generator = new Random();

    //
    // Constructors
    //
    public MontyHallSimulation() {
        this(DEFAULT_NUMBER_OF_ROUNDS, DEFAULT_NUMBER_OF_BOXES);
    }

    public MontyHallSimulation(final int numberOfRounds) {
        this(numberOfRounds, DEFAULT_NUMBER_OF_BOXES);
    }

    public MontyHallSimulation(final int numberOfRounds, final int numberOfBoxes) {
        this.numberOfRounds = Math.max(numberOfRounds, MIN_NUMBER_OF_ROUNDS);
        this.numberOfBoxes = Math.max(numberOfBoxes, MIN_NUMBER_OF_BOXES);
    }

    /**
     * Run the simulation and return the number of wins when staying and the number of wins when switching.
     *
     * @return Result which contains the number of wins for each alternative
     */
    public Result run() {
        final Result result = new Result(numberOfRounds);

        for (int i = 0; i < this.numberOfRounds; i++) {
            oneRound(result);
        }

        return result;
    }

    //
    // private helper methods
    //
    private void oneRound(final Result result) {
        final int winningBox = pickBox();
        int chosenBox = pickBox();
        int openedBox = pickBox(winningBox, chosenBox);
        int switchedBox = pickBox(chosenBox, openedBox);

        if (chosenBox == winningBox) {
            result.incrementStayWins();
        } else if (switchedBox == winningBox) {
            result.incrementSwitchWins();
        }
    }

    private int pickBox() {
        return this.generator.nextInt(numberOfBoxes);
    }

    private int pickBox(final int excludeBox1, final int excludeBox2) {
        int box;
        do {
            box = pickBox();
        } while (excludeBox1 == box || excludeBox2 == box);

        return box;
    }

    //
    // Getters
    //
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getNumberOfBoxes() {
        return numberOfBoxes;
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
