package com.qa.onlinepcgamestore.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "game_details")
public class Game {

	@Id
	@Column(name = "game_id")
	private int id;
	
	@Column(name = "game_name")
	private String name;
	
	@Column(name = "game_genre")
	private String genre;
	
	@Column(name = "game_release_year")
	private int releaseYear;
	
	@Column(name = "game_rating")
	private double rating;
	
	@Column(name = "game_developer")
	private String developer;

	
}
