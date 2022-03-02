package com.game.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.model.GameState;
import com.game.model.Player;

@RunWith(MockitoJUnitRunner.class)
public class TableTennisLoggerTest {

	@Mock
	Player player1;

	@Mock
	Player player2;

	@Mock
	PrintStream printStream;

	@Test
	public void testLog() {
		TableTennisLogger logger = sut();
		logger.log("any");
		verify(printStream, times(1)).println(eq("any"));
	}

	public TableTennisLogger sut() {
		Map<Player, Integer> map = new HashMap<>();
		map.put(player1, 11);
		map.put(player2, 7);
		when(player1.playerId()).thenReturn("player1");
		when(player2.playerId()).thenReturn("player2");
		GameState gameState = new GameState(map, player1, player1);
		return new TableTennisLogger(printStream, gameState);
	}

	@Test
	public void testShowWinner() {
		TableTennisLogger logger = sut();
		logger.showWinner();
		verify(printStream, times(1)).println(eq("WINNER: " + player1.playerId()));
	}

	@Test
	public void testShowScore() {
		TableTennisLogger logger = sut();
		logger.showScore();
		verify(printStream, times(1)).println(eq("-----------SCORE CARD----------\n" + "player1* : 11\n"
				+ "player2  : 7\n" + "-------------------------------\n"));
	}

}
