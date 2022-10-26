package com.qa.onlinepcgamestore.dto;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto {

	private int id;
	private String name;
	private String genre;
	private int releaseYear;
	private float rating;
	private String developer;
}
