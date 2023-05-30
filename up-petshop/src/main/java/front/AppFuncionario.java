package front;

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
				// incluirFuncionario();
				break;
			case 2:
				// listarFuncionarios();
				break;
			case 3:
				// consultarFuncionario();
				break;
			case 4:
				// atualizarFuncionario();
				break;
			case 5:
				// deletarFuncionario();
				break;
			}

		} while (op != 6);
	}
}
