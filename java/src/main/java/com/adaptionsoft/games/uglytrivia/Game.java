package com.adaptionsoft.games.uglytrivia;

public class Game {

    private final Questions questions = new Questions();
    private final Players players = new Players();

    public Game() {
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
        System.out.println(questions.getNextForType(currentCategory()));
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
