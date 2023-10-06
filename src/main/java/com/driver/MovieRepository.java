package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    //Store data using HashMap
    HashMap<String , Movie> movieMap = new HashMap<>();
    HashMap<String , Director> directorMap = new HashMap<>();

    HashMap<String , List<Movie>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie) {
        movieMap.put(movie.getName() , movie);
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName() , director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        Movie currMovie = movieMap.get(movieName);
        if(!directorMovieMap.containsKey(directorName)){
            List<Movie> newList = new ArrayList<>();
            newList.add(currMovie);
            directorMovieMap.put(directorName , newList);
        } else {
            List<Movie> oldList = directorMovieMap.get(directorName);
            oldList.add(currMovie);
            directorMovieMap.put(directorName , oldList);
        }
    }

    public Movie getMovieByName(String movieName) {
        return movieMap.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return directorMap.get(directorName);
    }

    public List<Movie> getMoviesByDirectorName(String directorName) {
        return directorMovieMap.get(directorName);
    }

    public List<Movie> findAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        for(Movie currMovie : movieMap.values()){
            allMovies.add(currMovie);
        }
        return allMovies;
    }

    public void deleteDirectorByName(String directorName) {
        List<Movie> movies = directorMovieMap.get(directorName);
        for(Movie currMovie : movies){
            movieMap.remove(currMovie.getName());
        }
        directorMap.remove(directorName);
        directorMovieMap.remove(directorName);
    }

    public void deleteAllDirectors() {
        for(String directorName : directorMovieMap.keySet()){
            directorMap.remove(directorName);
            //remove all movies under directorName
            for(Movie m : directorMovieMap.get(directorName)){
                movieMap.remove(m.getName());
            }
            directorMovieMap.remove(directorName);
        }
    }
}
