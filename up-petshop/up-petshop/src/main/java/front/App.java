package front;

public class App {

	public static void main(String[] args) {

		int op;

		do {
			System.out.println("\n\n-------- PETSHOP --------");
			System.out.println("[1] - Menu cliente");
			System.out.println("[2] - Menu funcionario");
			System.out.println("[3] - Menu animal");
			System.out.println("[4] - Sair");
			op = Console.readInt("Selecione uma opcao: ");
			switch (op) {
			case 1:
				new AppCliente();
				break;
			case 2:
				new AppFuncionario();
				break;
			case 3:
				new AppAnimal();
				break;
			}

		} while (op != 4);

	}

}
