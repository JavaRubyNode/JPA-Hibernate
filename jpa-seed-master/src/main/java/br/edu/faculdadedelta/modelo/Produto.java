package br.edu.faculdadedelta.modelo;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.faculdadedelta.generic.BaseEntity;

@Entity
@Table(name="produto")
public class Produto extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_produto",unique=true,nullable=false)
	private Long id;
	
	@Column(name="mn_produto",nullable=false,length=100)
	private String nome;
	
	@Column(name="mn_fabricante",nullable=false,length=50)
	@Basic(fetch = FetchType.LAZY,optional =true)
	private String fabricante;
	
	@Temporal(TemporalType.DATE)
	@Basic(fetch = FetchType.LAZY,optional =true)
	@Column(name="dt_validade")
	private Date validade;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNome() {return nome;}

	public void setNome(String nome) {this.nome = nome;}

	public String getFabricante() {return fabricante;}

	public void setFabricante(String fabricante) {this.fabricante = fabricante;}

	public Date getValidade() {return validade;}

	public void setValidade(Date validade) {this.validade = validade;}

	public void setId(Long id) {this.id = id;}
	
	

}
