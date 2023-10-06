package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Repository
@Service
public class MovieRepository {
    private HashMap<String,Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;
    public MovieRepository(){
        this.movieMap= new HashMap<String, Movie>();
        this.directorMap=  new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();

    }
    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }
    public void addDirector(Director director){
       directorMap .put(director.getName(),director);
    }
    public void addMovieDirectorPair(String movie, String director){
      if(movieMap.containsKey(movie)&& directorMap.containsKey(director)) {
          List<String> currentMovies = new ArrayList<String>();
          if (directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
          currentMovies.add(movie);
          directorMovieMapping.put(director, currentMovies);

      }
    }
    public Movie getMovieByName(String movie){
        return movieMap.get(movie);
    }
    public Director getDirectorByName(String director){
        return directorMap.get(director);
    }
    public List<String> getMoviesByDirectorName(String director){
        List<String> movieList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director))
            movieList= directorMovieMapping.get(director);
        return movieList;
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }
    public void deleteDirectorByName(String director){
        List<String> movies= new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)) {

            movies = directorMovieMapping.get(director);
            for (String movie : movies) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }
            directorMovieMapping.remove(director);
        }
        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<String>();
        for(String director : directorMovieMapping.keySet()) {
            for (String movie : directorMovieMapping.get(director)) {
                movieSet.add(movie);
            }
        }
        for (String movie: movieSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }
}