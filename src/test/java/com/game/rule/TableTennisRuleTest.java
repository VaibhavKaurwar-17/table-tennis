package com.game.rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.game.model.Decision;
import com.game.model.GameState;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.service.GameManager;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TableTennisRuleTest {

	List<Player> players;

	@Mock
	GameManager scoreManager;

	private static Player player1 = new Player("1");
	private static Player player2 = new Player("2");

	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
		players = Arrays.asList(player1, player2);
	}

	@Test
	@Parameters
	public void applyRule(Integer player1Score, Integer player2Score, Decision expected, String winner) {
		TableTennisRule rule = new TableTennisRule();
		Map<Player, Integer> map = new HashMap<>();
		map.put(player1, player1Score);
		map.put(player2, player2Score);
		GameStatus actualGameStatus = rule.applyRule(new GameState(map, player1, player1));

		Assert.assertEquals(expected, actualGameStatus.decision());
		Assert.assertEquals(winner, Optional.ofNullable(actualGameStatus.winner()).map(Player::playerId).orElse(null));

	}

	public Object[][] parametersForApplyRule() {
		return new Object[][] { { 13, 12, Decision.PENDING, null }, { 10, 10, Decision.PENDING, null },
				{ 10, 11, Decision.PENDING, null }, { 20, 20, Decision.PENDING, null },

				{ 21, 20, Decision.COMPLETED, "1" }, { 11, 9, Decision.COMPLETED, "1" },
				{ 13, 11, Decision.COMPLETED, "1" },

				{ 20, 21, Decision.COMPLETED, "2" }, { 9, 11, Decision.COMPLETED, "2" },
				{ 11, 13, Decision.COMPLETED, "2" } };
	}

}
