package services;

import java.util.HashSet;

import dtos.MovieDto;
import entities.Ator;
import entities.Diretor;
import entities.Movie;
import repositories.AtorRespository;
import repositories.DiretorRepository;
import repositories.MovieRespository;

public class MovieService {

	private MovieRespository mr;
	private AtorRespository ar;
	private DiretorRepository dr;
	
	public MovieService() {
		this.mr = new MovieRespository();
		this.ar = new AtorRespository() ;
		this.dr = new DiretorRepository();
	}
	
	public boolean addMovie(String nome, String nomeDiretor, String atores[],float nota) {
		
		Diretor diretor =new Diretor(nomeDiretor);
		this.dr.addDiretor(diretor);
		HashSet<Ator> elenco = new HashSet<Ator>();
		
		for(String nomeAtor : atores) {
			Ator ator = new Ator(nomeAtor);
			this.ar.addAtor(ator);
			elenco.add(ator);
			
		}
		
		Movie movie = new Movie (nome, diretor, elenco, nota);
		
		return this.mr.addMovie(movie);
	}
	
	public boolean addMovie(MovieDto movieDto) {
		return this.addMovie(movieDto.getNome(),movieDto.getDiretor(),movieDto.getElenco(),movieDto.getNota());
	}
	
	
	
}
