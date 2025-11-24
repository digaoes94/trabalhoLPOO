package testes;



import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.VisitanteDAO;
import data.JPAUtil;
import entities.Livro;
import entities.Visitante;

public class TesteBackend {

	public static void main(String[] args) {

		try {
			testarGravacaoLivro();
			testarGravacaoVisitante();
			testarEmprestimo();
			testarEmprestimoPolimorfico();

		} finally {
			System.out.println("\n>>> Encerrando sistema...");
			JPAUtil.close();
		}
	}

	private static void testarGravacaoLivro() {
		System.out.println("--- INICIANDO TESTE: LIVRO ---");

		LivroDAO dao = new LivroDAO();

		try {

			Livro novoLivro = new Livro("Harry Potter e a Pedra Filosofal", "Fantasia",
					"O menino que sobreviveu descobre que é bruxo.", "Estante F-12", "J.K. Rowling");

			// SALVANDO NO BANCO.
			System.out.println("Salvando livro no banco de dados...");
			dao.salvar(novoLivro);

			System.out.println("SUCESSO! Livro salvo. ID Gerado: " + novoLivro.getId());
			// BUSCANDO PARA CONFIRMAR SE FOI SALVO, (PROVA REAL)
			Livro livroRecuperado = dao.buscarPorId(novoLivro.getId());

			if (livroRecuperado != null) {
				System.out.println("Confirmação de Busca: Encontrei o livro '" + livroRecuperado.getNome()
						+ "' escrito por " + livroRecuperado.getAutor());
			} else {
				System.err.println("ERRO: O livro foi salvo mas não foi encontrado na busca.");
			}

		} catch (Exception e) {
			System.err.println("FALHA CRÍTICA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// FECHANDO A CONEXÃO PARA NÃO DAR NENHUM PROBLEMA NO BANCO.
			dao.fechar();
			System.out.println("--- FIM DO TESTE ---\n");
		}
	}

	private static void testarGravacaoVisitante() {
		System.out.println("--- INICIANDO TESTE: VISITANTE ---");

		VisitanteDAO dao = new VisitanteDAO();

		try {
			// CRIA UM NOVO VISITANTE
			// TODA VEZ QUE CRIAR UM NOVO VISTANTE PRECISA MUDAR O CPF.
			long numeroAleatorio = System.currentTimeMillis();
			Visitante novoVisitante = new Visitante("Ana Pereira", "CPF: " + numeroAleatorio, "ana.pereira@email.com",
					"(27) 99999-0000");

			System.out.println("Salvando visitante no banco...");
			dao.salvar(novoVisitante);

			System.out.println("SUCESSO! Visitante salvo. ID: " + novoVisitante.getId());

			Visitante encontrado = dao.buscarPorId(novoVisitante.getId());
			if (encontrado != null) {
				System.out.println("Visitante encontrado: " + encontrado.getNome() + " - CPF: " + encontrado.getCpf());
			}

		} catch (Exception e) {
			System.err.println("FALHA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			dao.fechar();
			System.out.println("--- FIM DO TESTE ---\n");
		}
	}

	private static void testarEmprestimo() {
		System.out.println("--- INICIANDO TESTE: EMPRÉSTIMO ---");

		// INSTANCIA UM LIVRO PARA QUE SEJA POSSIVEL EMPRESTA-LO.
		LivroDAO livroDAO = new LivroDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

		try {
			entities.Livro livroParaEmprestar = new entities.Livro("O Hobbit", "Fantasia", "Livro pequeno", "A-10",
					"Tolkien");
			livroDAO.salvar(livroParaEmprestar); // O HIBERNATE GERA O ID.

			// CRIANDO O EMPRÉSTIMO.
			// CONSTRUTOR: DOCUMENTO, CPF MUTUÁRIO, DATA INÍCIO, DATA FIM.
			entities.Emprestimo emp = new entities.Emprestimo(livroParaEmprestar, "123.456.789-00",
					java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(7));

			emprestimoDAO.salvar(emp);
			System.out.println("Empréstimo registrado! ID: " + emp.getId());
			System.out.println("Item: " + emp.getDocumento().getNome());
			System.out.println("Status Aberto? " + emp.getStatus());

			System.out.println("... Simulando devolução ...");
			emprestimoDAO.finalizarEmprestimo(emp.getId());

			entities.Emprestimo empAtualizado = emprestimoDAO.buscarPorId(emp.getId());
			System.out.println("Devolução confirmada! Status Aberto? " + empAtualizado.getStatus());
			System.out.println("Devolvido em: " + empAtualizado.getDataRealDevolucao());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			livroDAO.fechar();
			emprestimoDAO.fechar();
		}
		System.out.println("----------------------------------");
	}

	private static void testarEmprestimoPolimorfico() {
		System.out.println("--- TESTE FINAL: EMPRÉSTIMO DE DVD ---");

		dao.DVDDAO dvdDAO = new dao.DVDDAO();
		dao.EmprestimoDAO emprestimoDAO = new dao.EmprestimoDAO();

		try {
			// 2. Cria e Salva um Documento Digital (DVD)
			// Construtor: nome, genero, descricao, executavel
			entities.DVD filme = new entities.DVD("Interestelar", "Ficção Científica", "Filme sobre buracos negros",
					true);

			System.out.println("Salvando DVD...");
			dvdDAO.salvar(filme);

			entities.Emprestimo emprestimo = new entities.Emprestimo(filme, "555.666.777-88", // CPF
					java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(2));

			System.out.println("Registrando empréstimo...");
			emprestimoDAO.salvar(emprestimo);

			System.out.println("✅ SUCESSO! Empréstimo ID " + emprestimo.getId() + " realizado para o item: "
					+ emprestimo.getDocumento().getNome());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dvdDAO.fechar();
			emprestimoDAO.fechar();
		}
	}

}