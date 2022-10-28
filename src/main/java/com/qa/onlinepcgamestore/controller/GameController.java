package com.qa.onlinepcgamestore.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.onlinepcgamestore.entity.Game;
import com.qa.onlinepcgamestore.exception.GameAlreadyExistsException;
import com.qa.onlinepcgamestore.exception.GameNotFoundException;
import com.qa.onlinepcgamestore.service.GameServiceImpl;

@RestController
@RequestMapping("api/v1")
public class GameController {

	@Autowired
	GameServiceImpl gameService;
	
	ResponseEntity<?> responseEntity;
	
	@GetMapping("/games")
	public ResponseEntity<?> getAllGames(){
		try {
			List<Game> gameList = this.gameService.getAllGames();
			responseEntity = new ResponseEntity<>(gameList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/games/{id}")
	public ResponseEntity<?> getGameById(@PathVariable("id") int id) throws GameNotFoundException{
		try {
			Game game = this.gameService.getGameById(id);
			responseEntity = new ResponseEntity(game, HttpStatus.OK);
		} catch (GameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PostMapping("/games")
	public ResponseEntity<?> addGame(@Valid @RequestBody Game game) throws GameAlreadyExistsException{
		try {
			Game addedGame = this.gameService.addGame(game);
			System.out.println("added game" + addedGame);
			responseEntity = new ResponseEntity<>(game,HttpStatus.CREATED);
		} catch(GameAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@PutMapping("/games")
	public ResponseEntity<?> updateGame(@RequestBody Game game) throws GameNotFoundException{
		try {
			Game updatedGame = this.gameService.updateGame(game);			
			responseEntity = new ResponseEntity<>(updatedGame,HttpStatus.OK);
		} catch(GameNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@DeleteMapping("/games/{id}")
	public ResponseEntity<?> deleteGame(@PathVariable("id") @Min(value = 0,message = "id should be greater than 0")  int id) throws GameNotFoundException{
		try {
			boolean status = this.gameService.deleteGame(id);			
			if(status)
			responseEntity = new ResponseEntity<>("Game deleted Successfuly !!",HttpStatus.OK);
		} catch(GameNotFoundException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@GetMapping("/games/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name){
		try {
			Game gameByName = this.gameService.findByName(name);
			responseEntity = new ResponseEntity<>(gameByName,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/games/release-year/{releaseYear}")
	public ResponseEntity<?> findByReleaseYear(@PathVariable("releaseYear") int releaseYear){
		try {
			List<Game> gameListByReleaseYear = this.gameService.findByReleaseYear(releaseYear);
			responseEntity = new ResponseEntity<>(gameListByReleaseYear,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/games/genre/{genre}")
	public ResponseEntity<?> findByGenre(@PathVariable("genre") String genre){
		try {
			List<Game> gameListByGenre = this.gameService.findByGenre(genre);
			responseEntity = new ResponseEntity<>(gameListByGenre,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/games/developer/{developer}")
	public ResponseEntity<?> findByDeveloper(@PathVariable("developer") String developer){
		try {
			List<Game> gameListByDeveloper = this.gameService.findByDeveloper(developer);
			responseEntity = new ResponseEntity<>(gameListByDeveloper,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/games/min-rating/{minRating}")
	public ResponseEntity<?> findByMinRating(@PathVariable("minRating") double minRating){
		try {
			List<Game> gameListByMinRating = this.gameService.findByMinRating(minRating);
			responseEntity = new ResponseEntity<>(gameListByMinRating,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured..Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
