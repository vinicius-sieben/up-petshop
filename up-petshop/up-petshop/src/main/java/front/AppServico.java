package front;

import java.util.List;

import entidades.Funcionario;
import entidades.Servico;
import negocio.Negocio;
import persistencia.FuncionarioPersistencia;
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
				consultarServicos();
				break;
			case 4:
				atualizarServico();
				break;
			case 5:
				deletarServicos();
				break;
			}

		} while (op != 6);

	}

	private static void cadastrarServico() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\nCADASTRO DE SERVICO" + Colors.RESET);
		Servico objServico = new Servico();
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("\nInforme o CPF do funcionario: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			if (FuncionarioPersistencia.procurarPorCPF(objFuncionario) != null) {
				objServico.setDescricao(Console.readString("Informe a descrição do servico: "));
				objServico.setValor(Console.readDouble("Informe o valor do servico: "));
				if (ServicosPersistencia.incluir(objServico) == true) {
					System.out.println("\nServico cadastrado! ");
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA atualização não pode ser realizada no momento..." + Colors.RESET);
				}

			} else {
				System.out.println(Colors.RED_BRIGHT + "\nServico já cadastrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF inválido!" + Colors.RESET);
		}
	}

	private static void listarServicos() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE SERVICOS |-|" + Colors.RESET);
		Servico objServico = new Servico();
		objServico.setDescricao(Console.readString(
				Colors.YELLOW_BOLD_BRIGHT + "Informe uma parte do serviço que deseja listar: " + Colors.RESET));
		List<Servico> servicos = ServicosPersistencia.getServicos(objServico);
		if (!servicos.isEmpty()) {
			for (Servico x : servicos) {
				System.out.println("	ID: " + x.getId());
				System.out.println("	Descrição: " + x.getDescricao());
				System.out.println("	Valor: " + x.getValor());
				System.out.println("------------------");
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nServico(s) não encontrado(s)!" + Colors.RESET);
		}
	}

	private static void consultarServicos() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTA DE SERVICOS |-|" + Colors.RESET);
		Servico objServico = new Servico();
		objServico.setId(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o ID do servico que deseja buscar: " + Colors.RESET));
		objServico = ServicosPersistencia.procurarPorId(objServico);
		if (objServico != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Servico encontrado:" + Colors.RESET);
			System.out.println("    Descricao do servico: " + objServico.getDescricao());
			System.out.println("    Valor do servico : " + objServico.getValor());
			System.out.println("----------------------------------");
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nNenhum servico com esse ID registrado." + Colors.RESET);
		}
	}

	private static void atualizarServico() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| ATUALIZACAO DE SERVICOS |-|" + Colors.RESET);
		Servico objServico = new Servico();
		objServico.setId(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o ID do servico que deseja atualizar: " + Colors.RESET));
		objServico = ServicosPersistencia.procurarPorId(objServico);
		if (objServico != null) {
			objServico.setDescricao(Console.readString("Informe a descrição atualizada do servico: "));
			objServico.setValor(Console.readDouble("Informe o valor atualizado do servico: "));
			ServicosPersistencia.atualizar(objServico);
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Servico atualizado com sucesso." + Colors.RESET);
		} else {
			System.out.println(Colors.RED_BRIGHT + "Servico não encontrado." + Colors.RESET);
		}
	}

	private static void deletarServicos() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| DELECAO DE SERVICOS |-|" + Colors.RESET);
		Servico objServico = new Servico();
		objServico.setId(
				Console.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe ID do servico a ser deletado: " + Colors.RESET));
		objServico = ServicosPersistencia.procurarPorId(objServico);
		if (objServico != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Servico encontrado:" + Colors.RESET);
			System.out.println("    Descricao do servico: " + objServico.getDescricao());
			System.out.println("    Valor do servico: " + objServico.getValor());
			System.out.println("----------------------------------");
			String resposta = Console.readString("Deseja deletar esse Servico? S/N: ");
			if (resposta.equals("S")) {
				if (ServicosPersistencia.deletar(objServico) == true) {
					System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nServico deletado." + Colors.RESET);
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA delecao nao pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nOperacao cancelada." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nServico não encontrado." + Colors.RESET);
		}

	}

}
