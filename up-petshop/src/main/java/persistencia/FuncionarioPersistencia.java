package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Funcionario;

public class FuncionarioPersistencia {

	// PROCURAR POR NOME, CPF, ID e GETCLIENTES

	public static boolean incluir(Funcionario objFuncionario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(objFuncionario);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean atualizar(Funcionario objFuncionario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.merge(objFuncionario);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean deletar(Funcionario objFuncionario) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(objFuncionario);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Funcionario procurarPorCPF(Funcionario objFuncionario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Funcionario where cpf = :param");
		consulta.setParameter("param", objFuncionario.getCpf());
		@SuppressWarnings("unchecked")
		List<Funcionario> funcionarios = consulta.getResultList();
		if (!funcionarios.isEmpty()) {
			return funcionarios.get(0);
		} else {
			return null;
		}
	}

	public static Funcionario procurarPorNome(Funcionario objFuncionario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Funcionario where nome = :param");
		consulta.setParameter("param", objFuncionario.getNome());
		@SuppressWarnings("unchecked")
		List<Funcionario> funcionarios = consulta.getResultList();
		if (!funcionarios.isEmpty()) {
			return funcionarios.get(0);
		} else {
			return null;
		}
	}

	public static Funcionario procurarPorId(Funcionario objFuncionario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Funcionario where id = :param");
		consulta.setParameter("param", objFuncionario.getId());
		@SuppressWarnings("unchecked")
		List<Funcionario> funcionarios = consulta.getResultList();
		if (!funcionarios.isEmpty()) {
			return funcionarios.get(0);
		} else {
			return null;
		}
	}

	public static List<Funcionario> getFuncionarios(Funcionario objFuncionario) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Funcionario where nome like :param");
		consulta.setParameter("param", "%" + objFuncionario.getNome() + "%");
		@SuppressWarnings("unchecked")
		List<Funcionario> funcionarios = consulta.getResultList();
		return funcionarios;
	}

}
