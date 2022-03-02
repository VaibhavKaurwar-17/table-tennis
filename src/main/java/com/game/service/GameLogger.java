package com.game.service;

public interface GameLogger extends StateListener {

	void log(String log);

	void showScore();

	void showWinner();
}
