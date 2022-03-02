package com.game.tabletennis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.model.Decision;
import com.game.model.Game;
import com.game.model.GameState;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.rule.TableTennisRule;
import com.game.service.GameDecider;
import com.game.service.GameLogger;
import com.game.service.GameManager;
import com.game.service.TableTennisGameDecider;
import com.game.service.TableTennisLogger;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerTableTennisTest {

	@Test
	public void testPlayGame() {
		Player player1 = new Player("1");
		Player player2 = new Player("2");
		List<Player> players = Arrays.asList(player1, player2);
		Map<Player, Integer> map = new HashMap<>();
		map.put(player1, 0);
		map.put(player2, 0);
		GameManager gameManager = new GameManager(map, null, null, null, new ArrayList<>());
		GameDecider gameDecider = new TableTennisGameDecider(Collections.singletonList(new TableTennisRule()));
		GameLogger logger = new TableTennisLogger(System.out, new GameState(map, null, null));
		Game game = new TwoPlayerTableTennis(players, logger, gameManager, new Random(), gameDecider);

		GameStatus gameStatus = game.playGame();

		Assert.assertEquals(Decision.COMPLETED, gameStatus.decision());
	}

}
