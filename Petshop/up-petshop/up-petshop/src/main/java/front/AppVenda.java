package front;

import java.util.List;

import entidades.Animal;
import entidades.Funcionario;
import entidades.Servico;
import entidades.ServicoVenda;
import entidades.Venda;
import negocio.Negocio;
import negocio.ServicoNegocio;
import persistencia.AnimalPersistencia;
import persistencia.FuncionarioPersistencia;
import persistencia.ServicosPersistencia;
import persistencia.VendaPersistencia;

public class AppVenda {
	public AppVenda() {

		System.out.println(Colors.YELLOW_BOLD_BRIGHT + "\r\n" + " __      __            _           \r\n"
				+ " \\ \\    / /           | |          \r\n" + "  \\ \\  / /__ _ __   __| | __ _ ___ \r\n"
				+ "   \\ \\/ / _ \\ '_ \\ / _` |/ _` / __|\r\n" + "    \\  /  __/ | | | (_| | (_| \\__ \\\r\n"
				+ "     \\/ \\___|_| |_|\\__,_|\\__,_|___/\r\n" + "                                   \r\n"
				+ "                                   \r\n" + "" + Colors.RESET);

		int op;

		do {
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "\n[1] - Cadastrar venda" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Listar vendas" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Consultar venda" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Deletar venda" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);

			switch (op) {
			case 1:
				cadastrarVenda();
				break;
			case 2:
				listarVendas();
				break;
			case 3:
				consultarVendas();
				break;
			case 4:
				deletarVenda();
				break;
			}

		} while (op != 5);

	}

	private static void cadastrarVenda() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\nCADASTRO DE VENDA" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("Informe o CPF do funcionário: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				Animal objAnimal = new Animal();
				objAnimal.setId(Console.readInt("Informe o ID do animal: "));
				objAnimal = AnimalPersistencia.procurarPorId(objAnimal);
				if (objAnimal != null) {
					Venda objVenda = new Venda();
					objVenda.setFuncionario(objFuncionario);
					objVenda.setIdAnimal(objAnimal.getId());
					objVenda.setData(Console.readString("Informe a data da venda: "));
					objVenda.setCodigo(Console.readInt("Informe o codigo da venda: "));
					double precoVenda = 0;
					String resposta = "";
					do {
						ServicoVenda objServicoVenda = new ServicoVenda();
						Servico objServico = new Servico();
						objServico.setId(Console.readInt("Informe o ID do serviço a ser adicionado: "));
						objServico = ServicosPersistencia.procurarPorId(objServico);
						if (objServico != null) {
							objServicoVenda.setServico(objServico);
							objServicoVenda.setQuantidade(Console.readInt("Informe a quantidade: "));
							precoVenda += ServicoNegocio.calcularPrecoVenda(objServico);
							objServicoVenda.setUnitario(precoVenda);
							System.out.println("Valor da compra: " + precoVenda);
							objVenda.getServicos().add(objServicoVenda);
							resposta = Console.readString("Mais produtos? ");
						} else {
							System.out.println("\n\nProduto não encontrado...");
						}
					} while (resposta.equals("S"));
					objVenda.setTotal(precoVenda);
					if (VendaPersistencia.incluir(objVenda) == true) {
						System.out.println(Colors.GREEN_BRIGHT + "\nVenda cadastrada com sucesso." + Colors.RESET);
					} else {
						System.out.println(Colors.RED_BRIGHT + "\nA atualização não pode ser realizada no momento..."
								+ Colors.RESET);
					}
				} else {
					System.out.println("animal não encontrado.");
				}
			} else {
				System.out.println("Funcionario não encontrado.");
			}
		} else {
			System.out.println("CPF inválido.");
		}
	}

	private static void listarVendas() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| LISTAGEM DE VENDAS |-|" + Colors.RESET);
		Venda objVenda = new Venda();
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString(Colors.YELLOW_BOLD_BRIGHT
				+ "Informe o CPF do funcionario que deseja listar as vendas: " + Colors.RESET));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				objVenda.setFuncionario(objFuncionario);
				List<Venda> vendas = VendaPersistencia.procurarPorFuncionario(objFuncionario);
				if (!vendas.isEmpty()) {
					for (Venda x : vendas) {
						System.out.println("	Código da venda: " + x.getCodigo());
						System.out.println("	ID animal: " + x.getIdAnimal());
						System.out.println("	Valor total: " + x.getTotal());
						System.out.println("------------------");
					}
				} else {
					System.out.println(Colors.RED_BRIGHT + "\nVenda(s) não encontrada(s)!" + Colors.RESET);
				}
			} else {
				System.out.println("Funcionário não encontrado.");
			}
		} else {
			System.out.println("CPF Inválido.");
		}

	}

	private static void consultarVendas() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| CONSULTA DE VENDAS |-|" + Colors.RESET);
		Venda objVenda = new Venda();
		objVenda.setCodigo(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o codigo da venda que deseja buscar: " + Colors.RESET));
		objVenda = VendaPersistencia.procurarPorCodigo(objVenda);
		if (objVenda != null) {
			System.out.println("	Código da venda: " + objVenda.getCodigo());
			System.out.println("	ID animal: " + objVenda.getIdAnimal());
			System.out.println("	Valor total: " + objVenda.getTotal());
			System.out.println("------------------");
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nNenhuma venda com esse codigo registrada." + Colors.RESET);
		}
	}

	private static void deletarVenda() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\n|-| DELECAO DE VENDA |-|" + Colors.RESET);
		Venda objVenda = new Venda();
		objVenda.setCodigo(Console
				.readInt(Colors.WHITE_BOLD_BRIGHT + "Informe o codigo da venda a ser deletado: " + Colors.RESET));
		objVenda = VendaPersistencia.procurarPorCodigo(objVenda);
		if (objVenda != null) {
			System.out.println(Colors.GREEN_BOLD_BRIGHT + "Venda encontrado:" + Colors.RESET);
			System.out.println("    Valor da venda: " + objVenda.getTotal());
			System.out.println("    Funcionario responsavel: " + objVenda.getFuncionario().getNome());
			System.out.println("     Animal atendido: " + objVenda.getIdAnimal());
			System.out.println("----------------------------------");
			String resposta = Console.readString("Deseja deletar essa venda? S/N: ");
			if (resposta.equals("S")) {
				if (VendaPersistencia.deletar(objVenda) == true) {
					System.out.println(Colors.GREEN_BOLD_BRIGHT + "\nVenda deletado." + Colors.RESET);
				} else {
					System.out.println(
							Colors.RED_BRIGHT + "\nA delecao nao pode ser realizada no momento..." + Colors.RESET);
				}
			} else {
				System.out.println(Colors.RED_BRIGHT + "\nOperacao cancelada." + Colors.RESET);
			}
		} else {
			System.out.println(Colors.RED_BRIGHT + "\nVenda não encontrado." + Colors.RESET);
		}

	}

}
