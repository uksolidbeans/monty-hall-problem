package se.klosa.montyhall;

public class BoxImpl implements Box {
    private final int number;
    private boolean containsMoney;
    private boolean open;
    private boolean chosen;

    public BoxImpl(final int number) {
        this.number = number;
        this.containsMoney = false;
        this.open = false;
        this.chosen = false;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean isContainsMoney() {
        return this.containsMoney;
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }

    @Override
    public boolean isChosenByContestant() {
        return this.chosen;
    }

    @Override
    public void chooseBox() {
        this.chosen = true;
    }

    @Override
    public void openBox() {
        this.open = true;
    }

    @Override
    public void putMoneyIntoBox() {
        this.containsMoney = true;
    }
}
