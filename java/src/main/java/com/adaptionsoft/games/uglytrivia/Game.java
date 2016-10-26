package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    private Player currentPlayer;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(Constants.POP_QUESTION + i);
            scienceQuestions.addLast((Constants.SCIENCE_QUESTION + i));
            sportsQuestions.addLast((Constants.SPORTS_QUESTION + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    private String createRockQuestion(int index) {
        return Constants.ROCK_QUESTION + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void add(Player player) {
        if (players.size() == 6) {
            return;
        }

        players.add(player);

        System.out.println(player.getName() + Constants.WAS_ADDED);
        System.out.println(Constants.THEY_ARE_PLAYER_NUMBER + players.size());

        if (currentPlayer == null) goToNextPlayer();
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + Constants.IS_THE_CURRENT_PLAYER);
        System.out.println(Constants.THEY_HAVE_ROLLED_A + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + Constants.GETTING_OUT_OF_PENALTY_BOX);
                currentPlayer.advanceByPlaces(roll);

                System.out.println(currentPlayer.getName() + Constants.NEW_LOCATION + currentPlayer.getLocation());
                System.out.println(Constants.THE_CATEGORY_IS + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer.getName() + Constants.NOT_GETTING_OUT_OF_PENALTY_BOX);
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            currentPlayer.advanceByPlaces(roll);

            System.out.println(currentPlayer.getName() + Constants.NEW_LOCATION + currentPlayer.getLocation());
            System.out.println(Constants.THE_CATEGORY_IS + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        switch (currentCategory()) {
            case POP:
                System.out.println(popQuestions.removeFirst());
                break;
            case SCIENCE:
                System.out.println(scienceQuestions.removeFirst());
                break;
            case SPORTS:
                System.out.println(sportsQuestions.removeFirst());
                break;
            case ROCK:
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }

    private QuestionType currentCategory() {
        switch (currentPlayer.getLocation() % 4) {
            case 0:
                return QuestionType.POP;
            case 1:
                return QuestionType.SCIENCE;
            case 2:
                return QuestionType.SPORTS;
            default:
                return QuestionType.ROCK;
        }
    }

    public boolean rightAnswer() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println(Constants.ANSWER_WAS_CORRECT);
                currentPlayer.givePurse();
                System.out.println(currentPlayer.getName() + Constants.NOW_HAS + currentPlayer.getPurses() + Constants.GOLD_COINS);

                boolean winner = didPlayerWin();
                goToNextPlayer();
                return winner;
            } else {
                goToNextPlayer();
                return true;
            }
        } else {
            System.out.println(Constants.ANSWER_WAS_CORRECT);
            currentPlayer.givePurse();
            System.out.println(currentPlayer.getName() + Constants.NOW_HAS + currentPlayer.getPurses() + Constants.GOLD_COINS);

            boolean winner = didPlayerWin();
            goToNextPlayer();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println(Constants.QUESTION_INCORRECTLY_ANSWERED);
        System.out.println(currentPlayer.getName() + Constants.WAS_SENT_TO_PENALTY_BOX);
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
