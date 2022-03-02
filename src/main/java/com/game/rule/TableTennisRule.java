package com.game.rule;

import java.util.List;

import com.game.model.Decision;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.service.ScoreManager;

public class TableTennisRule implements GameRule {

	private final List<Player> players;
	private final ScoreManager scoreManager;

	public TableTennisRule(List<Player> players, ScoreManager scoreManager) {
		this.players = players;
		this.scoreManager = scoreManager;
	}

	@Override
	public GameStatus applyRule() {
		Player player1 = players.get(0);
		Player player2 = players.get(1);

		Integer player1Score = scoreManager.getTotalScore(player1);
		Integer player2Score = scoreManager.getTotalScore(player2);
		
		//The first to 11 points is declared the winner.
		if (player1Score >= 11 && player2Score < 10) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score >= 11 && player1Score < 10) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		if (player2Score == 10 && player1Score == 10) {
			return new GameStatus(null, Decision.PENDING);
		}


		//If the points are tied at 10-10, a player then has to strive for a two-point lead to win the game.

		if (player2Score - player1Score >= 2 && player2Score > 11 && player2Score <= 20) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		if (player1Score - player2Score >= 2 && player1Score > 11 && player1Score <= 20) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score == 20 && player1Score == 20) {
			return new GameStatus(null, Decision.PENDING);
		}

		//If the scores are tied at 20-20, the first player to reach 21 point wins the game
		if (player1Score >= 21) {
			return new GameStatus(player1, Decision.COMPLETED);
		}

		if (player2Score >= 21) {
			return new GameStatus(player2, Decision.COMPLETED);
		}

		//Non winning situations
		return new GameStatus(null, Decision.PENDING);
	}

}
