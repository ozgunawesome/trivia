package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final boolean[] inPenaltyBox = new boolean[6];

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    private Player currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    private String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void add(Player player) {
        players.add(player);

        System.out.println(player.getName() + " was added");
        System.out.println("They are player number " + players.size());

        if (currentPlayer == null) goToNextPlayer();
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                currentPlayer.advanceByPlaces(roll);

                System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getLocation());
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            currentPlayer.advanceByPlaces(roll);

            System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getLocation());
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (Objects.equals(currentCategory(), "Pop"))
            System.out.println(popQuestions.removeFirst());
        if (Objects.equals(currentCategory(), "Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (Objects.equals(currentCategory(), "Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (Objects.equals(currentCategory(), "Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        switch (currentPlayer.getLocation() % 4) {
            case 0:
                return "Pop";
            case 1:
                return "Science";
            case 2:
                return "Sports";
            default:
                return "Rock";
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.givePurse();
                System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurses() + " Gold Coins.");

                boolean winner = didPlayerWin();
                goToNextPlayer();
                return winner;
            } else {
                goToNextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            currentPlayer.givePurse();
            System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurses() + " Gold Coins.");

            boolean winner = didPlayerWin();
            goToNextPlayer();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.sendToPenaltyBox();

        goToNextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(currentPlayer.getPurses() == 6);
    }

    private void goToNextPlayer() {
        currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
    }
}
