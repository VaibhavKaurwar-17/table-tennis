package com.game.service;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.game.model.GameState;
import com.game.model.Player;

public class TableTennisLogger implements GameLogger, StateListener {

	private final PrintStream printStream;
	private GameState gameState;

	public TableTennisLogger(PrintStream printStream, GameState gameState) {
		this.printStream = printStream;
		this.gameState = gameState;
	}

	@Override
	public void log(String log) {
		printStream.println(log);
	}

	public void showScore() {
		StringBuilder builder = new StringBuilder();
		builder.append("-----------SCORE CARD----------\n");
		gameState.getTotalScores().keySet().stream().forEach(p -> builder.append(playerScore(p)));
		builder.append("-------------------------------\n");
		log(builder.toString());
	}

	public String playerScore(Player player) {
		return player.playerId() + asteriskForServer(player.playerId()) + ": " + gameState.getTotalScore(player) + "\n";
	}

	private String asteriskForServer(String playerId) {
		return playerId.equals(gameState.getServer().playerId()) ? "* " : "  ";
	}

	public void showWinner() {
		log("WINNER: " + gameState.getWinner().playerId());
	}

	@Override
	public void handle(GameState gameState) {
		this.gameState = gameState;

	}

}
