package com.game.tabletennis;

import java.util.List;
import java.util.Random;

import com.game.model.Decision;
import com.game.model.Game;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.service.GameDecider;
import com.game.service.GameLogger;
import com.game.service.ScoreManager;

public class TwoPlayerTableTennis implements Game {
	private final List<Player> players;
	private final ScoreManager scoreManager;
	private final GameLogger logger;
	private final GameDecider gameDecider;
	private final Random randomPointGenerator;

	public TwoPlayerTableTennis(List<Player> players, GameLogger logger, ScoreManager scoreManager,
			Random randomPointGenerator, GameDecider gameDecider) {
		this.players = players;
		this.scoreManager = scoreManager;
		this.logger = logger;
		this.randomPointGenerator = randomPointGenerator;
		this.gameDecider = gameDecider;
	}

	@Override
	public GameStatus playGame() {
		int serverRound = 0;
		scoreManager.setServer(players.get(0));
		scoreManager.setReceiver(players.get(1));
		while (Decision.PENDING.equals(gameDecider.makeDecision().decision())) {
			logger.showScore();
			serve();
			serverRound++;
			//Every player gets to serve twice in a row
			if (serverRound == 2) {
				scoreManager.swap();
				serverRound = 0;
				logger.log("Switching serve\n");
			}
		}
		logger.showScore();
		GameStatus gameStatus = gameDecider.makeDecision();
		scoreManager.setWinner(gameStatus.winner());
		logger.showWinner();
		return gameStatus;
	}

	private void serve() {
		if (randomPointGenerator.nextInt() % 2 == 0) {
			scoreManager.addScore(scoreManager.getServer(), 1);
			logger.log(scoreManager.getServer().playerId() + " scored 1 point\n");
		} else {
			scoreManager.addScore(scoreManager.getReceiver(), 1);
			logger.log(scoreManager.getReceiver().playerId() + " scored 1 point\n");
		}
	}

}
