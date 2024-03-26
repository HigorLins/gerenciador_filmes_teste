package dtos;

import java.util.HashSet;

import entities.Ator;
import entities.Movie;

public class MovieDto {

	private String nome;
	private String diretor;
	private String elenco [];
	private float nota;
	
	public MovieDto(String nome,String diretor,String elenco[], float nota) {
		this.nome= nome;
		this.diretor= diretor;
		this.nota= nota;
		
		this.elenco = new String[elenco.length];
		
		for(int i = 0; i < elenco.length; i++ ) {
			this.elenco[i]= elenco[i];
			
		}
	}
	
	public MovieDto(MovieDto movie) {
		this(movie.getNome(), movie.getDiretor(),movie.getElenco(),movie.getNota());
	}
	
	public MovieDto(Movie movie) {
		this.nome= movie.getNome();
		this.diretor= movie.getDiretor().getNome();
		this.nota= movie.getNota();
		
		HashSet<Ator> atores = movie.getElenco();
		
		String atoresNomes[] = new String[atores.size()];
		
		int i=0;
		
		for(Ator a: atores) {
			atoresNomes[i]= a.getNome();
			i++;
		}
		this.elenco=atoresNomes;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome= nome;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public String[] getElenco() {
		String elenco[] = new String[this.elenco.length];
		
		for(int i=0; i < this.elenco.length; i++)
			elenco[i]= this.elenco[i];
		
		return elenco;
	}
	
	public void setElenco(String elenco[]) {
		this.elenco= new String[elenco.length];
		
		for(int i=0;i < elenco.length; i++)
			this.elenco[i]= elenco[i];
	}
	
	public float getNota() {
		return nota;
		
	}
	public void setNota(float nota) {
		this.nota= nota;
	}
	
	public String toString() {
		String s = "";
		
		s +=" Nome: " + this.nome + "\n";
		s +=" Diretor: " + this.diretor + "\n";
		s +=" Elenco: \n";
		
		for(String e : this.elenco) {
			s+="     " + e + "\n";
		}
		
		s += " Nota: " + this.nota + "\n";
		
		return s;
 	}
	
	public boolean validate() {
		
		if(this.nome == "" || this.nome == null)
			return false;
		else if(this.diretor == "" || this.diretor == null)
			return false;
		else if(this.elenco== null || this.elenco.length == 0)
			return false;
		else if(this.nota < 0)
			return false;
		
		return true;
	}
}
