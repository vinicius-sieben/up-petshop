package persistencia;

import javax.persistence.EntityManager;

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
	
}
