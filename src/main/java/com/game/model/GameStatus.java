package com.game.model;

public class GameStatus {
	private final Player winner;
	private final Decision decision;

	public GameStatus(Player winner, Decision decision) {
		this.winner = winner;
		this.decision = decision;
	}

	public Decision decision() {
		return decision;
	}

	public Player winner() {
		return winner;

	}

}
