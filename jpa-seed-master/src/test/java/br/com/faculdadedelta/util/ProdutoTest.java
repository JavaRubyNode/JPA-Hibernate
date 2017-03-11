package br.com.faculdadedelta.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.LongFunction;

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

	@Test
	public void alterarProduto(){
		salvarProduto();
		TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class).setMaxResults(1);
		Produto produto = query.getSingleResult();
		assertNotNull("encontrado um produto", produto);
		Integer versao = produto.getVersion();
		em.getTransaction().begin();
		produto.setNome("vinicius");
		produto = em.merge(produto);
		em.getTransaction().commit();
		assertNotEquals("versao incrementada", versao.intValue(),produto.getVersion().intValue());
		
		
	}
	
	@Test
	public void excluirProduto(){
		salvarProduto();
		TypedQuery<Long> query = em.createQuery("select max(p.id) from Produto p ",Long.class);
		Long id = query.getSingleResult();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, id);
		em.remove(produto);
		em.getTransaction().commit();
		Produto excluido = em.find(Produto.class, id);
		assertNull("nao encontrado ",excluido);
		
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
