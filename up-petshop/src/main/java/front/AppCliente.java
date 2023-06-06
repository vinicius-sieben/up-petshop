package front;

import java.util.List;

import entidades.Animal;
import entidades.Cliente;
import negocio.Negocio;
import persistencia.ClientePersistencia;

public class AppCliente {
	public AppCliente() {

		int op;
		System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\n\n   _____ _ _            _       \r\n"
				+ "  / ____| (_)          | |      \r\n" + " | |    | |_  ___ _ __ | |_ ___ \r\n"
				+ " | |    | | |/ _ \\ '_ \\| __/ _ \\\r\n" + " | |____| | |  __/ | | | ||  __/\r\n"
				+ "  \\_____|_|_|\\___|_| |_|\\__\\___|\r\n" + "                                \r\n"
				+ "                                " + Colors.RESET);

		do {
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "\n[1] - Cadastrar cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Listar clientes" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Consultar cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Atualizar cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Deletar cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[6] - Consultar animais do cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[7] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);
			switch (op) {
			case 1:
				incluirCliente();
				break;
			case 2:
				listarClientes();
				break;
			case 3:
				consultarCliente();
				break;
			case 4:
				atualizarCliente();
				break;
			case 5:
				deletarCliente();
				break;
			case 6:
				consultarAnimalCliente();
				break;
			}

		} while (op != 7);
	}

	private static void incluirCliente() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\nCADASTRO DE CLIENTE" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("\nInforme o CPF do cliente: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o nome do cliente: "));
				objCliente.setContato(Console.readString("Informe o número de contato do cliente: "));
				objCliente.setEndereco(Console.readString("Informe o endereço do cliente: "));
				if (ClientePersistencia.incluir(objCliente) == true) {
					System.out.println(Colors.GREEN_BRIGHT + "\nCliente cadastrado com sucesso." + Colors.RESET);
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA atualização não pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nCliente já cadastrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF inválido!" + Colors.RESET);
		}
	}

	private static void listarClientes() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE CLIENTES |-|" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setNome(Console.readString(
				Colors.YELLOW_BOLD_BRIGHT + "Informe uma parte do nome que deseja listar: " + Colors.RESET));
		List<Cliente> clientes = ClientePersistencia.getClientes(objCliente);
		if (!clientes.isEmpty()) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Clientes registrados:" + Colors.RESET);
			System.out.println("-----------------");
			for (Cliente x : clientes) {
				System.out.println("	ID: " + x.getId());
				System.out.println("	Nome: " + x.getNome());
				System.out.println("	CPF: " + x.getCpf());
				System.out.println("	Contato: " + x.getContato());
				System.out.println("	Endereco: " + x.getEndereco());
				System.out.println("------------------");
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCliente(s) nao encontrados!" + Colors.RESET);
		}

	}

	private static void consultarCliente() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTA DE CLIENTES |-|" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setId(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o ID de cliente que deseja buscar: " + Colors.RESET));
		objCliente = ClientePersistencia.procurarPorId(objCliente);
		if (objCliente != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Cliente encontrado:" + Colors.RESET);
			System.out.println("	ID: " + objCliente.getId());
			System.out.println("	Nome: " + objCliente.getNome());
			System.out.println("	CPF: " + objCliente.getCpf());
			System.out.println("	Contato: " + objCliente.getContato());
			System.out.println("	Endereco: " + objCliente.getEndereco());
			System.out.println("----------------------------------");
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nNenhum cliente com esse ID registrado." + Colors.RESET);
		}
	}

	private static void atualizarCliente() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| ATUALIZACAO DE CLIENTES |-|" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString(
				Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do cliente que deseja atualizar os dados: " + Colors.RESET));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				objCliente.setNome(Console.readString("Informe o nome atualizado do cliente: "));
				objCliente.setCpf(Console.readString("Informe o CPF atualizado do cliente: "));
				if (Negocio.validarCPF(objCliente.getCpf()) == true) {
					objCliente.setContato(Console.readString("Informe o contato atualizado do cliente: "));
					objCliente.setEndereco(Console.readString("Informe o endereco atualizado do cliente: "));
					if (ClientePersistencia.atualizar(objCliente) == true) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nCliente atualizado." + Colors.RESET);
					} else {
						System.out.println(Colors.RED_BRIGHT + "\nA atualizacao nao pode ser realizada no momento..."
								+ Colors.RESET);
					}
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
				}

			} else {
				System.out.println(Colors.RED_BRIGHT + "\nCliente não encontrado." + Colors.RESET);
			}

		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}

	}

	private static void deletarCliente() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| DELETACAO DE CLIENTES |-|" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console
				.readString(Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do cliente a ser deletado: " + Colors.RESET));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				System.out.println(Colors.GREEN_BOLD_BRIGHT + "Cliente encontrado:" + Colors.RESET);
				System.out.println("	ID: " + objCliente.getId());
				System.out.println("	Nome: " + objCliente.getNome());
				System.out.println("	CPF: " + objCliente.getCpf());
				System.out.println("	Contato: " + objCliente.getContato());
				System.out.println("	Endereco: " + objCliente.getEndereco());
				String resposta = Console.readString(Colors.RED_BRIGHT + "Deseja deletar esse cliente? " + Colors.RESET
						+ Colors.GREEN_BRIGHT + "S/N: " + Colors.RESET);
				if (resposta.equals("S")) {
					if (ClientePersistencia.deletar(objCliente) == true) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nCliente deletado." + Colors.RESET);
					} else {
						System.out.println(
								Colors.RED_BRIGHT + "\nA delecao nao pode ser realizada no momento..." + Colors.RESET);
					}
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nOperacao cancelada." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "Cliente não encontrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}

	}

	private static void consultarAnimalCliente() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTAR ANIMAIS DO CLIENTE |-|" + Colors.RESET);
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString(Colors.WHITE_BOLD_BRIGHT + "Informe o CPF do cliente: " + Colors.RESET));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				List<Animal> animais = objCliente.getAnimais();
				System.out.println("\nAnimais do cliente: " + objCliente.getNome());
				for (Animal x : animais) {
					System.out.println("---------------");
					System.out.println("ID animal: " + x.getId());
					System.out.println("Nome animal: " + x.getNome());
					System.out.println("---------------");
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nCliente não encontrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF inválido." + Colors.RESET);
		}
	}

}