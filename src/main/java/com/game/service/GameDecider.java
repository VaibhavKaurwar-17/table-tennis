package com.game.service;

import com.game.model.GameStatus;

public interface GameDecider extends StateListener {

	GameStatus makeDecision();

}