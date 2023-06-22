package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Funcionario;
import entidades.Venda;

public class VendaPersistencia {

	public static boolean incluir(Venda objVenda) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(objVenda);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Venda procurarPorCodigo(Venda objVenda) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Venda where id = :param");
		consulta.setParameter("param", objVenda.getId());
		@SuppressWarnings("unchecked")
		List<Venda> vendas = consulta.getResultList();
		if (!vendas.isEmpty()) {
			return vendas.get(0);
		} else {
			return null;
		}
	}

	public static List<Venda> procurarPorFuncionario(Funcionario objFuncionario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Venda where funcionario_id = :param");
		consulta.setParameter("param", objFuncionario.getId());
		@SuppressWarnings("unchecked")
		List<Venda> vendas = consulta.getResultList();
		if (!vendas.isEmpty()) {
			return vendas;
		} else {
			return null;
		}
	}

	public static boolean atualizar(Venda objVenda) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.merge(objVenda);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deletar(Venda objVenda) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(objVenda);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Venda> getVendas(Venda objVenda) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Venda where data like :param");
		consulta.setParameter("param", "%" + objVenda.getData() + "%");
		@SuppressWarnings("unchecked")
		List<Venda> vendas = consulta.getResultList();
		return vendas;
	}
}
