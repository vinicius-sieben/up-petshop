package front;

public class App {

	public static void main(String[] args) {

		int op;
		
		do {
			System.out.println("-------- PETSHOP --------");
			System.out.println("[1] - Menu cliente");
			op = Console.readInt("Selecione uma opcao: ");
			switch(op) {
			case 1:
				new AppCliente();
				break;
			default:
				System.out.println("Opcao invalida!");
			}

		} while (op != 1);
	
		
	}

}
