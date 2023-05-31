package front;

import java.util.List;

import entidades.Funcionario;
import negocio.Negocio;
import persistencia.FuncionarioPersistencia;

public class AppFuncionario {
	public AppFuncionario() {

		int op;

		do {
			System.out.println("\n\n*** PAINEL DE FUNCIONARIO ***");
			System.out.println("[1] - Cadastrar funcionario");
			System.out.println("[2] - Listar funcionarios");
			System.out.println("[3] - Consultar funcionario");
			System.out.println("[4] - Atualizar funcionario");
			System.out.println("[5] - Deletar funcionario");
			System.out.println("[6] - Voltar");
			op = Console.readInt("Selecione uma opcao: ");
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
		System.out.println("\n\n|-| CADASTRO DE FUNCIONARIO |-|");
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("Informe o CPF do funcionario: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			if (FuncionarioPersistencia.procurarPorCPF(objFuncionario) == null) {
				objFuncionario.setNome(Console.readString("Informe o nome do funcionario: "));
				objFuncionario.setSalario(Console.readDouble("Informe o salario do funcionario: "));
				objFuncionario.setComissao(Console.readDouble("Informe a comissao do funcionario: "));
				if (FuncionarioPersistencia.incluir(objFuncionario) == true) {
					System.out.println("\nFuncionario cadastrado com sucesso.");
				} else {
					System.out.println("\nA atualizacao nao pode ser realizada no momento...");
				}
			} else {
				System.out.println("\nFuncionario ja cadastrado.");
			}
		} else {
			System.out.println("\nCPF invalido!");
		}
	}

	private static void listarFuncionarios() {
		System.out.println("\n\n|-| LISTAGEM DE FUNCIONARIOS |-|");
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		List<Funcionario> funcionarios = FuncionarioPersistencia.getFuncionarios(objFuncionario);
		if (!funcionarios.isEmpty()) {
			System.out.println("Funcionarios registrados:");
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
			System.out.println("\nFuncionario(s) nao encontrados!");
		}

	}

	private static void consultarFuncionario() {
		System.out.println("\n\n|-| CONSULTA DE FUNCIONARIOS |-|");
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setId(Console.readInt("Informe o ID do funcionario que deseja buscar: "));
		objFuncionario = FuncionarioPersistencia.procurarPorId(objFuncionario);
		if (objFuncionario != null) {
			System.out.println("Funcionario encontrado:");
			System.out.println("	ID: " + objFuncionario.getId());
			System.out.println("	Nome: " + objFuncionario.getNome());
			System.out.println("	CPF: " + objFuncionario.getCpf());
			System.out.println("	Salario: " + objFuncionario.getSalario());
			System.out.println("	Comissao: " + objFuncionario.getComissao());
			System.out.println("----------------------------------");
		} else {
			System.out.println("\nNenhum funcionario com esse ID registrado.");
		}
	}

	private static void atualizarFuncionario() {
		System.out.println("\n\n|-| ATUALIZACAO DE FUNCIONARIOS |-|");
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("Informe o CPF do funcionario que deseja atualizar os dados: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				objFuncionario.setNome(Console.readString("Informe o nome atualizado do funcionario: "));
				objFuncionario.setCpf(Console.readString("Informe o CPF atualizado do funcionario: "));
				if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
					objFuncionario.setSalario(Console.readDouble("Informe o salario atualizado do funcionario: "));
					objFuncionario.setComissao(Console.readDouble("Informe comissao atualizada do funcionario: "));
					if (FuncionarioPersistencia.atualizar(objFuncionario) == true) {
						System.out.println("\nFuncionario atualizado.");
					} else {
						System.out.println("\nA atualizacao nao pode ser realizada no momento...");
					}
				} else {
					System.out.println("\nCPF invalido!");
				}

			} else {
				System.out.println("\nFuncionario não encontrado.");
			}

		} else {
			System.out.println("\nCPF invalido!");
		}

	}

	private static void deletarFuncionario() {
		System.out.println("\n\n|-| DELECAO DE FUNCIONARIOS |-|");
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("Informe o CPF do funcionario a ser deletado: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				System.out.println("Funcionario encontrado:");
				System.out.println("	ID: " + objFuncionario.getId());
				System.out.println("	Nome: " + objFuncionario.getNome());
				System.out.println("	CPF: " + objFuncionario.getCpf());
				System.out.println("	Salario: " + objFuncionario.getSalario());
				System.out.println("	Comissao: " + objFuncionario.getComissao());
				String resposta = Console.readString("Deseja deletar esse funcionario? S/N: ");
				if (resposta.equals("S")) {
					if(FuncionarioPersistencia.deletar(objFuncionario) == true) {
						System.out.println("\nFuncionario deletado.");
					} else {
						System.out.println("\nA delecao nao pode ser realizada no momento...");
					}
				}
			} else {
				System.out.println("Funcionario não encontrado.");
			}
		} else {
			System.out.println("\nCPF invalido!");
		}
	}

}