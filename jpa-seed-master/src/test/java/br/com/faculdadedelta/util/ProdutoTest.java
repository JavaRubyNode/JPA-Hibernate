package br.com.faculdadedelta.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import br.edu.faculdadedelta.modelo.Produto;
import br.edu.faculdadedelta.util.JpaUtil;

public class ProdutoTest {
	
	private EntityManager em;
	
	
	@Before
	public void instanciarEntityManager(){em = JpaUtil.INSTANCE.getEntityManager();}
	
	@After
	public void fecharEntityManager(){if(em.isOpen()){em.close();}}
	
	
	@Test
	public void salvarProduto(){
		Produto produto = new Produto("notebook", "Dell");
		assertTrue("nao tem id", produto.isTranscient());
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		assertNull("tem id definido",produto.getId());
	}
	
	
	@Test
	public void pesquisarProdutos(){
		for (int i = 0; i < 10; i++) {
			salvarProduto();
		}
		
		TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
		List<Produto> listaProdutos = query.getResultList();
		assertFalse("encontrou um produto",listaProdutos.isEmpty());
		assertTrue("encontrou varios produtos", listaProdutos.size() >= 10);
	}

}
