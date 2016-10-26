package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

/**
 * --- Created by ozgunayaz on 10/26/16 ---
 */
class Questions {

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    public Questions() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(Constants.POP_QUESTION + i);
            scienceQuestions.addLast(Constants.SCIENCE_QUESTION + i);
            sportsQuestions.addLast(Constants.SPORTS_QUESTION + i);
            rockQuestions.addLast(Constants.ROCK_QUESTION + i);
        }
    }

    public String getNextForType(QuestionType type) {
        switch (type) {
            case POP:
                return popQuestions.removeFirst();
            case SCIENCE:
                return scienceQuestions.removeFirst();
            case SPORTS:
                return sportsQuestions.removeFirst();
            case ROCK:
                return rockQuestions.removeFirst();
        }
        return null; // dead
    }

}
