package com.game.rule;

import com.game.model.Decision;
import com.game.model.GameState;
import com.game.model.GameStatus;
import com.game.model.Player;

public class TableTennisRule implements GameRule {

	@Override
	public GameStatus applyRule(GameState state) {

		Player player1 = state.getTotalScores().keySet().toArray(new Player[state.getTotalScores().size()])[0];
		Player player2 = state.getTotalScores().keySet().toArray(new Player[state.getTotalScores().size()])[1];

		Integer player1Score = state.getTotalScore(player1);
		Integer player2Score = state.getTotalScore(player2);

		// The first to 11 points is declared the winner.
		if (player1Score >= 11 && player2Score < 10) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score >= 11 && player1Score < 10) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		if (player2Score == 10 && player1Score == 10) {
			return new GameStatus(null, Decision.PENDING);
		}

		// If the points are tied at 10-10, a player then has to strive for a two-point
		// lead to win the game.

		if (player2Score - player1Score >= 2 && player2Score > 11 && player2Score <= 20) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		if (player1Score - player2Score >= 2 && player1Score > 11 && player1Score <= 20) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score == 20 && player1Score == 20) {
			return new GameStatus(null, Decision.PENDING);
		}

		// If the scores are tied at 20-20, the first player to reach 21 point wins the
		// game
		if (player1Score >= 21) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score >= 21) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		// Non winning situations
		return new GameStatus(null, Decision.PENDING);
	}

}
