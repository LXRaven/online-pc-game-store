package com.qa.onlinepcgamestore.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.qa.onlinepcgamestore.entity.Game;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class GameRepositoryTest {

	@Autowired
    GameRepository gameRepository;

    Game game1;
    Game game2;
    Game game3;

    List<Game> gameList;

    @BeforeEach
    public void setUp() {
        /*
         * Create the necessary instances
         * Create dummy data
         */
        game1 = new Game(1,"game1", "genre1", 2001, 1, "dev1");
        game2 = new Game(2,"game2", "genre2", 2002, 2, "dev2");
        game3 = new Game(3,"game3", "genre3", 2003, 3, "dev3");
        gameList = Arrays.asList(game1,game2,game3);
    }

    @AfterEach
    public void tearDown() {
        game1 = game2 = game3 = null;
        gameRepository.deleteAll();
        gameList = null;
    }

    @Test
    @DisplayName("save-game-test")
    public void given_Game_To_Save_Should_Return_The_Saved_Game() {
        Game savedGame = gameRepository.save(game1);
        assertNotNull(savedGame);
        assertEquals("game1", savedGame.getName());
        assertEquals("dev1", savedGame.getDeveloper());
    }

    @Test
    @DisplayName("get-game-non-existing-id-test")
    public void given_Non_Existing_Id_Should_Return_Optional_Empty() {
        gameRepository.save(game1);
        assertThat(gameRepository.findById(4)).isEmpty();
    }
}
