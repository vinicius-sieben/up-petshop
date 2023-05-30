package front;

import java.util.List;

import entidades.Cliente;
import negocio.Negocio;
import persistencia.ClientePersistencia;

public class AppCliente {
	public AppCliente() {

		int op;

		do {
			System.out.println("\n\n*** PAINEL DE CLIENTE ***");
			System.out.println("[1] - Cadastrar cliente");
			System.out.println("[2] - Listar clientes");
			System.out.println("[3] - Consultar cliente");
			System.out.println("[4] - Atualizar cliente");
			System.out.println("[5] - Deletar cliente");
			System.out.println("[6] - Voltar");
			op = Console.readInt("Selecione uma opcao: ");
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
			}

		} while (op != 6);
	}

	private static void incluirCliente() {
		System.out.println("\n\n|-| CADASTRO DE CLIENTE |-|");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setNome(Console.readString("Informe o nome do cliente: "));
				objCliente.setContato(Console.readString("Informe o numero de contato do cliente: "));
				objCliente.setEndereco(Console.readString("Informe o endereco do cliente: "));
				if (ClientePersistencia.incluir(objCliente) == true) {
					System.out.println("\nCliente cadastrado com sucesso.");
				} else {
					System.out.println("\nA atualizacao nao pode ser realizada no momento...");
				}
			} else {
				System.out.println("\nCliente ja cadastrado.");
			}
		} else {
			System.out.println("\nCPF invalido!");
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
				objCliente.setContato(Console.readString("Informe o contato atualizado do cliente: "));
				objCliente.setEndereco(Console.readString("Informe o endereco atualizado do cliente: "));
				if (ClientePersistencia.atualizar(objCliente) == true) {
					System.out.println("\nCliente atualizado.");
				} else {
					System.out.println("\nA atualizacao nao pode ser realizada no momento...");
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

}