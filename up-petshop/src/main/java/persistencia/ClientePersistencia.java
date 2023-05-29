package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;

public class ClientePersistencia {
	
	public static boolean incluir(Cliente objCliente) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-bd");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(objCliente);
			em.getTransaction().commit();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static Cliente procurarPorCPF(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-bd");
		EntityManager em = emf.createEntityManager();
		// Query de consulta ao BD
		Query consulta = em.createQuery("from Cliente where cpf = :param");
		// Editando o parametro da Query
		consulta.setParameter("param", cliente.getCpf());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if(!clientes.isEmpty()){
			return clientes.get(0);
		} else {
			return null;	
		}
	}
	
	public static Cliente procurarPorNome(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-bd");
		EntityManager em = emf.createEntityManager();
		Query consulta = em.createQuery("from Cliente where nome = :param");
		consulta.setParameter("param", cliente.getNome());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if(!clientes.isEmpty()) {
			return clientes.get(0);
		} else {
			return null;
		}
	}
	
	public static Cliente procurarPorId(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-bd");
		EntityManager em = emf.createEntityManager();
		Query consulta = em.createQuery("from Cliente where id = :param");
		consulta.setParameter("param", cliente.getId());
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		if(!clientes.isEmpty()) {
			return clientes.get(0);
		} else {
			return null;
		}
	}
	
	public static List<Cliente> getClientes(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-bd");
		EntityManager em = emf.createEntityManager();
		Query consulta = em.createQuery("from Cliente where nome like :param");
		consulta.setParameter("param", "%" + cliente.getNome() + "%");
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = consulta.getResultList();
		return clientes;
	}
	
}
