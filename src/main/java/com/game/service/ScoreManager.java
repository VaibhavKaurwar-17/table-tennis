package com.game.service;

import java.util.Map;

import com.game.model.Player;

public class ScoreManager {
	private Map<Player, Integer> totalScores;
	private Player winner;
	private Player server;
	private Player receiver;

	public ScoreManager(Map<Player, Integer> totalScores) {
		this.totalScores = totalScores;
	}

	public Integer getTotalScore(Player player) {
		return totalScores.getOrDefault(player, 0);
	}

	public synchronized void addScore(Player player, Integer scoreToAdd) {
		int currentScore = getTotalScore(player);
		totalScores.put(player, currentScore + scoreToAdd);
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getServer() {
		return server;
	}

	public void setServer(Player server) {
		this.server = server;
	}

	public Player getReceiver() {
		return receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

	public synchronized void swap() {
		Player temp = getServer();
		setServer(getReceiver());
		setReceiver(temp);
	}

}
