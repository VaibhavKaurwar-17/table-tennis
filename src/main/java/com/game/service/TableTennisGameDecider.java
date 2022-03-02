package com.game.service;

import java.util.List;

import com.game.model.Decision;
import com.game.model.GameStatus;
import com.game.rule.GameRule;

public class TableTennisGameDecider implements GameDecider {
	private final List<GameRule> winningRules;

	public TableTennisGameDecider(List<GameRule> winningRules) {
		this.winningRules = winningRules;
	}

	@Override
	public GameStatus makeDecision() {
		return winningRules.stream().map(GameRule::applyRule).findFirst()
				.orElse(new GameStatus(null, Decision.PENDING));
	}

}
