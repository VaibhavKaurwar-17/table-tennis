package com.game.service;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import com.game.model.Player;

public class TableTennisLogger implements GameLogger {

	private final PrintStream printStream;
	private final List<Player> players;
	private final ScoreManager scoreManager;

	public TableTennisLogger(List<Player> players, ScoreManager scoreManager, PrintStream printStream) {
		this.players = players;
		this.scoreManager = scoreManager;
		this.printStream = printStream;
	}

	@Override
	public void log(String log) {
		printStream.println(log);
	}

	public void showScore() {
		StringBuilder builder = new StringBuilder();
		builder.append("-----------SCORE CARD----------\n");
		players.stream().forEach(p -> builder.append(playerScore(p)));
		builder.append("-------------------------------\n");
		log(builder.toString());
	}

	public String playerScore(Player player) {
		return player.playerId() + asteriskForServer(player.playerId()) + ": " + scoreManager.getTotalScore(player)
				+ "\n";
	}

	private String asteriskForServer(String playerId) {
		return playerId.equals(scoreManager.getServer().playerId()) ? "* " : "  ";
	}

	public void showWinner() {
		log("WINNER: " + scoreManager.getWinner().playerId());
	}

}
