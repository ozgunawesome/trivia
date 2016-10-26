package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    private final Players players = new Players();

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

    public void add(Player player) {
        players.addPlayer(player);
    }

    public void roll(int roll) {
        System.out.println(players.getCurrentPlayer().getName() + Constants.IS_THE_CURRENT_PLAYER);
        System.out.println(Constants.THEY_HAVE_ROLLED_A + roll);

        if (players.getCurrentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                players.getCurrentPlayer().setGettingOutOfPenaltyBox();

                System.out.println(players.getCurrentPlayer().getName() + Constants.GETTING_OUT_OF_PENALTY_BOX);
                players.getCurrentPlayer().advanceByPlaces(roll);

                System.out.println(players.getCurrentPlayer().getName() + Constants.NEW_LOCATION + players.getCurrentPlayer().getLocation());
                System.out.println(Constants.THE_CATEGORY_IS + currentCategory());
                askQuestion();
            } else {
                System.out.println(players.getCurrentPlayer().getName() + Constants.NOT_GETTING_OUT_OF_PENALTY_BOX);
                players.getCurrentPlayer().setNotGettingOutOfPenaltyBox();
            }

        } else {
            players.getCurrentPlayer().advanceByPlaces(roll);

            System.out.println(players.getCurrentPlayer().getName() + Constants.NEW_LOCATION + players.getCurrentPlayer().getLocation());
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
        switch (players.getCurrentPlayer().getLocation() % 4) {
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
        if (players.getCurrentPlayer().isInPenaltyBox()) {
            if (players.getCurrentPlayer().isGettingOutOfPenaltyBox()) {
                System.out.println(Constants.ANSWER_WAS_CORRECT);
                players.getCurrentPlayer().givePurse();
                System.out.println(players.getCurrentPlayer().getName() + Constants.NOW_HAS + players.getCurrentPlayer().getPurses() + Constants.GOLD_COINS);

                boolean winner = didPlayerNotWin();
                players.goToNextPlayer();
                return winner;
            } else {
                players.goToNextPlayer();
                return true;
            }
        } else {
            System.out.println(Constants.ANSWER_WAS_CORRECT);
            players.getCurrentPlayer().givePurse();
            System.out.println(players.getCurrentPlayer().getName() + Constants.NOW_HAS + players.getCurrentPlayer().getPurses() + Constants.GOLD_COINS);

            boolean winner = didPlayerNotWin();
            players.goToNextPlayer();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println(Constants.QUESTION_INCORRECTLY_ANSWERED);
        System.out.println(players.getCurrentPlayer().getName() + Constants.WAS_SENT_TO_PENALTY_BOX);
        players.getCurrentPlayer().sendToPenaltyBox();

        players.goToNextPlayer();
        return true;
    }

    private boolean didPlayerNotWin() {
        return !(players.getCurrentPlayer().getPurses() == 6);
    }

}
