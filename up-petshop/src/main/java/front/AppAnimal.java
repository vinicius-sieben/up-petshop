package front;

import java.util.List;

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
				listarAnimal();
				break;
			case 3:
				consultarAnimal();
				break;
			case 4:
				atualizarAnimal();
				break;
			case 5:
				deletarAnimal();
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
					objCliente.getAnimais().add(objAnimal);
					if (AnimalPersistencia.incluir(objAnimal) == true && ClientePersistencia.atualizar(objCliente)) {
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

	private static void listarAnimal() {
		System.out.println("\n\n|-| LISTAGEM DE ANIMAIS |-|");
		Animal objAnimal = new Animal();
		objAnimal.setNome(Console.readString("Informe uma parte do nome que deseja listar: "));
		List<Animal> animais = AnimalPersistencia.getAnimais(objAnimal);
		if (!Animal.isEmpty()) {
			System.out.println("Animais registrados:");
			System.out.println("-----------------");
			for (Animal x : animais) {
				System.out.println("    ID tutor: " + x.getTutor().getId());
				System.out.println("    Nome do tutor: " + x.getTutor().getNome());
				System.out.println("	ID animal: " + x.getId());
				System.out.println("	Nome: " + x.getNome());
				System.out.println("	Idade: " + x.getIdade());
				System.out.println("	Especie: " + x.getEspecie());
				System.out.println("	Cor: " + x.getCor());
				System.out.println("	Raca: " + x.getRaca());
				System.out.println("------------------");
			}
		} else {
			System.out.println("\nAnimal(s) nao encontrado!");
		}

	}

	private static void consultarAnimal() {
		System.out.println("\n\n|-| CONSULTA DE ANIMAIS |-|");
		Animal objAnimal = new Animal();
		objAnimal.setId(Console.readInt("Informe o ID do animal que deseja buscar: "));
		objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
		if (objAnimal != null) {
			System.out.println("Animal encontrado:");
			System.out.println("    ID tutor: " + objAnimal.getTutor().getId());
			System.out.println("    Nome do tutor: " + objAnimal.getTutor().getNome());
			System.out.println("	ID animal: " + objAnimal.getId());
			System.out.println("	Nome: " + objAnimal.getNome());
			System.out.println("	Idade: " + objAnimal.getIdade());
			System.out.println("	Especie: " + objAnimal.getEspecie());
			System.out.println("	Cor: " + objAnimal.getCor());
			System.out.println("	Raca: " + objAnimal.getRaca());
			System.out.println("----------------------------------");
		} else {
			System.out.println("\nNenhum animal com esse ID registrado.");
		}
	}

	private static void atualizarAnimal() {
		System.out.println("\n\n|-| ATUALIZACAO DE ANIMAIS |-|");
		Animal objAnimal = new Animal();
		objAnimal.setId(Console.readInt("Informe o ID do animal que deseja atualizar: "));
		objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
		if (objAnimal != null) {
			objAnimal.setNome(Console.readString("Informe o nome atualizado do animal: "));
			objAnimal.setIdade(Console.readInt("Infome a idade atualizada: "));
			objAnimal.setEspecie(Console.readString("Informe a especie atualizada do animal: "));
			objAnimal.setRaca(Console.readString("Informe a raca atualizada do animal: "));
			objAnimal.setCor(Console.readString("Informe a cor atualizada do animal: "));
			String opcao = Console.readString("Deseja atualizar o tutor do animal? S/N: ");
			if (opcao != "S") {
				Cliente objCliente = new Cliente();
				objCliente.setId(Console.readInt("Informe o ID atualizado do tutor: "));
				objCliente = ClientePersistencia.procurarPorId(objCliente);
				if (objCliente != null) {
					objAnimal.setTutor(objCliente);
					AnimalPersistencia.atualizar(objAnimal);
					System.out.println("Animal atualizado com sucesso.");
				} else {
					System.out.println("\nID do tutor nao encontado.");
				}

			} else {
				AnimalPersistencia.atualizar(objAnimal);
				System.out.println("Animal atualizado com sucesso.");
			}
		} else {
			System.out.println("Animal não encontrado.");
		}
	}

	private static void deletarAnimal() {
		System.out.println("\n\n|-| DELECAO DE CLIENTES |-|");
		Animal objAnimal = new Animal();
		objAnimal.setId(Console.readInt("Informe ID do animal a ser deletado: "));
		objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
		if (objAnimal != null) {
			System.out.println("Animal encontrado:");
			System.out.println("    ID tutor: " + objAnimal.getTutor().getId());
			System.out.println("    Nome do tutor: " + objAnimal.getTutor().getNome());
			System.out.println("	ID animal: " + objAnimal.getId());
			System.out.println("	Nome: " + objAnimal.getNome());
			System.out.println("	Idade: " + objAnimal.getIdade());
			System.out.println("	Especie: " + objAnimal.getEspecie());
			System.out.println("	Cor: " + objAnimal.getCor());
			System.out.println("	Raca: " + objAnimal.getRaca());
			System.out.println("----------------------------------");
			String resposta = Console.readString("Deseja deletar esse animal? S/N: ");
			if (resposta.equals("S")) {
				if (AnimalPersistencia.deletar(objAnimal) == true) {
					System.out.println("\nAnimal deletado.");
				} else {
					System.out.println("\nA delecao nao pode ser realizada no momento...");
				}
			} else {
				System.out.println("\nOperacao cancelada.");
			}
		} else {
			System.out.println("\nAnimal não encontrado.");
		}

	}

}
