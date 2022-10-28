package com.qa.onlinepcgamestore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.onlinepcgamestore.entity.Game;
import com.qa.onlinepcgamestore.repository.GameRepository;
import com.qa.onlinepcgamestore.exception.GameAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

	@Mock
	private GameRepository gameRepository;

	@Autowired
	@InjectMocks
	private GameServiceImpl gameService;

	Game game1;
	Game game2;
	Game game3;

	List<Game> gameList;

	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances Create dummy data
		 */
		game1 = new Game(1, "game1", "genre1", 2001, 1, "dev1");
		game2 = new Game(2, "game2", "genre2", 2002, 2, "dev2");
		game3 = new Game(3, "game3", "genre3", 2003, 3, "dev3");
		gameList = Arrays.asList(game1, game2, game3);
	}

	@AfterEach
	public void tearDown() {
		game1 = game2 = game3 = null;
		gameRepository.deleteAll();
		gameList = null;
	}

	@Test
	@DisplayName("save-game-test")
	public void given_Game_To_Save_Should_Return_The_Saved_Game() throws GameAlreadyExistsException {
		when(gameRepository.findById(any())).thenReturn(Optional.empty());
		when(gameRepository.save(any())).thenReturn(game1);

		Game savedGame = gameService.addGame(game1);

		assertNotNull(savedGame);
		assertEquals(1, savedGame.getId());
	}

	@Test
	@DisplayName("save-game-throws-exception-test")
	public void given_Existing_Game_To_Save_Method_Should_Throw_Exception() throws GameAlreadyExistsException {
		when(gameRepository.findById(any())).thenReturn(Optional.of(game1));

		assertThrows(GameAlreadyExistsException.class, () -> gameService.addGame(game1));
	}
}
