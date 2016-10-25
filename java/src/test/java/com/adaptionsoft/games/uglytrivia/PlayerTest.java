package com.adaptionsoft.games.uglytrivia;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ozgunayaz on 10/25/16.
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

}
