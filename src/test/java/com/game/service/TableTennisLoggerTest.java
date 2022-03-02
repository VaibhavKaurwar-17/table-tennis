package com.game.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.model.Player;

@RunWith(MockitoJUnitRunner.class)
public class TableTennisLoggerTest {

	@Mock
	Player player1;

	@Mock
	Player player2;

	@Mock
	ScoreManager scoreManager;

	@Mock
	PrintStream printStream;

	@Test
	public void testLog() {
		TableTennisLogger logger = sut();
		logger.log("any");
		verify(printStream, times(1)).println(eq("any"));
	}

	public TableTennisLogger sut() {
		return new TableTennisLogger(Arrays.asList(player1, player2), scoreManager, printStream);
	}

	@Test
	public void testShowWinner() {
		when(player1.playerId()).thenReturn("player1");
		when(scoreManager.getWinner()).thenReturn(player1);
		TableTennisLogger logger = sut();
		logger.showWinner();
		verify(printStream, times(1)).println(eq("WINNER: " + player1.playerId()));
	}

	@Test
	public void testShowScore() {
		when(player1.playerId()).thenReturn("player1");
		when(player2.playerId()).thenReturn("player2");
		when(scoreManager.getServer()).thenReturn(player1);
		when(scoreManager.getTotalScore(player1)).thenReturn(11);
		when(scoreManager.getTotalScore(player2)).thenReturn(7);
		TableTennisLogger logger = sut();
		logger.showScore();
		verify(printStream, times(1)).println(eq("-----------SCORE CARD----------\n" + "player1* : 11\n"
				+ "player2  : 7\n" + "-------------------------------\n"));
	}

}
