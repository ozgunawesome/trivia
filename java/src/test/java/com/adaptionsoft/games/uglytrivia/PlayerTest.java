package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * --- Created by ozgunayaz on 10/25/16 ---
 */
public class PlayerTest {

    @Test
    public void testLocations() {
        Player player = new Player("test player");
        assertEquals(player.getLocation(), 0);
        player.advanceByPlaces(5);
        assertEquals(player.getLocation(), 5);
        player.advanceByPlaces(7);
        assertEquals(player.getLocation(), 0);
    }

    @Test
    public void testName() {
        Player player = new Player("test player");
        assertEquals(player.getName(), "test player");
    }

    @Test
    public void testPurses() {
        Player player = new Player("x");
        assertEquals(player.getPurses(), 0);
        player.givePurse();
        assertEquals(player.getPurses(), 1);
    }

    @Test
    public void testPenaltyBox() {
        Player player = new Player("x");
        assertFalse(player.isInPenaltyBox());
        player.sendToPenaltyBox();
        assertTrue(player.isInPenaltyBox());
        player.removeFromPenaltyBox();
        assertFalse(player.isInPenaltyBox());
    }

    @Test
    public void testGettingOutPenaltyBox() {
        Player player = new Player("x");
        assertFalse(player.isGettingOutOfPenaltyBox());
        player.setGettingOutOfPenaltyBox();
        assertTrue(player.isGettingOutOfPenaltyBox());
        player.setNotGettingOutOfPenaltyBox();
        assertFalse(player.isGettingOutOfPenaltyBox());
    }

    @Test
    public void testQuestionTypeForLocation() {
        Player player = new Player("x");
        assertEquals(0, player.getLocation());
        assertEquals(QuestionType.POP, player.getQuestionTypeForLocation());
        player.advanceByPlaces(1);
        assertEquals(QuestionType.SCIENCE, player.getQuestionTypeForLocation());
        player.advanceByPlaces(1);
        assertEquals(QuestionType.SPORTS, player.getQuestionTypeForLocation());
        player.advanceByPlaces(1);
        assertEquals(QuestionType.ROCK, player.getQuestionTypeForLocation());
        player.advanceByPlaces(1);
        assertEquals(QuestionType.POP, player.getQuestionTypeForLocation());
    }
}
