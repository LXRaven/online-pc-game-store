# Online PC Game Store
### Description
This objective of this assignment is to create a web page which displays games with movie name, release year, genre, developer and the rating. The user can search for games by either one of the criteria and multiple games can be returned accordingly. The user can also add games to the database, update details and delete entries.
### Tech Stack
	JDK 11
	SpringBoot 2.7.5
	MySQL 8.0
	Maven 3.8.6
### Steps To Run Locally
	run "Online PC Game Store".jar through cmd
### Endpoints
	http://localhost:8083/api/v1/games
	http://localhost:8083/api/v1/games/{id}
	http://localhost:8083/api/v1/games/name/{name}
	http://localhost:8083/api/v1/games/release-year/{releaseYear}
	http://localhost:8083/api/v1/games/genre/{genre}
	http://localhost:8083/api/v1/games/developer/{developer}
	http://localhost:8083/api/v1/games/min-rating/{minRating}
### Sample Screens
	.\Swagger API Documentation Screenshots\main.png
	.\Swagger API Documentation Screenshots\post.png
	.\Swagger API Documentation Screenshots\put.png
	.\Swagger API Documentation Screenshots\delete.png
	.\Swagger API Documentation Screenshots\getAll.png
	.\Swagger API Documentation Screenshots\getByMinRating.png