package br.edu.faculdadedelta.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum JpaUtil {

	INSTANCE;
	
	private EntityManagerFactory factory;

	public EntityManager getEntityManager() {return factory.createEntityManager();}

	private JpaUtil() {factory = Persistence.createEntityManagerFactory("DeltaPU");}

	
	
	
}
