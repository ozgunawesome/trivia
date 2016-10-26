package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * --- Created by ozgunayaz on 10/26/16 ---
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Questions questions;

    @Mock
    private Players players;

    @Mock
    private Player playerOne, playerTwo;

    @InjectMocks
    private Game game;

    @Before
    public void setup() {
        when(players.getCurrentPlayer()).thenReturn(playerOne);
        when(players.isCurrentInPenaltyBox()).thenCallRealMethod();
    }

    @Test
    public void testPlayerMovesWhenNotInPenaltyBox() {
        when(playerOne.isInPenaltyBox()).thenReturn(false);
        game.roll(3);
        verify(playerOne).advanceByPlaces(3);
    }

    @Test
    public void testPlayerMovementInPenaltyBox() {
        when(playerOne.isInPenaltyBox()).thenReturn(true);
        InOrder inOrder = inOrder(playerOne);
        game.roll(2); //no move when even
        inOrder.verify(playerOne).setNotGettingOutOfPenaltyBox();
        inOrder.verify(playerOne, never()).advanceByPlaces(2);
        game.roll(3); //move when odd
        inOrder.verify(playerOne).setGettingOutOfPenaltyBox();
        inOrder.verify(playerOne).advanceByPlaces(3);
    }

    @Test
    public void testWrongAnswer() {
        //wrong answer supposed to (a) put player in penalty box, (b) switch players
        game.wrongAnswer();
        InOrder inOrder = inOrder(playerOne, players);
        inOrder.verify(playerOne).sendToPenaltyBox();
        inOrder.verify(players).goToNextPlayer();
    }

    @Test
    public void testRightInPenaltyButNotOut() {
        when(playerOne.isInPenaltyBox()).thenReturn(true);
        when(playerOne.isGettingOutOfPenaltyBox()).thenReturn(false);
        assertTrue(game.rightAnswer());
        verify(playerOne, never()).givePurse();
        verify(players).goToNextPlayer();
    }

    @Test
    public void testRightAnswerInPenaltyBox() {
        when(playerOne.isInPenaltyBox()).thenReturn(true);
        when(playerOne.isGettingOutOfPenaltyBox()).thenReturn(true);
        assertTrue(game.rightAnswer());
        verify(playerOne).givePurse();
        verify(players).goToNextPlayer();
    }

    @Test
    public void testRightAnswerNotInPenaltyBox() {
        when(playerOne.isInPenaltyBox()).thenReturn(false);
        assertTrue(game.rightAnswer());
        verify(playerOne).givePurse();
        verify(players).goToNextPlayer();
    }

    @Test
    public void testCorrectPlayerSentToPenaltyBox() {
        Players myPlayers = spy(new Players());

        myPlayers.addPlayer(playerOne);
        myPlayers.addPlayer(playerTwo);

        assertSame(myPlayers.getCurrentPlayer(), playerOne);
        assertTrue(game.wrongAnswer());
        verify(playerOne).sendToPenaltyBox();
        verify(playerTwo, never()).sendToPenaltyBox();
    }

    @Test
    public void testCorrectPlayerWasGivenPurse() {
        Players myPlayers = spy(new Players());

        myPlayers.addPlayer(playerOne);
        myPlayers.addPlayer(playerTwo);

        assertSame(myPlayers.getCurrentPlayer(), playerOne);
        assertTrue(game.rightAnswer());
        verify(playerOne).givePurse();
        verify(playerTwo, never()).givePurse();
    }

    @Test
    public void testPlayerWinCondition() {
        Player myPlayer = new Player("x");
        when(players.getCurrentPlayer()).thenReturn(myPlayer);
        when(players.getPursesForCurrent()).thenCallRealMethod();
        myPlayer.givePurse();
        myPlayer.givePurse();
        myPlayer.givePurse();
        myPlayer.givePurse();
        assertTrue(game.rightAnswer());
        assertFalse(game.rightAnswer());
    }

}
