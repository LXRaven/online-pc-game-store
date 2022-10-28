package com.qa.onlinepcgamestore.service;

import java.util.List;

import com.qa.onlinepcgamestore.dto.GameDto;
import com.qa.onlinepcgamestore.entity.Game;
import com.qa.onlinepcgamestore.exception.GameAlreadyExistsException;
import com.qa.onlinepcgamestore.exception.GameNotFoundException;

public interface GameService {

	public List<Game> getAllGames();
	public Game getGameById(int id) throws GameNotFoundException;
	public Game findByName(String name) throws GameNotFoundException;
	public List<Game> findByReleaseYear(int releaseYear) throws GameNotFoundException;
	public List<Game> findByGenre(String genre) throws GameNotFoundException;
	public List<Game> findByDeveloper(String developer) throws GameNotFoundException;
	public List<Game> findByMinRating(double minRating) throws GameNotFoundException;
	public Game addGame(Game game) throws GameAlreadyExistsException;
	public Game updateGame(Game game) throws GameNotFoundException;
	public boolean deleteGame(int id) throws GameNotFoundException;
	public List<GameDto> getGameDetails();
	
}
