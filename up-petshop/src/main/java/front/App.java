package front;

public class App {

	public static void main(String[] args) {

		int op;
			System.out.println(Colors.GREEN_BOLD + ",-.___,-.\r\n"
					+ "\\_/_ _\\_/\r\n"
					+ "  )O_O(\r\n"
					+ " { (_) }\r\n"
					+ "  `-^-'  \r\n"
					+ "  _____  ______ _______ _____ _    _  ____  _____  \r\n"
					+ " |  __ \\|  ____|__   __/ ____| |  | |/ __ \\|  __ \\ \r\n"
					+ " | |__) | |__     | | | (___ | |__| | |  | | |__) |\r\n"
					+ " |  ___/|  __|    | |  \\___ \\|  __  | |  | |  ___/ \r\n"
					+ " | |    | |____   | |  ____) | |  | | |__| | |     \r\n"
					+ " |_|    |______|  |_| |_____/|_|  |_|\\____/|_|     \r\n"
					+ "                                                    \r\n"
					+ "                                                   \r\n"  + Colors.RESET);
		do {
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[1] - Menu cliente" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[2] - Menu funcionario" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[3] - Menu animal" + Colors.RESET);
			System.out.println(Colors.WHITE_BOLD_BRIGHT + "[4] - Sair" + Colors.RESET);
			op = Console.readInt(Colors.WHITE_BOLD_BRIGHT + "\nSelecione uma opção: " + Colors.RESET);
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
