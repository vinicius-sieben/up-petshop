package negocio;

import entidades.Servico;

public class ServicoNegocio {
	public static double calcularPrecoVenda(Servico servico) {
		return servico.getValor();
	}
}
