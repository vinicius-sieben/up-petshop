package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Animal;

public class AnimalPersistencia {

	public static boolean incluir(Animal objAnimal) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(objAnimal);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Animal> getAnimais(Animal animal) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Animal where nome like :param");
		consulta.setParameter("param", "%" + animal.getNome() + "%");
		@SuppressWarnings("unchecked")
		List<Animal> animais = consulta.getResultList();
		return animais;
	}

	public static Animal procurarPorId(Animal animal) {
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Animal where id = :param");
		consulta.setParameter("param", animal.getId());
		@SuppressWarnings("unchecked")
		List<Animal> animais = consulta.getResultList();
		if (!animais.isEmpty()) {
			return animais.get(0);
		} else {
			return null;
		}
	}

	public static boolean atualizar(Animal objAnimal) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.merge(objAnimal);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean deletar(Animal objAnimal) {
		try {
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(objAnimal);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
