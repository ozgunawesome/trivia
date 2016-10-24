package com.adaptionsoft.games.uglytrivia;

/**
 * Created by ozgunayaz on 10/24/16.
 */
public class Player {

    private final String name;
    private int purses;
    private int location;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }

    public int getLocation() {
        return location;
    }

    public void advanceByPlaces(int places) {
        location = (location + places) % 12;
    }

    public int getPurses() {
        return purses;
    }

    public void givePurse() {
        purses++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void removeFromPenaltyBox() {
        inPenaltyBox = false;
    }

}
