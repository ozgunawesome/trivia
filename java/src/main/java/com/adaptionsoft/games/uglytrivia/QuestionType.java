package com.adaptionsoft.games.uglytrivia;

public enum QuestionType {

    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    /**
     * --- Created by ozgunayaz on 10/25/16 ---
     */
    private final String type;

    QuestionType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
