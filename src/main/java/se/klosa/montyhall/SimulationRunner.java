package se.klosa.montyhall;

public class SimulationRunner {
    public static void main(String[] args) {
        MontyHallSimulation simulation;

        if (args.length == 0) {
            simulation = new MontyHallSimulation();
        } else if (args.length == 1) {
            simulation = new MontyHallSimulation(Integer.parseInt(args[0]));
        } else if (args.length == 2) {
            simulation = new MontyHallSimulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } else {
            System.out.println("Usage:");
            System.out.println("java -cp target/classes/ se.klosa.montyhall.SimulationRunner [tries] [doors]");
            return;
        }
        final MontyHallSimulation.Result result = simulation.run();
        System.out.println("Number of tries: " + simulation.getNumberOfTries());
        System.out.println("Number of doors: " + simulation.getNumberOfDoors());
        System.out.println(result);
    }
}
