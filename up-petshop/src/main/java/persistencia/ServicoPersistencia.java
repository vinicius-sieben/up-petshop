package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Servico;

public class ServicoPersistencia {

	public static boolean incluir(Servico objServico) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(objServico);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Servico> getServicos(Servico objServico) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Servico where descricao like :param");
		consulta.setParameter("param", "%" + objServico.getDescricao() + "%");
		@SuppressWarnings("unchecked")
		List<Servico> servicos = consulta.getResultList();
		return servicos;
	}

	public static Servico procurarPorId(Servico objServico) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Servico where id = :param");
		consulta.setParameter("param", objServico.getId());
		@SuppressWarnings("unchecked")
		List<Servico> servicos = consulta.getResultList();
		if (!servicos.isEmpty()) {
			return servicos.get(0);
		} else {
			return null;
		}
	}

	public static boolean atualizar(Servico objServico) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.merge(objServico);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deletar(Servico objServico) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(objServico);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
