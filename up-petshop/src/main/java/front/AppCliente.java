package front;

import java.util.List;

import entidades.Cliente;
import negocio.Negocio;
import persistencia.ClientePersistencia;

public class AppCliente {
	public AppCliente() {

		int op;

		do {
			System.out.println("\n\n-------- PAINEL DE CLIENTE --------");
			System.out.println("[1] - Cadastrar cliente");
			System.out.println("[2] - Listar clientes");
			System.out.println("[3] - Consultar cliente");
			System.out.println("[4] - Alterar cliente");
			System.out.println("[5] - Excluir cliente");
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
				// alterarCliente();
				break;
			case 5:
				// excluirCliente();
				break;
			default:
				System.out.println("Opcao invalida!");
			}

		} while (op != 6);
	}

	private static void incluirCliente() {
		System.out.println("\n\n|-| CADASTRO DE CLIENTE |-|");
		Cliente objCliente = new Cliente();
		objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
		if (Negocio.validarCPF(objCliente.getCpf()) != false) {
			objCliente.setNome(Console.readString("Informe o nome do cliente: "));
			if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
				objCliente.setContato(Console.readString("Informe o numero de contato do cliente: "));
				objCliente.setEndereco(Console.readString("Informe o endereco do cliente: "));
				ClientePersistencia.incluir(objCliente);
				System.out.println("Cliente cadastrado com sucesso.");
			} else {
				System.out.println("Cliente ja cadastrado.");
			}

		} else {
			System.out.println("CPF invalido!");
		}
	}

	private static void listarClientes() {
		System.out.println("\n\n|-| LISTAGEM DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		List<Cliente> clientes = ClientePersistencia.getClientes(objCliente);
		if(!clientes.isEmpty()) {
			System.out.println("Clientes registrados:");
			System.out.println("-----------------");
			for(Cliente x : clientes) {
					System.out.println("ID: " + x.getId());
					System.out.println("Nome: " + x.getNome());
					System.out.println("CPF: " + x.getCpf());
					System.out.println("Contato: " + x.getContato());
					System.out.println("Endereco: " + x.getEndereco());
					System.out.println("-----------------");
			}
		} else {
			System.out.println("Cliente(s) nao encontrados!");
		}
		
	}
	
	private static void consultarCliente() {
		System.out.println("\n\n|-| CONSULTA DE CLIENTES |-|");
		Cliente objCliente = new Cliente();
		objCliente.setId(Console.readInt("Informe o ID de cliente que deseja buscar: "));
		objCliente = ClientePersistencia.procurarPorId(objCliente);
		if(objCliente != null) {
			System.out.println("Cliente encontrado:");
			System.out.println("	ID: " + objCliente.getId());
			System.out.println("	Nome: " + objCliente.getNome());
			System.out.println("	CPF: " + objCliente.getCpf());
			System.out.println("	Contato: " + objCliente.getContato());
			System.out.println("	Endereco: " + objCliente.getEndereco());
			System.out.println("----------------------------------");
		} else {
			System.out.println("Nenhum cliente com esse ID registrado.");
		}
	}

}