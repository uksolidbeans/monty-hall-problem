package se.klosa.montyhall;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    final Logger logger = LoggerFactory.getLogger(SimulationTest.class);

    @Test
    void testSimulationWithDefaultValues() {
        MontyHallSimulation simulation = new MontyHallSimulation();
        assertEquals(1000, simulation.getNumberOfTries(), "Default number of tries is used");
        assertEquals(3, simulation.getNumberOfDoors(), "Default number of doors is used");
        var result = simulation.run();
        assertNotNull(result);
        assertTrue(result.getSwitchWins() > 0);
        assertTrue(result.getStayWins() > 0);
        logger.info("Test with default values! {}", result);
    }

    @Test
    void testSimulationWithFiveDoorsHundredTries() {
        MontyHallSimulation simulation = new MontyHallSimulation(100, 5);
        assertEquals(5, simulation.getNumberOfDoors());
        assertEquals(100, simulation.getNumberOfTries());
        final var result = simulation.run();
        assertNotNull(result);
        logger.info("Test with 5 doors and 100 tries! {}", result);
    }
}
