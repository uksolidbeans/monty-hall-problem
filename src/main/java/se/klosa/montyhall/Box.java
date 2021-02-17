package se.klosa.montyhall;

interface Box {
    int getNumber();

    boolean isContainsMoney();

    boolean isOpen();

    boolean isChosenByContestant();

    void chooseBox();

    void openBox();

    void putMoneyIntoBox();
}
