package repositories;

import java.util.HashSet;

import entities.Movie;

public class MovieRespository {
	
	private HashSet<Movie> movies;

	public MovieRespository() {
		this.movies = new HashSet<Movie>();
	}
	
	public boolean addMovie (Movie movie) {
		return this.movies.add(movie);
	}
	
	public HashSet<Movie> getMovie(){
		return new HashSet<Movie>(this.movies);
	}

}
