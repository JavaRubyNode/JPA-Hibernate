package br.edu.faculdadedelta.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.faculdadedelta.generic.BaseEntity;

@Entity
public class Venda extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_venda")
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST},fetch =FetchType.LAZY)
	@JoinColumn(name="id_cliente",referencedColumnName="id_cliente", insertable=true,updatable=false,nullable=false)
	private Cliente cliente;
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST})
	@JoinTable(name="venda_produto",joinColumns = @JoinColumn(name="id_venda"),inverseJoinColumns = @JoinColumn(name="id_produto"))
	private List<Produto> listaProdutos;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_venda",nullable=false)
	private Date dataHora;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Date getDataHora() {return dataHora;}
	
	public Cliente getCliente() {return cliente;}

	public void setCliente(Cliente cliente) {this.cliente = cliente;}

	public void setDataHora(Date dataHora) {this.dataHora = dataHora;}

	public List<Produto> getListaProdutos() {return listaProdutos;}

	public void setListaProdutos(List<Produto> listaProdutos) {this.listaProdutos = listaProdutos;}


	
	
}
