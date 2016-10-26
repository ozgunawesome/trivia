package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * --- Created by ozgunayaz on 10/26/16 ---
 */
public class QuestionsTest {

    private final Questions questions = new Questions();

    @Test
    public void testQuestions() {
        assertEquals("Pop Question 0", questions.getNextForType(QuestionType.POP));
        assertEquals("Science Question 0", questions.getNextForType(QuestionType.SCIENCE));
        assertEquals("Sports Question 0", questions.getNextForType(QuestionType.SPORTS));
        assertEquals("Rock Question 0", questions.getNextForType(QuestionType.ROCK));

        assertEquals("Pop Question 1", questions.getNextForType(QuestionType.POP));
        assertEquals("Science Question 1", questions.getNextForType(QuestionType.SCIENCE));
        assertEquals("Sports Question 1", questions.getNextForType(QuestionType.SPORTS));
        assertEquals("Rock Question 1", questions.getNextForType(QuestionType.ROCK));
    }

    @Test
    public void testQuestionType() {
        assertEquals(QuestionType.POP.toString(), "Pop");
        assertEquals(QuestionType.SCIENCE.toString(), "Science");
        assertEquals(QuestionType.SPORTS.toString(), "Sports");
        assertEquals(QuestionType.ROCK.toString(), "Rock");
    }

    @Test(expected = NullPointerException.class)
    public void testNullQuestionType() {
        questions.getNextForType(null);
    }

}
