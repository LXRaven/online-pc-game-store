package com.qa.onlinepcgamestore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.onlinepcgamestore.entity.Game;
import com.qa.onlinepcgamestore.repository.GameRepository;
import com.qa.onlinepcgamestore.service.GameServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
	@Mock
	private GameServiceImpl gameService;

	@Autowired
	@InjectMocks
	private GameController gameController;
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
    GameRepository gameRepository;

    Game game1;
    Game game2;
    Game game3;

    List<Game> gameList;

	@BeforeEach
	public void setUp() {

		game1 = new Game(1, "game1", "genre1", 2001, 1, "dev1");
		game2 = new Game(2, "game2", "genre2", 2002, 2, "dev2");
		game3 = new Game(3, "game3", "genre3", 2003, 3, "dev3");
		gameList = Arrays.asList(game1, game2, game3);

		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
	}

	@AfterEach
	public void tearDown() {
		game1 = game2 = game3 = null;
		gameList = null;
	}

	@Test
	@DisplayName("save-game")
	public void given_game_To_Save_game_Should_Return_game_As_JSON_With_Status_Create() throws Exception {
		when(gameService.addGame(any())).thenReturn(game1);
		mockMvc.perform(post("/api/v1/games").contentType(MediaType.APPLICATION_JSON).content(asJsonString(game1)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("game1"));

	}

	@Test
	@DisplayName("get-game-developer")
	public void given_AllGames_Should_Return_List() throws Exception {
		when(gameService.getAllGames()).thenReturn(gameList);
		mockMvc.perform(get("/api/v1/games").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[1].developer").value("dev2"));
	}

	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;

		try {

			jsonStr = Obj.writeValueAsString(obj);
			System.out.println(jsonStr);
		} catch (IOException e) {
			System.out.println("SOME INTERNAL ERROR HAS OCCURED..");
			e.printStackTrace();
		}
		return jsonStr;
	}
}
