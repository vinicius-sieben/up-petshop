package negocio;

public class Negocio {

	public static boolean validarCPF(String cpf) {
		// Remover caracteres especiais e espaços em branco
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verificar se possui 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verificar se todos os dígitos são iguais (caso contrário, o CPF é inválido)
		boolean todosDigitosIguais = true;
		for (int i = 1; i < 11; i++) {
			if (cpf.charAt(i) != cpf.charAt(0)) {
				todosDigitosIguais = false;
				break;
			}
		}
		if (todosDigitosIguais) {
			return false;
		}

		// Validar os dígitos verificadores
		int digito1 = calcularDigitoVerificador(cpf.substring(0, 9), 10);
		int digito2 = calcularDigitoVerificador(cpf.substring(0, 9) + digito1, 11);

		// Verificar se os dígitos verificadores calculados são iguais aos informados no CPF
		if (digito1 == Integer.parseInt(cpf.substring(9, 10)) && digito2 == Integer.parseInt(cpf.substring(10, 11))) {
			return true; // CPF válido
		} else {
			return false; // CPF inválido
		}
	}

	private static int calcularDigitoVerificador(String cpfParcial, int pesoInicial) {
		int soma = 0;
		for (int i = 0; i < cpfParcial.length(); i++) {
			int digito = Integer.parseInt(cpfParcial.substring(i, i + 1));
			soma += digito * (pesoInicial - i);
		}
		int resto = soma % 11;
		if (resto < 2) {
			return 0;
		} else {
			return 11 - resto;
		}
	}

}
