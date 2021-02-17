package se.klosa.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class BoxSet {
    private final Random generator = new Random();
    private final Supplier<Box> chooseBox;
    private final Supplier<Box> chooseRemainingEmptyBox;
    private final Supplier<Box> chooseRemainingClosedBox;
    private final int numberOfBoxes;
    private final List<Box> boxList;

    public BoxSet(final int numberOfBoxes) {
        this.boxList = new ArrayList<>();
        this.numberOfBoxes = numberOfBoxes;

        chooseBox = () -> this.boxList.get(this.generator.nextInt(this.numberOfBoxes));

        chooseRemainingEmptyBox = () -> {
            Box box;
            do {
                box = chooseBox.get();
            } while (box.isContainsMoney() || box.isChosenByContestant());

            return box;
        };

        chooseRemainingClosedBox = () -> {
            Box box;
            do {
                box = chooseBox.get();
            } while (box.isChosenByContestant() || box.isOpen());

            return box;
        };

    }

    public void createNewSet() {
        boxList.clear();
        for (int i = 1; i <= getNumberOfBoxes(); i++) {
            boxList.add(new BoxImpl(i));
        }
        final Box box = chooseBox.get();
        box.putMoneyIntoBox();
    }

    public int getNumberOfBoxes() {
        return numberOfBoxes;
    }

    public int contestantChoosesBox() {
        final Box box = chooseBox.get();
        box.chooseBox();
        return box.getNumber();
    }

    public int showMasterOpensEmptyBox() {
        final Box box = chooseRemainingEmptyBox.get();
        box.openBox();
        return box.getNumber();
    }

    public boolean contestantChoosesToStay() {
        final Optional<Box> box = boxList.stream().filter(Box::isChosenByContestant).findFirst();
        return box.isPresent() && box.get().isContainsMoney();
    }

    public boolean contestantChoosesToSwitch() {
        final Box switchToBox = chooseRemainingClosedBox.get();
        return switchToBox.isContainsMoney();
    }
}
