package com.game.tabletennis;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.model.Decision;
import com.game.model.Game;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.rule.TableTennisRule;
import com.game.service.GameDecider;
import com.game.service.GameLogger;
import com.game.service.ScoreManager;
import com.game.service.TableTennisGameDecider;
import com.game.service.TableTennisLogger;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerTableTennisTest {

	@Test
	public void testPlayGame() {
		List<Player> players = Arrays.asList(new Player("1"), new Player("2"));
		ScoreManager scoreManager = new ScoreManager(new HashMap());
		GameDecider gameDecider = new TableTennisGameDecider(
				Collections.singletonList(new TableTennisRule(players, scoreManager)));
		GameLogger logger = new TableTennisLogger(players, scoreManager, System.out);
		Game game = new TwoPlayerTableTennis(players, logger, scoreManager, new Random(), gameDecider);

		GameStatus gameStatus = game.playGame();

		Assert.assertEquals(Decision.COMPLETED, gameStatus.decision());
	}

}
