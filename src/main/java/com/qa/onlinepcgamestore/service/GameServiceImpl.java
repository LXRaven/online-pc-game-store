package com.qa.onlinepcgamestore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.onlinepcgamestore.dto.GameDto;
import com.qa.onlinepcgamestore.entity.Game;
import com.qa.onlinepcgamestore.exception.GameAlreadyExistsException;
import com.qa.onlinepcgamestore.exception.GameNotFoundException;
import com.qa.onlinepcgamestore.repository.GameRepository;

@SuppressWarnings("unused")
@Service
public class GameServiceImpl implements GameService{
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Game> getAllGames() {
		return this.gameRepository.findAll();
	}

	@Override
	public Game getGameById(int id) throws GameNotFoundException {
		Optional<Game> findByIdOptional = this.gameRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new GameNotFoundException();
		return findByIdOptional.get();
	}

	@Override
	public Game findByName(String name) throws GameNotFoundException {
		return this.gameRepository.findByName(name);
	}

	@Override
	public List<Game>findByReleaseYear(int releaseYear) throws GameNotFoundException {
		return this.gameRepository.findByReleaseYear(releaseYear);
	}

	@Override
	public List<Game> findByGenre(String genre) throws GameNotFoundException {
		return this.gameRepository.findByGenre(genre);
	}

	@Override
	public List<Game> findByDeveloper(String developer) throws GameNotFoundException {
		return this.gameRepository.findByDeveloper(developer);
	}

	@Override
	public List<Game> findByMinRating(double minRating) throws GameNotFoundException {
		return this.gameRepository.findByMinRating(minRating);
	}

	@Override
	public Game addGame(Game game) throws GameAlreadyExistsException {
		 Optional<Game> findByIdOptional = this.gameRepository.findById(game.getId());
		 if(findByIdOptional.isPresent())
			 throw new GameAlreadyExistsException();
		 else
			 return this.gameRepository.save(game);
	}

	@Override
	public Game updateGame(Game game) throws GameNotFoundException {
		 Optional<Game> findByIdOptional = this.gameRepository.findById(game.getId());
		 if(!findByIdOptional.isPresent())
			 throw new GameNotFoundException();
		 else
			 return this.gameRepository.save(game);
	}

	@Override
	public boolean deleteGame(int id) throws GameNotFoundException {
		boolean status = false;
		Optional<Game> findByIdOptional = this.gameRepository.findById(id);
		 if(!findByIdOptional.isPresent())
			 throw new GameNotFoundException();
		 
		 this.gameRepository.delete(findByIdOptional.get());
		 status = true;
		 
		 return status;
	}

	@Override
	public List<GameDto> getGameDetails() {
		return this.gameRepository.findAll().stream().map(this::mapToGameDto).collect(Collectors.toList());
	}
	private GameDto mapToGameDto(Game game) {
		return this.modelMapper.map(game, GameDto.class);
	}

}
