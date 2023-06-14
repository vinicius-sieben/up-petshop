package front;

import java.util.List;

import entidades.Funcionario;
import negocio.Negocio;
import persistencia.FuncionarioPersistencia;

public class AppFuncionario {
	public AppFuncionario() {

		int op;

		do {
			System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\n\r\n"
					+ "  ______                _               __       _       \r\n"
					+ " |  ____|              (_)             /_/      (_)      \r\n"
					+ " | |__ _   _ _ __   ___ _  ___  _ __   __ _ _ __ _  ___  \r\n"
					+ " |  __| | | | '_ \\ / __| |/ _ \\| '_ \\ / _` | '__| |/ _ \\ \r\n"
					+ " | |  | |_| | | | | (__| | (_) | | | | (_| | |  | | (_) |\r\n"
					+ " |_|   \\__,_|_| |_|\\___|_|\\___/|_| |_|\\__,_|_|  |_|\\___/ \r\n"
					+ "                                                         \r\n"
					+ "                                                         \r\n" + "" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[1] - Cadastrar funcionario" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Listar funcionarios" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Consultar funcionario" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Atualizar funcionario" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Deletar funcionario" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[6] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);
			switch (op) {
			case 1:
				incluirFuncionario();
				break;
			case 2:
				listarFuncionarios();
				break;
			case 3:
				consultarFuncionario();
				break;
			case 4:
				atualizarFuncionario();
				break;
			case 5:
				deletarFuncionario();
				break;
			}

		} while (op != 6);
	}

	private static void incluirFuncionario() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CADASTRO DE FUNCIONARIO |-|" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario
				.setCpf(Console.readString(Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do funcionario: " + Colors.RESET));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			if (FuncionarioPersistencia.procurarPorCPF(objFuncionario) == null) {
				objFuncionario.setNome(Console.readString("Informe o nome do funcionario: "));
				objFuncionario.setSalario(Console.readDouble("Informe o salario do funcionario: "));
				objFuncionario.setComissao(Console.readDouble("Informe a comissao do funcionario: "));
				if (FuncionarioPersistencia.incluir(objFuncionario) == true) {
					System.out.println(Colors.GREEN_BRIGHT + "\nFuncionario cadastrado com sucesso." + Colors.RESET);
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA atualizacao nao pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nFuncionario ja cadastrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}
	}

	private static void listarFuncionarios() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE FUNCIONARIOS |-|" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setNome(Console
				.readString(Colors.WHITE_BOLD_BRIGHT + "Informe uma parte do nome que deseja listar: " + Colors.RESET));
		List<Funcionario> funcionarios = FuncionarioPersistencia.getFuncionarios(objFuncionario);
		if (!funcionarios.isEmpty()) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Funcionarios registrados:" + Colors.RESET);
			System.out.println("-----------------");
			for (Funcionario x : funcionarios) {
				System.out.println("	ID: " + x.getId());
				System.out.println("	Nome: " + x.getNome());
				System.out.println("	CPF: " + x.getCpf());
				System.out.println("	Salario: " + x.getSalario());
				System.out.println("	Comissao: " + x.getComissao());
				System.out.println("------------------");
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nFuncionario(s) nao encontrados!" + Colors.RESET);
		}

	}

	private static void consultarFuncionario() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTAR FUNCIONARIOS |-|" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString(
				Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do funcionario que deseja consultar: " + Colors.RESET));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				System.out.println(Colors.GREEN_BOLD_BRIGHT + "Funcionario encontrado:" + Colors.RESET);
				System.out.println("	ID: " + objFuncionario.getId());
				System.out.println("	Nome: " + objFuncionario.getNome());
				System.out.println("	CPF: " + objFuncionario.getCpf());
				System.out.println("	Salario: " + objFuncionario.getSalario());
				System.out.println("	Comissao: " + objFuncionario.getComissao());
			} else {
				System.out.println(Colors.RED_BRIGHT + "Funcionario não encontrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}
	}

	private static void atualizarFuncionario() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| ATUALIZACAO DE FUNCIONARIOS |-|" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString(Colors.WHITE_BOLD_BRIGHT
				+ "Informe o CPF do funcionario que deseja atualizar os dados: " + Colors.RESET));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				objFuncionario.setNome(Console.readString("Informe o nome atualizado do funcionario: "));
				objFuncionario.setCpf(Console.readString("Informe o CPF atualizado do funcionario: "));
				if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
					objFuncionario.setSalario(Console.readDouble("Informe o salario atualizado do funcionario: "));
					objFuncionario.setComissao(Console.readDouble("Informe comissao atualizada do funcionario: "));
					if (FuncionarioPersistencia.atualizar(objFuncionario) == true) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nFuncionario atualizado." + Colors.RESET);
					} else {
						System.out.println(Colors.RED_BRIGHT + "\nA atualizacao nao pode ser realizada no momento..."
								+ Colors.RESET);
					}
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
				}

			} else {
				System.out.println(Colors.RED_BRIGHT + "\nFuncionario não encontrado." + Colors.RESET);
			}

		} else {
			System.out.println("\nCPF invalido!");
		}

	}

	private static void deletarFuncionario() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| DELECAO DE FUNCIONARIOS |-|" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console
				.readString(Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do funcionario a ser deletado: " + Colors.RESET));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				System.out.println(Colors.GREEN_BOLD_BRIGHT + "Funcionario encontrado:" + Colors.RESET);
				System.out.println("	ID: " + objFuncionario.getId());
				System.out.println("	Nome: " + objFuncionario.getNome());
				System.out.println("	CPF: " + objFuncionario.getCpf());
				System.out.println("	Salario: " + objFuncionario.getSalario());
				System.out.println("	Comissao: " + objFuncionario.getComissao());
				String resposta = Console.readString("Deseja deletar esse funcionario? S/N: ");
				if (resposta.equals("S")) {
					if (FuncionarioPersistencia.deletar(objFuncionario) == true) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nFuncionario deletado." + Colors.RESET);
					} else {
						System.out.println(
								Colors.RED_BRIGHT + "\nA delecao nao pode ser realizada no momento..." + Colors.RESET);
					}
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "Funcionario não encontrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}
	}

}
