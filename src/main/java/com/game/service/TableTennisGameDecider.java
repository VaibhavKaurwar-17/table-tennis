package com.game.service;

import java.util.List;

import com.game.model.Decision;
import com.game.model.GameState;
import com.game.model.GameStatus;
import com.game.rule.GameRule;

public class TableTennisGameDecider implements GameDecider, StateListener {
	private final List<GameRule> winningRules;
	private GameState gameState;

	public TableTennisGameDecider(List<GameRule> winningRules) {
		this.winningRules = winningRules;
	}

	@Override
	public GameStatus makeDecision() {
		return winningRules.stream().map(rule->rule.applyRule(gameState)).findFirst()
				.orElse(new GameStatus(null, Decision.PENDING));
	}

	@Override
	public void handle(GameState gameState) {
		this.gameState = gameState;
	}

}
