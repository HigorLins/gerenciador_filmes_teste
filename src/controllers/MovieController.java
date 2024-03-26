package controllers;

import java.util.List;

import dtos.MovieDto;
import services.MovieService;

public class MovieController {
	private MovieService mr;
	
	public MovieController() {
		this.mr = new MovieService();
	}
	
	public boolean addMovie(MovieDto MovieDto) {
		
		if(!MovieDto.validate())
			return false;
		
		return mr.addMovie(MovieDto);		
	}
	
	public List<MovieDto> listaFilmesPorNome(){
		return mr.listaFilmesPorNome();
	}
	
	public List<MovieDto> listaFilmesPorDiretor(String diretor){
		return mr.listaFilmesPorDiretor(diretor);
	}
	
	public List<MovieDto> listaFilmesPorAtor(String ator){
		return mr.listaFilmesPorAtor(ator);
	}
	
	public List<MovieDto> listaFilmesPorNota(float nota){
		return mr.listaFilmesPorNota(nota);
	}
	
	public List<String> listaAtores(){
		return mr.listaAtores();
	}
	
	public List<String> listaDiretores() {
		return mr.listaDiretores();
	}
	
	
}
