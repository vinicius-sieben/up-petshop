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
			System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\n\r\n" + "                 _                 _ \r\n"
					+ "     /\\         (_)               | |\r\n" + "    /  \\   _ __  _ _ __ ___   __ _| |\r\n"
					+ "   / /\\ \\ | '_ \\| | '_ ` _ \\ / _` | |\r\n" + "  / ____ \\| | | | | | | | | | (_| | |\r\n"
					+ " /_/    \\_\\_| |_|_|_| |_| |_|\\__,_|_|\r\n" + "                                     \r\n"
					+ "                                     \r\n" + "" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[1] - Cadastrar animal" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Listar animais" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Consultar animal" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Atualizar animal" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Deletar animal" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[6] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);
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
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CADASTRO DE ANIMAL |-|" + Colors.RESET);
		Animal objAnimal = new Animal();
		Cliente objCliente = new Cliente();
		objAnimal.setNome(Console.readString("Informe o nome do animal: "));
		objAnimal.setIdade(Console.readInt("Informe a idade do animal: "));
		objCliente.setCpf(Console.readString("Informe o cpf do tutor: "));
		if (Negocio.validarCPF(objCliente.getCpf()) == true) {
			objCliente = ClientePersistencia.procurarPorCPF(objCliente);
			if (objCliente != null) {
				objAnimal.setTutor(objCliente);
				System.out.println(Colors.GREEN_BOLD_BRIGHT + "Tutor encontrado e associado ao animal" + Colors.RESET);
				objAnimal.setEspecie(Console.readString("Informe a especie do animal: "));
				objAnimal.setCor(Console.readString("Informe a cor do animal: "));
				objAnimal.setRaca(Console.readString("Informe a raca do animal: "));
				String resposta = Console.readString("O animal é castrado S/N?: ");
				if (resposta.equals("S")) {
					objAnimal.setCastrado(true);
					objCliente.getAnimais().add(objAnimal);
					if (AnimalPersistencia.incluir(objAnimal) == true && ClientePersistencia.atualizar(objCliente)) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nAnimal cadastrado." + Colors.RESET);
					} else {
						System.out.println(Colors.RED_BRIGHT + "\nNao foi possivel cadastrar o animal, tente novamente."
								+ Colors.RESET);
					}
				} else if (resposta.equals("N")) {
					objAnimal.setCastrado(false);
					if (AnimalPersistencia.incluir(objAnimal) == true) {
						System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nAnimal cadastrado." + Colors.RESET);
					} else {
						System.out.println(Colors.RED_BRIGHT + "\nNao foi possivel cadastrar o animal, tente novamente."
								+ Colors.RESET);
					}
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nOpcao invalida." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nTutor nao encontrado." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nCPF invalido!" + Colors.RESET);
		}

	}

	private static void listarAnimal() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE ANIMAIS |-|" + Colors.RESET);
		Animal objAnimal = new Animal();
		objAnimal.setNome(Console
				.readString(Colors.WHITE_BOLD_BRIGHT + "Informe uma parte do nome que deseja listar: " + Colors.RESET));
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
			System.out.println(Colors.RED_BRIGHT + "\nAnimal(s) nao encontrado!" + Colors.RESET);
		}

	}

	private static void consultarAnimal() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTA DE ANIMAIS |-|" + Colors.RESET);
		Animal objAnimal = new Animal();
		objAnimal.setId(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o ID do animal que deseja buscar: " + Colors.RESET));
		objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
		if (objAnimal != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Animal encontrado:" + Colors.RESET);
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
			System.out.println(Colors.RED_BRIGHT + "\nNenhum animal com esse ID registrado." + Colors.RESET);
		}
	}

	private static void atualizarAnimal() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| ATUALIZACAO DE ANIMAIS |-|" + Colors.RESET);
		Animal objAnimal = new Animal();
		objAnimal.setId(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o ID do animal que deseja atualizar: " + Colors.RESET));
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
					System.out.println(Colors.GREEN_BOLD_BRIGHT + "Animal atualizado com sucesso." + Colors.RESET);
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nID do tutor nao encontado." + Colors.RESET);
				}

			} else {
				AnimalPersistencia.atualizar(objAnimal);
				System.out.println(Colors.GREEN_BOLD_BRIGHT + "Animal atualizado com sucesso." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "Animal não encontrado." + Colors.RESET);
		}
	}

	private static void deletarAnimal() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| DELECAO DE CLIENTES |-|" + Colors.RESET);
		Animal objAnimal = new Animal();
		objAnimal.setId(
				Console.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe ID do animal a ser deletado: " + Colors.RESET));
		objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
		if (objAnimal != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Animal encontrado:" + Colors.RESET);
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
					System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nAnimal deletado." + Colors.RESET);
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA delecao nao pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nOperacao cancelada." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nAnimal não encontrado." + Colors.RESET);
		}

	}

}
