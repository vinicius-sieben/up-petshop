package front;

import entidades.Cliente;
import entidades.Funcionario;
import negocio.Negocio;
import persistencia.ClientePersistencia;
import persistencia.FuncionarioPersistencia;

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
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Atualizar venda" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[5] - Deletar venda" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[6] - Voltar" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opcao: " + Colors.RESET);

			switch (op) {
			case 1:
				cadastrarVenda();
				break;
			case 2:
				// listarVendas();
				break;
			case 3:
				// consultarVenda();
				break;
			case 4:
				// atualizarVenda();
				break;
			case 5:
				// deletarVenda();
				break;
			}

		} while (op != 6);

	}

	private static void cadastrarVenda() {
		System.out.println(Colors.GREEN_BACKGROUND + "\n\nCADASTRO DE VENDA" + Colors.RESET);
		Funcionario objFuncionario = new Funcionario();
		objFuncionario.setCpf(Console.readString("Informe o CPF do funcionário: "));
		if (Negocio.validarCPF(objFuncionario.getCpf()) == true) {
			objFuncionario = FuncionarioPersistencia.procurarPorCPF(objFuncionario);
			if (objFuncionario != null) {
				Cliente objCliente = new Cliente();
				objCliente.setCpf(Console.readString("Informe o CPF do cliente: "));
				if (Negocio.validarCPF(objCliente.getCpf()) == true) {
					objCliente = ClientePersistencia.procurarPorCPF(objCliente);
					if (objCliente != null) {
						// Listar animais do cliente e escolher o animal p associar
					}
					System.out.println("Cliente não encontrado.");
				} else {
					System.out.println("CPF inválido.");
				}
			} else {
				System.out.println("Funcionario não encontrado.");
			}
		} else {
			System.out.println("CPF inválido.");
		}
	}

}
