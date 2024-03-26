package entities;

import java.util.HashSet;

public class Movie {

	private String nome;
	private Diretor diretor;
	private HashSet<Ator> elenco;
	private float nota;

	public Movie(String nome, Diretor diretor, HashSet<Ator> elenco, float nota) {
		this.nome = nome;
		this.diretor = diretor;
		this.elenco = elenco;
		this.nota = nota;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Diretor getDiretor() {
		return diretor;
	}
	public void setDiretor(Diretor diretor) {
		this.diretor = new Diretor (diretor);
	}
	public HashSet<Ator> getElenco() {
		return new HashSet<Ator>(elenco) ;
	}
	public void setElenco(HashSet<Ator> elenco) {
		this.elenco = elenco;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	
}
