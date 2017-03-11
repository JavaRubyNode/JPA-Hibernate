package br.com.faculdadedelta.util;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import br.edu.faculdadedelta.util.JpaUtil;

public class JPAUtilTeste {
	
	private EntityManager em;
	
	
	@Test
	public void deveTerInstanciado(){Assert.assertNotNull("Deve ter cido instanciado", em);}
	
	@Before
	public void instaciarEntityManager(){em = JpaUtil.INSTANCE.getEntityManager();}
	
	@After
	public void fecharEntityManager(){if(em.isOpen()){em.close();}}
	
	@Test
	public void devefecharEntityManager(){em.close(); assertFalse("deve ser fechada", em.isOpen());}
	
	@Test
	public void deveAbrirUmaTransacao(){assertFalse("Fechada",em.getTransaction().isActive());em.getTransaction().begin();
	assertTrue("Aberta", em.getTransaction().isActive());}
	

}
