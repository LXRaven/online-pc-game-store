package com.qa.onlinepcgamestore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.onlinepcgamestore.entity.Game;

@SuppressWarnings("unused")
@Repository
@Transactional
public interface  GameRepository extends JpaRepository<Game, Integer>{

	@Query(value = "select * from game_details where game_name like CONCAT('%', :name, '%')", nativeQuery = true)
	public List<Game> findByName(String name);
	
	@Query(value = "select * from game_details where game_release_year like CONCAT('%', :releaseYear, '%')", nativeQuery = true)
	public List<Game> findByReleaseYear(int releaseYear);
	
	@Query(value = "select * from game_details where game_genre like CONCAT('%', :genre, '%')", nativeQuery = true)
	public List<Game> findByGenre(String genre);
	
	@Query(value = "select * from game_details where game_developer like CONCAT('%', :developer, '%')", nativeQuery = true)
	public List<Game> findByDeveloper(String developer);
	
	@Query(value = "select * from game_details where game_rating >= :minRating", nativeQuery = true)
	public List<Game> findByMinRating(double minRating);
}
