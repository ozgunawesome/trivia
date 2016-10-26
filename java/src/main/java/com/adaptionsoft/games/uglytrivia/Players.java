package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Created by ozgunayaz on 10/26/16.. ---
 */

class Players {

    private static final int MAX_PLAYERS = 6;

    private final List<Player> players = new ArrayList<>(6);
    private int currentPlayer;

    public void addPlayer(Player player) {
        if (players.size() == MAX_PLAYERS) {
            return;
        }

        players.add(player);

        System.out.println(player.getName() + Constants.WAS_ADDED);
        System.out.println(Constants.THEY_ARE_PLAYER_NUMBER + players.size());
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public int howManyPlayers() {
        return players.size();
    }

    void goToNextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public String getNameForCurrent() {
        return getCurrentPlayer().getName();
    }

    public int getPursesForCurrent() {
        return getCurrentPlayer().getPurses();
    }

}
