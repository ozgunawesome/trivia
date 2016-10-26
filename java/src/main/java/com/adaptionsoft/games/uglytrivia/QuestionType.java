package com.adaptionsoft.games.uglytrivia;

/**
 * Created by ozgunayaz on 10/25/16.
 */
public enum QuestionType {

    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String type;

    private QuestionType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
