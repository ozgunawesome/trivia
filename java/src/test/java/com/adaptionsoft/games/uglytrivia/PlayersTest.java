package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

/**
 * --- Created by ozgunayaz on 10/26/16 ---
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayersTest {

    private final Players players = new Players();

    @Mock
    private Player playerOne;

    @Mock
    private Player playerTwo;

    @Before
    public void setup() {
        players.addPlayer(playerOne);
        players.addPlayer(playerTwo);
    }

    @Test
    public void testNextPlayerCorrectlyCycles() {
        assertSame(players.getCurrentPlayer(), playerOne);
        players.goToNextPlayer();
        assertSame(players.getCurrentPlayer(), playerTwo);
        players.goToNextPlayer();
        assertSame(players.getCurrentPlayer(), playerOne);
    }

    @Test
    public void cantAddMoreThanMaxPlayers() {
        for (int i = 0; i < 10; i++) {
            players.addPlayer(mock(Player.class));
        }

        assertEquals(players.howManyPlayers(), 6);
    }
}
