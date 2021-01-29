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
        assertEquals(1000, simulation.getNumberOfRounds(), "Default number of rounds is used");
        assertEquals(3, simulation.getNumberOfBoxes(), "Default number of boxes is used");
        var result = simulation.run();
        assertNotNull(result);
        assertTrue(result.getSwitchWins() > 0);
        assertTrue(result.getStayWins() > 0);
        logger.info("Test with default values! {}", result);
    }

    @Test
    void testSimulationWithFiveBoxesHundredRounds() {
        MontyHallSimulation simulation = new MontyHallSimulation(100, 5);
        assertEquals(5, simulation.getNumberOfBoxes());
        assertEquals(100, simulation.getNumberOfRounds());
        logger.info("Test with 5 boxes and 100 rounds! {}", simulation.run());
    }

    @Test
    void testInvalidValues() {
        MontyHallSimulation simulation = new MontyHallSimulation(0, 2);
        assertEquals(3, simulation.getNumberOfBoxes());
        assertEquals(1, simulation.getNumberOfRounds());
    }
}
