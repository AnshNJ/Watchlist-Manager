package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    public void addMovie(Movie movie){
        movieRepo.addMovie(movie);
    }

    public void addDirector(Director director){
        movieRepo.addDirector(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        movieRepo.addMovieDirectorPair(movieName , directorName);
    }

    public Movie getMovieByName(String movieName){
        Movie m = movieRepo.getMovieByName(movieName);
        return m;
    }

    public Director getDirectorByName(String directorName){
        Director d = movieRepo.getDirectorByName(directorName);
        return d;
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieUnderDirector = movieRepo.getMoviesByDirectorName(directorName);
        return movieUnderDirector;
    }

    public List<String> findAllMovies(){
        List<String> allMovies = movieRepo.findAllMovies();
        return allMovies;
    }

    public void deleteDirectorByName(String directorName){
        movieRepo.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors(){
        movieRepo.deleteAllDirectors();
    }
}
