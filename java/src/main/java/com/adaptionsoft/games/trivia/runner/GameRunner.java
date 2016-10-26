
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;

import java.util.Random;


class GameRunner {

	public static void main(String[] args) {
		Game aGame = new Game();

		aGame.add(new Player("Chet"));
		aGame.add(new Player("Pat"));
		aGame.add(new Player("Sue"));
		
		Random rand = new Random();

		boolean notAWinner;

		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.rightAnswer();
			}
			
		} while (notAWinner);
		
	}
}
