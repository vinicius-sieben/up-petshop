package front;

public class App {

	public static void main(String[] args) {

		int op;
			System.out.println(Colors.GREEN_BOLD + "\r\n"
					+ "  _____  ______ _______ _____ _    _  ____  _____  \r\n"
					+ " |  __ \\|  ____|__   __/ ____| |  | |/ __ \\|  __ \\ \r\n"
					+ " | |__) | |__     | | | (___ | |__| | |  | | |__) |\r\n"
					+ " |  ___/|  __|    | |  \\___ \\|  __  | |  | |  ___/ \r\n"
					+ " | |    | |____   | |  ____) | |  | | |__| | |     \r\n"
					+ " |_|    |______|  |_| |_____/|_|  |_|\\____/|_|     \r\n"
					+ "                                                   \r\n"
					+ "                                                   \r\n"
					+ "" + Colors.RESET);
		do {
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
