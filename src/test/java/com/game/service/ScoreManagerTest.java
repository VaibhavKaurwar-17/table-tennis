package com.game.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.model.Player;

@RunWith(MockitoJUnitRunner.class)
public class ScoreManagerTest {

	@Test
	public void returnTotalScoreAs0() {
		GameManager scoreManager = new GameManager(Collections.emptyMap(), null, null, null, new ArrayList<>());
		Assert.assertEquals(0, scoreManager.getTotalScore(new Player("any")).intValue());
	}

	@Test
	public void returnTotalScoreAsStored() {
		Player player = new Player("any");
		GameManager scoreManager = new GameManager(Collections.singletonMap(player, 10), null, null, null,
				new ArrayList<>());
		Assert.assertEquals(10, scoreManager.getTotalScore(player).intValue());
	}

	@Test
	public void addScore() {
		Player player = new Player("any");
		Map<Player, Integer> map = new HashMap<>();
		map.put(player, 10);
		GameManager scoreManager = new GameManager(map, null, null, null, new ArrayList<>());
		scoreManager.addScore(player, 1);
		Assert.assertEquals(11, scoreManager.getTotalScore(player).intValue());
	}

	@Test
	public void testSwap() {
		Player player = new Player("1");
		Player player2 = new Player("2");
		GameManager scoreManager = new GameManager(Collections.singletonMap(player, 10), null, null, null,
				new ArrayList<>());
		scoreManager.setServer(player);
		scoreManager.setReceiver(player2);
		scoreManager.swap();
		Assert.assertEquals(player, scoreManager.getReceiver());
		Assert.assertEquals(player2, scoreManager.getServer());
	}

}
