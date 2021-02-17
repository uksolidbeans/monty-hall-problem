package se.klosa.montyhall;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    final Logger logger = LoggerFactory.getLogger(SimulationTest.class);

    @ParameterizedTest
    @ValueSource(ints = {3, 5})
    void testBoxSet(int numberOfBoxes) {
        BoxSet boxSet = new BoxSet(numberOfBoxes);
        assertEquals(numberOfBoxes, boxSet.getNumberOfBoxes());
        boxSet.createNewSet();
        int chosenBox = boxSet.contestantChoosesBox();
        assertTrue(chosenBox > 0);
        int openedBox = boxSet.showMasterOpensEmptyBox();
        assertTrue(openedBox > 0);
        assertNotEquals(chosenBox, openedBox);
        boolean stayed = boxSet.contestantChoosesToStay();
        boolean switched = boxSet.contestantChoosesToSwitch();
        if (3 == numberOfBoxes) {
            assertNotEquals(stayed, switched);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 10})
    void testSimulationW(int numberOfBoxes) {
        MontyHallSimulation simulation = new MontyHallSimulation(1000, numberOfBoxes);
        assertEquals(1000, simulation.getNumberOfRounds());
        assertEquals(numberOfBoxes, simulation.getNumberOfBoxes());
        var result = simulation.run();
        assertNotNull(result);
        assertTrue(result.getSwitchWins() > 0);
        assertTrue(result.getStayWins() > 0);
        logger.info("Test with {} boxes and 1000 rounds! {}", numberOfBoxes, result);
    }

    @Test
    void testInvalidValues() {
        MontyHallSimulation simulation = new MontyHallSimulation(0, 2);
        assertEquals(3, simulation.getNumberOfBoxes());
        assertEquals(1, simulation.getNumberOfRounds());
    }
}
