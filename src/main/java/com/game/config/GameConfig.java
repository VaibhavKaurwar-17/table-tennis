package com.game.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.game.model.Game;
import com.game.model.GameState;
import com.game.model.Player;
import com.game.rule.GameRule;
import com.game.rule.TableTennisRule;
import com.game.service.GameDecider;
import com.game.service.GameLogger;
import com.game.service.GameManager;
import com.game.service.TableTennisGameDecider;
import com.game.service.TableTennisLogger;
import com.game.tabletennis.TwoPlayerTableTennis;

@SpringBootApplication
public class GameConfig {

	@Bean
	public Player player1() {
		// Id can be accepted from user in future
		return new Player("Player 1");
	}

	@Bean
	public Player player2() {
		// Id can be accepted from user in future
		return new Player("Player 2");
	}

	@Bean
	public GameLogger logger(Map<Player, Integer> defaultScores) {
		// logs to output console for simplicity
		return new TableTennisLogger(System.out, new GameState(defaultScores, null, null));
	}

	@Bean
	public GameManager scoreManager(Map<Player, Integer> defaultScores) {
		return new GameManager(defaultScores, null, null, null, new ArrayList<>());
	}

	@Bean
	public Map<Player, Integer> defaultScores(@Qualifier("player1") Player player1,
			@Qualifier("player2") Player player2) {
		// Defaulting score to 0 as initialization
		Map<Player, Integer> defaultScores = new HashMap<>();
		defaultScores.put(player1, 0);
		defaultScores.put(player2, 0);
		return defaultScores;
	}

	@Bean
	public GameDecider gameDecider(GameRule rule) {
		// Game Decider ensures rules are applied at every step of game
		return new TableTennisGameDecider(Collections.singletonList(rule));
	}

	@Bean
	public TableTennisRule tableTennisRule(@Qualifier("player1") Player player1, @Qualifier("player2") Player player2,
			GameManager scoreManager) {
		// Winning rule
		return new TableTennisRule();
	}

	@Bean
	public Random randomPointGenerator() {
		return new Random();
	}

	@Bean
	public Game game(@Qualifier("player1") Player player1, @Qualifier("player2") Player player2, GameLogger logger,
			GameManager scoreManager, GameDecider gameDecider, Random randomPointGenerator) {
		return new TwoPlayerTableTennis(Arrays.asList(player1, player2), logger, scoreManager, randomPointGenerator,
				gameDecider);
	}

	// Runner can decoupled if needed
	// This is entry point
	@Bean
	@Profile("!test")
	public CommandLineRunner runner(Game game) {
		return (args) -> game.playGame();
	}

	// Trigger game
	public static void main(String[] args) {
		SpringApplication.run(GameConfig.class, args);
	}

}
