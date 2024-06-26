package ui;

import java.util.ArrayList;
import java.util.List;

import controllers.MovieController;
import dtos.MovieDto;
import ultils.Reader;

public class Ui {
	private enum MENU {
		CADASTRAR_FILME(1), LISTAR_FILMES(2), PROCURAR_POR_DIRETOR(3), PROCURAR_POR_ATOR(4), PROCURAR_POR_NOTA(5),
		LISTAR_DIRETORES(6), LISTAR_ATORES(7), SAIR(8);

		private final int valor;

		MENU(int valor) {
			this.valor = valor;
		}

		public MENU doInt(int valor) {
			if (valor == CADASTRAR_FILME.valor)
				return CADASTRAR_FILME;
			else if (valor == LISTAR_FILMES.valor)
				return LISTAR_FILMES;
			else if (valor == PROCURAR_POR_DIRETOR.valor)
				return PROCURAR_POR_DIRETOR;
			else if (valor == PROCURAR_POR_ATOR.valor)
				return PROCURAR_POR_ATOR;
			else if (valor == PROCURAR_POR_NOTA.valor)
				return PROCURAR_POR_NOTA;
			else if (valor == LISTAR_DIRETORES.valor)
				return LISTAR_DIRETORES;
			else if (valor == LISTAR_ATORES.valor)
				return LISTAR_ATORES;
			else
				return SAIR;
		}

		public int getValor() {
			return this.valor;
		}
	};

	private MovieController mc;

	public Ui() {
		this.mc = new MovieController();
	}

	public void run() {
		divisoria();
		bemVindo();

		MENU escolhaMenu = null;

		do {
			int opcao = menu();

			if (opcao > MENU.values().length) {
				escolhaMenu = null;
				System.out.println("  ** Voce precisa informar uma opcao valida.");
			} else
				escolhaMenu = MENU.values()[opcao - 1];

			switch (escolhaMenu) {
			case CADASTRAR_FILME:
				cadastrarFilme();
				break;

			case LISTAR_FILMES:
				listarFilmes();
				break;

			case PROCURAR_POR_DIRETOR:
				procurarFilmePorDiretor();
				break;

			case PROCURAR_POR_ATOR:
				procurarFilmePorAtor();
				break;

			case PROCURAR_POR_NOTA:
				procurarFilmePorNota();
				break;

			case LISTAR_DIRETORES:
				listarDiretores();
				break;

			case LISTAR_ATORES:
				listarAtores();
				break;

			case SAIR:
				sair();
				break;
			}

		} while (escolhaMenu != MENU.SAIR && escolhaMenu != null);

	}

	public void divisoria() {
		System.out.println("=========================");
	}

	public void titulo(String titulo) {
		divisoria();
		System.out.println(titulo);
		divisoria();
	}

	public void bemVindo() {
		System.out.println("Bem vindo ao aplicativo de cadastro de filmes.");
	}

	public void sair() {
		System.out.println("Ate mais!");
	}

	public int menu() {
		divisoria();
		System.out.println("Escolha uma opcao do menu para continuar: ");
		divisoria();

		System.out.print(MENU.CADASTRAR_FILME.getValor());
		System.out.println(" - Cadastrar um filme.");
		System.out.print(MENU.LISTAR_FILMES.getValor());
		System.out.println(" - Listar filmes cadastrados.");
		System.out.print(MENU.PROCURAR_POR_DIRETOR.getValor());
		System.out.println(" - Listar filmes de um diretor.");
		System.out.print(MENU.PROCURAR_POR_ATOR.getValor());
		System.out.println(" - Listar filmes de um ator.");
		System.out.print(MENU.PROCURAR_POR_NOTA.getValor());
		System.out.println(" - Listar filmes por nota.");
		System.out.print(MENU.LISTAR_DIRETORES.getValor());
		System.out.println(" - Listar diretores.");
		System.out.print(MENU.LISTAR_ATORES.getValor());
		System.out.println(" - Listar atores/atrizes.");
		System.out.print(MENU.SAIR.getValor());
		System.out.print(" - Encerrar.");

		System.out.println();

		int opcao = Reader.LeInt("Qual a opcao desejada? ");

		return opcao;
	}

	public boolean cadastrarFilme() {

		titulo("CADASTRO DE FILME");

		String nome = Reader.leStr("  Informe o nome do filme: ");
		String diretor = Reader.leStr("  Informe o nome do diretor do filme: ");

		ArrayList<String> elenco = new ArrayList<String>();

		boolean maisAtores = true;

	
		do {
			String ator = Reader.leStr("  Informe um(a) ator/atriz do filme ou -1 para parar de informar: ");

			if (!ator.equals("-1"))
				elenco.add(ator);
			else {
				if (elenco.size() > 0)
					maisAtores = false;
				else
					System.out.println("  ** Pelo menos um(a) ator/atriz deve ser infromado(a).");
			}

		} while (maisAtores);

		float nota = Reader.LeFloat("  Informa a sua nota para o filme: ");

		
		String nomesElenco[] = new String[elenco.size()];
		nomesElenco = (String[]) elenco.toArray(nomesElenco);

		return mc.addMovie(new MovieDto(nome, diretor, nomesElenco, nota));
	}

	public void listarFilmes() {
		titulo("LISTAR TODOS FILMES");

		List<MovieDto> filmesDto = mc.listaFilmesPorNome();

		System.out.println(filmesDto);
	}

	public void procurarFilmePorDiretor() {
		titulo("PROCURAR FILME POR DIRETOR");

		String diretor = Reader.leStr("Qual o nome do diretor que deseja procurar? ");

		List<MovieDto> filmesDto = mc.listaFilmesPorDiretor(diretor);

		System.out.println(filmesDto);
	}

	public void procurarFilmePorAtor() {
		titulo("PROCURAR FILME POR ATOR/ATRIZ");

		String ator = Reader.leStr("Qual o nome do(a) Ator/Atriz que deseja procurar? ");

		List<MovieDto> filmesDto = mc.listaFilmesPorAtor(ator);

		System.out.println(filmesDto);
	}

	public void procurarFilmePorNota() {
		titulo("PROCURAR FILME POR NOTA");

		float nota = Reader.LeFloat("Qual o valor da nota que o filme deve ser maior ou igual? ");

		List<MovieDto> filmesDto = mc.listaFilmesPorNota(nota);

		System.out.println(filmesDto);
	}

	public void listarDiretores() {
		titulo("LISTAR TODOS DIRETORES");

		List<String> diretores = this.mc.listaDiretores();

		System.out.println(diretores);
	}

	public void listarAtores() {
		titulo("LISTAR TODOS ATORES/ATRIZES");

		List<String> atores = this.mc.listaAtores();

		System.out.println(atores);
	}
}
