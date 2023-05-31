package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.Cliente;

public class ClientePersistencia {

	public static boolean incluir(Cliente objCliente) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(objCliente);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean atualizar(Cliente objCliente) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.merge(objCliente);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deletar(Cliente objCliente) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(objCliente);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Cliente procurarPorCPF(Cliente cliente) {
		EntityManager manager = EntityManagerFactory.getInstance();
		// Query de consulta ao BD
		Query consulta = manager.createQuery("from Cliente where cpf = :param");
		// Editando o parametro da Query
		consulta.setParameter("param", cliente.getCpf());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if (!clientes.isEmpty()) {
			return clientes.get(0);
		} else {
			return null;
		}
	}

	public static Cliente procurarPorNome(Cliente cliente) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Cliente where nome = :param");
		consulta.setParameter("param", cliente.getNome());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if (!clientes.isEmpty()) {
			return clientes.get(0);
		} else {
			return null;
		}
	}

	public static Cliente procurarPorId(Cliente cliente) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Cliente where id = :param");
		consulta.setParameter("param", cliente.getId());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if (!clientes.isEmpty()) {
			return clientes.get(0);
		} else {
			return null;
		}
	}

	public static List<Cliente> getClientes(Cliente cliente) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Cliente where nome like :param");
		consulta.setParameter("param", "%" + cliente.getNome() + "%");
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		return clientes;
	}

}
