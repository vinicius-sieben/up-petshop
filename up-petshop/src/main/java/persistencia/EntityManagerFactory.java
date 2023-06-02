package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static EntityManager manager;

	public static EntityManager getInstance() {
		if (manager == null) {
			manager = Persistence.createEntityManagerFactory("conexao-bd").createEntityManager();
		} 
		return manager;
	}
	
}
