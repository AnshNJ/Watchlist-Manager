package com.driver;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService; //creating movieService object

    //ADD MOVIE
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New Movie added" , HttpStatus.CREATED);
    }

    //ADD DIRECTOR
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New Director added" , HttpStatus.CREATED);
    }

    //PAIR MOVIE AND DIRECTOR
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("key1") String movieName , @RequestParam("key2") String directorName){
        movieService.addMovieDirectorPair(movieName , directorName);
        return new ResponseEntity<>("Movie Director pair added successfully.", HttpStatus.CREATED);
    }

    //GET MOVIE
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie m = movieService.getMovieByName(name);
        return new ResponseEntity<>(m , HttpStatus.CREATED);
    }

    //GET DIRECTOR
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director d = movieService.getDirectorByName(name);
        return new ResponseEntity<>(d , HttpStatus.CREATED);
    }

    //GET LIST OF MOVIES UNDER GIVEN DIRECTOR
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> moviesUnderDirector = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>( moviesUnderDirector , HttpStatus.CREATED);
    }

    //GET ALL MOVIES

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies , HttpStatus.FOUND);
    }

    //DELETE DIRECTOR BY NAME
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirectorByName(director);
        return new ResponseEntity<>(director+ " deleted successfully" , HttpStatus.ACCEPTED);
    }

    //DELETE ALL DIRECTORS ALONG WITH THEIR RECORDS
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors deleted along with their records", HttpStatus.ACCEPTED);
    }


}
