package se.klosa.montyhall;

public class SimulationRunner {
    public static void main(String[] args) {
        MontyHallSimulation simulation;

        try {
            if (args.length == 0) {
                simulation = new MontyHallSimulation();
            } else if (args.length == 1) {
                simulation = new MontyHallSimulation(Integer.parseInt(args[0]));
            } else if (args.length == 2) {
                simulation = new MontyHallSimulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            } else {
                printUsage();
                return;
            }

            final MontyHallSimulation.Result result = simulation.run();
            System.out.println("Number of rounds: " + simulation.getNumberOfRounds());
            System.out.println("Number of boxes: " + simulation.getNumberOfBoxes());
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("One of the parameters is not a number\n");
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java -cp target/classes/ se.klosa.montyhall.SimulationRunner [rounds] [boxes]\n");
        System.out.println("The default value for rounds is 1000. For values less than 1 the value 1 is used.");
        System.out.println("The default value for boxes is 3. For values less than 3 the value 3 is used.\n");
    }
}
