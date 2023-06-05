package front;

import java.util.List;

import javax.swing.colorchooser.ColorSelectionModel;

import entidades.Animal;
import entidades.Cliente;
import negocio.Negocio;
import persistencia.ClientePersistencia;

public class AppCliente {
	public AppCliente() {

		int op;
		System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\n\n   _____ _ _            _       \r\n"
				+ "  / ____| (_)          | |      \r\n"
				+ " | |    | |_  ___ _ __ | |_ ___ \r\n"
				+ " | |    | | |/ _ \\ '_ \\| __/ _ \\\r\n"
				+ " | |____| | |  __/ | | | ||  __/\r\n"
				+ "  \\_____|_|_|\\___|_| |_|\\__\\___|\r\n"
				+ "                                \r\n"
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
					System.out.println(Colors.RED_BRIGHT + "\nA atualização não pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nCliente já cadastrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF inválido!" + Colors.RESET);
		}
	}

	private static void listarClientes() {
		System.out.println("\n\n|-| LISTAGEM DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		List<Cliente> clientes = ClientePersistencia.getClientes(objCliente);
		if (!clientes.isEmpty()) {
			System.out.println("Clientes registrados:");
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
			System.out.println("\nCliente(s) nao encontrados!");
		}

	}

	private static void consultarCliente() {
		System.out.println("\n\n|-| CONSULTA DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setId(Console.readInt("Informe o ID de cliente que deseja buscar: "));
		objCliente = ClientePersistencia.procurarPorId(objCliente);
		if (objCliente != null) {
			System.out.println("Cliente encontrado:");
			System.out.println("	ID: " + objCliente.getId());
			System.out.println("	Nome: " + objCliente.getNome());
			System.out.println("	CPF: " + objCliente.getCpf());
			System.out.println("	Contato: " + objCliente.getContato());
			System.out.println("	Endereco: " + objCliente.getEndereco());
			System.out.println("----------------------------------");
		} else {
			System.out.println("\nNenhum cliente com esse ID registrado.");
		}
	}

	private static void atualizarCliente() {
		System.out.println("\n\n|-| ATUALIZACAO DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente que deseja atualizar os dados: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				objCliente.setNome(Console.readString("Informe o nome atualizado do cliente: "));
				objCliente.setCpf(Console.readString("Informe o CPF atualizado do cliente: "));
				if (Negocio.validarCPF(objCliente.getCpf()) == true) {
					objCliente.setContato(Console.readString("Informe o contato atualizado do cliente: "));
					objCliente.setEndereco(Console.readString("Informe o endereco atualizado do cliente: "));
					if (ClientePersistencia.atualizar(objCliente) == true) {
						System.out.println("\nCliente atualizado.");
					} else {
						System.out.println("\nA atualizacao nao pode ser realizada no momento...");
					}
				} else {
					System.out.println("\nCPF invalido!");
				}

			} else {
				System.out.println("\nCliente não encontrado.");
			}

		} else {
			System.out.println("\nCPF invalido!");
		}

	}

	private static void deletarCliente() {
		System.out.println("\n\n|-| DELECAO DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente a ser deletado: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				System.out.println("Cliente encontrado:");
				System.out.println("	ID: " + objCliente.getId());
				System.out.println("	Nome: " + objCliente.getNome());
				System.out.println("	CPF: " + objCliente.getCpf());
				System.out.println("	Contato: " + objCliente.getContato());
				System.out.println("	Endereco: " + objCliente.getEndereco());
				String resposta = Console.readString("Deseja deletar esse cliente? S/N: ");
				if (resposta.equals("S")) {
					if (ClientePersistencia.deletar(objCliente) == true) {
						System.out.println("\nCliente deletado.");
					} else {
						System.out.println("\nA delecao nao pode ser realizada no momento...");
					}
				} else {
					System.out.println("\nOperacao cancelada.");
				}
			} else {
				System.out.println("Cliente não encontrado.");
			}
		} else {
			System.out.println("\nCPF invalido!");
		}

	}

	private static void consultarAnimalCliente() {
		System.out.println("\n\n|-| CONSULTAR ANIMAIS DO CLIENTE |-|");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
		if(Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if(objCliente != null) {
				List<Animal> animais = objCliente.getAnimais();
				System.out.println("Animais do cliente: " + objCliente.getNome());
				for (Animal x : animais) {
					System.out.println("---------------");
					System.out.println("ID animal: " + x.getId());
					System.out.println("Nome animal: " + x.getNome());
					System.out.println("---------------");
				}
			} else {
				System.out.println("\nCliente não encontrado.");
			}
		} else {
			System.out.println("\nCPF inválido.");
		}
	}
	
}