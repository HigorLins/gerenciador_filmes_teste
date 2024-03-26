package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
	
	public List<MovieDto> listaFilmesPorNome(){
		List<Movie> filmesFiltrados = new ArrayList<Movie>(
				this.mr.getMovie() );
		
		filmesFiltrados.sort((o1, o2) -> {
				return o1.getNome().compareTo(o2.getNome());
		});
		
		return converteListaFilmeParaDto(filmesFiltrados);
	}
	
	public List<MovieDto> listaFilmesPorDiretor(String diretor){
		
		ArrayList<Movie> filmesFiltrados = new ArrayList<Movie>();
		
		HashSet<Movie> filmes = this.fr.getFilmes();
		
		Iterator<Movie> i = filmes.iterator();
		
		while(i.hasNext()) {
			Movie f = i.next();
			
			if(f.getDiretor().getNome().equals(diretor)) {
				filmesFiltrados.add(new Movie(f));
			}
		}
		
		return converteListaFilmeParaDto(filmesFiltrados);
	}
	
	public List<MovieDto> listaFilmesPorAtor(String ator){
		
		ArrayList<Movie> filmesFiltrados = new ArrayList<Movie>();
		
		HashSet<Movie> filmes = this.mr.getMovie();
		
		Iterator<Movie> i = filmes.iterator();
		
		while(i.hasNext()) {
			Movie f = i.next();
			
			Iterator<Ator> j = f.getElenco().iterator();
			
			while(j.hasNext()) {				
				Ator a = j.next();
				
				if(a.getNome().equals(ator)) {
					filmesFiltrados.add(new Movie(f));
					break;
				}				
			}
		}
		
		return converteListaFilmeParaDto(filmesFiltrados);
	}
	
	public List<MovieDto> listaFilmesPorNota(float nota){
		ArrayList<Movie> filmesFiltrados = new ArrayList<Movie>();
		
		HashSet<Movie> filmes = this.mr.getMovie();
		
		for(Movie f : filmes) {
			if(f.getNota() >= nota)
				filmesFiltrados.add(f);
		}
		
		return converteListaFilmeParaDto(filmesFiltrados);
	}
	
	private List<MovieDto> converteListaFilmeParaDto(List<Movie> movies){
		List<MovieDto> filmesDto = new ArrayList<MovieDto>();
		
		for(Movie f : movies) {
			filmesDto.add(new MovieDto(f));
		}
		
		return filmesDto;		
	}
	
	public boolean addAtor(String nome) {
		return this.ar.addAtor(new Ator(nome));
	}
	
	
	public Ator getAtor(String nome) {
		return ar.getAtor(nome);
	}
	
	public ArrayList<String> listaAtores(){
		HashSet<Ator> atoresArray = this.ar.getAtores();	
		
		ArrayList<String> atores = new ArrayList<>();
		
		for(Ator a : atoresArray)
			atores.add(a.getNome());
		
		return atores;
	}
	
	public boolean addDiretor(String nome) {
		return this.dr.addDiretor(new Diretor(nome));
	}
	
	
	public ArrayList<String> listaDiretores(){
		
		HashSet<Diretor> diretoresArray = this.dr.getDiretores();
		
		ArrayList<String> diretores = new ArrayList<String>();
		
		for(Diretor d : diretoresArray)
			diretores.add(d.getNome());
		
		return diretores;
	}
	
	
	
}
