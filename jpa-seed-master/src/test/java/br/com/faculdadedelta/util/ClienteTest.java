package br.com.faculdadedelta.util;

import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import br.edu.faculdadedelta.modelo.Cliente;
import br.edu.faculdadedelta.util.JpaUtil;

public class ClienteTest {
       
	private static final String CPF_PADRAO= "016.860.921-56";
	private EntityManager em;
	
	
	@Before
	public void instanciarEntityManager(){em = JpaUtil.INSTANCE.getEntityManager();}
	
	@After
	public void fecharEntityManager(){if(em.isOpen()){em.close();}}
	
	@AfterClass
	public static void limparTabela(){
		EntityManager entityManager = JpaUtil.INSTANCE.getEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("delete from Cliente c");
		int qtde = query.executeUpdate();
		entityManager.getTransaction().commit();
		assertTrue("tabela limpa ", qtde >0);
	}
	@Test
	public void salvarCliente(){
		Cliente cliente = new Cliente("vinicius", CPF_PADRAO);
		assertTrue("nao tem id", cliente.isTranscient());
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		assertNull("tem id definido",cliente.getId());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarCPF(){
		salvarCliente();
		
		String filtro = "vinicius";
		Query query = em.createQuery("select c.cpf from Cliente c where c.nome like :nome");
		query.setParameter("nome", "%"+filtro+"%");
		List<String> listaCPF = query.getResultList();
		assertFalse("tem na lista ",listaCPF.isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void deveConsultarClienteComIdnome(){
		salvarCliente();
		Query query = em.createQuery("select new Cliente(c.id, c.nome) from Cliente c where c.cpf = :cpf");
		query.setParameter("cpf", CPF_PADRAO);
		List<Cliente> listaClientes = query.getResultList();
		assertFalse("Verifica se a lista esta vazia ",listaClientes.isEmpty());
		for (Cliente cliente : listaClientes) {
			assertNotNull("verifica se o cpf esta nulo", cliente.getCpf());
			cliente.setCpf(CPF_PADRAO);
		}
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void deveConsultarIdNome(){
		salvarCliente();
		Query query = em.createQuery("select c.id, c.nome from Cliente c where c.cpf = :cpf");
		query.setParameter("cpf", CPF_PADRAO);
		List<Object[]> lista = query.getResultList();
		assertFalse("Verifica se a lista esta vazia ",lista.isEmpty());
		for (Object[] obj: lista) {
			assertTrue("checar se tem id ",obj[0] instanceof Long);
			assertTrue("checa se tem nome ",obj[1] instanceof String);
			
			Cliente cliente = new Cliente((Long)obj[0], (String)obj[1]);
			assertNotNull(cliente);
		}
	}
	
	
}
