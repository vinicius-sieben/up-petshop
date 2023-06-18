package front;

import java.util.List;

import entidades.Servico;
import persistencia.ServicosPersistencia;

public class AppServico {
	public AppServico() {
		System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\n\n   _____                 _               \r\n"
				+ "  / ____|               (_)              \r\n" + " | (___   ___ _ ____   ___  ___ ___  ___ \r\n"
				+ "  \\___ \\ / _ \\ '__\\ \\ / / |/ __/ _ \\/ __|\r\n"
				+ "  ____) |  __/ |   \\ V /| | (_| (_) \\__ \\\r\n"
				+ " |_____/ \\___|_|    \\_/ |_|\\___\\___/|___/\r\n" + "                            )_)          \r\n"
				+ "                                         " + Colors.RESET);

		int op;

		do {
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "\n[1] - Cadastrar servico" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Listar servicos" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Consultar servico" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Atualizar servico" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Deletar servico" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[6] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);

			switch (op) {
			case 1:
				cadastrarServico();
				break;
			case 2:
				listarServicos();
				break;
			case 3:
				// consultarServico();
				break;
			case 4:
				// atualizarServico();
				break;
			case 5:
				// deletarServico();
				break;
			}

		} while (op != 6);

	}

	private static void cadastrarServico() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\nCADASTRO DE SERVICO" + Colors.RESET);
		Servico objServico = new Servico();
	}

	private static void listarServicos() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE SERVICOS |-|" + Colors.RESET);
		Servico objServico = new Servico();
		objServico.setDescricao(Console.readString(
				Colors.YELLOW_BOLD_BRIGHT + "Informe uma parte do serviço que deseja listar: " + Colors.RESET));
		List<Servico> servicos = ServicosPersistencia.getServicos(objServico);
		if (!servicos.isEmpty()) {
			for(Servico x : servicos) {
				System.out.println("	ID: " + x.getId());
				System.out.println("	Descrição: " + x.getDescricao());
				System.out.println("	Valor: " + x.getValor());
				System.out.println("------------------");
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nServico(s) não encontrado(s)!" + Colors.RESET);
		}
	}

}
