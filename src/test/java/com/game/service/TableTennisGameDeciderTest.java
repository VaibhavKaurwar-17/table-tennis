package com.game.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.game.model.Decision;
import com.game.model.GameStatus;
import com.game.model.Player;
import com.game.rule.GameRule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TableTennisGameDeciderTest {

	@Mock
	GameRule rule;

	@Test
	@Parameters
	public void testMakeDecision(GameStatus expected) {
		MockitoAnnotations.openMocks(this);
		when(rule.applyRule(any())).thenReturn(expected);
		TableTennisGameDecider decider = new TableTennisGameDecider(Collections.singletonList(rule));
		GameStatus actual = decider.makeDecision();
		Assert.assertEquals(expected, actual);
	}

	public Object[][] parametersForTestMakeDecision() {
		return new Object[][] { { new GameStatus(new Player("1"), Decision.COMPLETED) },
				{ new GameStatus(new Player("1"), Decision.PENDING) } };
	}
}
