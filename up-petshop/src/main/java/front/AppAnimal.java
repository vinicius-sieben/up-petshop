package front;

import entidades.Animal;
import entidades.Cliente;
import negocio.Negocio;
import persistencia.AnimalPersistencia;
import persistencia.ClientePersistencia;

public class AppAnimal {
	public AppAnimal() {

		int op;

		do {
			System.out.println("\n\n*** PAINEL DE ANIMAL ***");
			System.out.println("[1] - Cadastrar animal");
			System.out.println("[2] - Listar animais");
			System.out.println("[3] - Consultar animal");
			System.out.println("[4] - Atualizar animal");
			System.out.println("[5] - Deletar animal");
			System.out.println("[6] - Voltar");
			op = Console.readInt("Selecione uma opcao: ");
			switch (op) {

			case 1:
				incluirAnimal();
				break;
			case 2:
				// listarClientes();
				break;
			case 3:
				// consultarCliente();
				break;
			case 4:
				// atualizarCliente();
				break;
			case 5:
				// deletarCliente();
				break;
			}

		} while (op != 6);
	}

	private static void incluirAnimal() {
		System.out.println("\n\n|-| CADASTRO DE ANIMAL |-|");
		Animal objAnimal = new Animal();
		Cliente objCliente = new Cliente();
		objAnimal.setNome(Console.readString("Informe o nome do animal: "));
		objAnimal.setIdade(Console.readInt("Informe a idade do animal: "));
		objCliente.setCpf(Console.readString("Informe o cpf do tutor: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				objAnimal.setTutor(objCliente);
				System.out.println("Tutor encontrado e associado ao animal");
				objAnimal.setEspecie(Console.readString("Informe a especie do animal: "));
				objAnimal.setCor(Console.readString("Informe a cor do animal: "));
				objAnimal.setRaca(Console.readString("Informe a raca do animal: "));
				String resposta = Console.readString("O animal é castrado S/N?: ");
				if (resposta.equals("S")) {
					objAnimal.setCastrado(true);
					if (AnimalPersistencia.incluir(objAnimal) == true) {
						System.out.println("\nAnimal cadastrado.");
					} else {
						System.out.println("\nNao foi possivel cadastrar o animal, tente novamente.");
					}
				} else if (resposta.equals("N")) {
					objAnimal.setCastrado(false);
					if (AnimalPersistencia.incluir(objAnimal) == true) {
						System.out.println("\nAnimal cadastrado.");
					} else {
						System.out.println("\nNao foi possivel cadastrar o animal, tente novamente.");
					}
				} else {
					System.out.println("\nOpcao invalida.");
				}
			} else {
				System.out.println("\nTutor nao encontrado.");
			}
		} else {
			System.out.println("\nCPF invalido!");
		}

	}

}
