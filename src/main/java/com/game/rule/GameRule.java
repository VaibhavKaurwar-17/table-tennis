package com.game.rule;

import com.game.model.GameState;
import com.game.model.GameStatus;

public interface GameRule {
	GameStatus applyRule(GameState gameState);
}
