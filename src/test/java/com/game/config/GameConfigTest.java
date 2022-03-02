package com.game.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.model.Decision;
import com.game.model.Game;
import com.game.model.GameStatus;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = GameConfig.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class GameConfigTest {

	@Autowired
	Game game;

	@Test
	public void test() {
		GameStatus status = game.playGame();
		Assert.assertEquals(status.decision(), Decision.COMPLETED);
	}

}
