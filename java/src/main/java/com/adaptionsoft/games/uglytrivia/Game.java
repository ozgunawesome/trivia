package com.adaptionsoft.games.uglytrivia;

public class Game {

    private Questions questions;
    private Players players;

    public Game() {

        if (questions == null) {
            questions = new Questions();
        }

        if (players == null) {
            players = new Players();
        }

    }

    public void add(Player player) {
        players.addPlayer(player);
    }

    public void roll(int roll) {
        System.out.println(players.getCurrentPlayer().getName() + Constants.IS_THE_CURRENT_PLAYER);
        System.out.println(Constants.THEY_HAVE_ROLLED_A + roll);

        if (players.getCurrentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                letOutOfPenaltyBox();
                advanceByPlacesAndAskQuestion(roll);
            } else {
                notLetOutOfPenaltyBox();
            }
        } else {
            advanceByPlacesAndAskQuestion(roll);
        }
    }

    public boolean rightAnswer() {
        if (players.getCurrentPlayer().isInPenaltyBox()) {
            if (players.getCurrentPlayer().isGettingOutOfPenaltyBox()) {
                return givePurseCheckIfNotWinAndGotoNextPlayer();
            } else {
                players.goToNextPlayer();
                return true;
            }
        } else {
            return givePurseCheckIfNotWinAndGotoNextPlayer();
        }
    }

    public boolean wrongAnswer() {
        System.out.println(Constants.QUESTION_INCORRECTLY_ANSWERED);
        System.out.println(players.getCurrentPlayer().getName() + Constants.WAS_SENT_TO_PENALTY_BOX);
        players.getCurrentPlayer().sendToPenaltyBox();

        players.goToNextPlayer();
        return true;
    }

    private void letOutOfPenaltyBox() {
        players.getCurrentPlayer().setGettingOutOfPenaltyBox();
        System.out.println(players.getCurrentPlayer().getName() + Constants.GETTING_OUT_OF_PENALTY_BOX);
    }

    private void notLetOutOfPenaltyBox() {
        System.out.println(players.getCurrentPlayer().getName() + Constants.NOT_GETTING_OUT_OF_PENALTY_BOX);
        players.getCurrentPlayer().setNotGettingOutOfPenaltyBox();
    }

    private void advanceByPlacesAndAskQuestion(int roll) {
        players.getCurrentPlayer().advanceByPlaces(roll);
        System.out.println(players.getCurrentPlayer().getName() + Constants.NEW_LOCATION + players.getCurrentPlayer().getLocation());
        System.out.println(Constants.THE_CATEGORY_IS + players.getCurrentPlayer().getQuestionTypeForLocation());
        System.out.println(questions.getNextForType(players.getCurrentPlayer().getQuestionTypeForLocation()));
    }

    private boolean givePurseCheckIfNotWinAndGotoNextPlayer() {
        System.out.println(Constants.ANSWER_WAS_CORRECT);
        players.getCurrentPlayer().givePurse();
        System.out.println(players.getCurrentPlayer().getName() + Constants.NOW_HAS + players.getCurrentPlayer().getPurses() + Constants.GOLD_COINS);

        boolean winner = didPlayerNotWin();
        players.goToNextPlayer();
        return winner;
    }

    private boolean didPlayerNotWin() {
        return !(players.getCurrentPlayer().getPurses() == 6);
    }

}
