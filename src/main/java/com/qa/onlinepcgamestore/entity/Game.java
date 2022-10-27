package com.qa.onlinepcgamestore.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id")
	private int id;
	
	@NotNull
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters only")
	@Pattern(regexp = "^[A-Za-z0-9]*", message = "Invalid name, must contain only alphanumeric")
	@Column(name = "game_name")
	private String name;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters only")
	@Pattern(regexp = "^[A-Za-z0-9]*", message = "Invalid genre, must contain only alphanumeric")
	@Column(name = "game_genre")
	private String genre;
	
	@NotNull
	@Min(value = 1990, message = "Year must be after 1990")
	@Max(value = 2022, message="Year must be earlier than current year")
	@Column(name = "game_release_year")
	private int releaseYear;
	
	@NotNull
	@Min(value = 1, message = "Rating must be more than 1")
	@Max(value = 10, message="Rating must be less than 10")
	@Column(name = "game_rating")
	private double rating;
	
	@NotNull
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters only")
	@Pattern(regexp = "^[A-Za-z0-9]*", message = "Invalid developer name, must contain only alphanumeric")
	@Column(name = "game_developer")
	private String developer;

	
}
