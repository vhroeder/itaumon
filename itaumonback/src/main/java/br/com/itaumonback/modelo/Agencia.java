package br.com.itaumonback.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_agencia")
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	
	@Column(name="nr_agencia")
	private int numero;
	
	@Column(name="nm_agencia", length=50)
	private String nome;

	@OneToMany(mappedBy="agencia", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("agencia")
	private List<Feriado> feriados;

	
	public Agencia() {
		super();
	}

	public Agencia(int id, int numero, String nome, List<Feriado> feriados) {
		super();
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.feriados = feriados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Feriado> getFeriados() {
		return feriados;
	}

	public void setFeriados(List<Feriado> feriados) {
		this.feriados = feriados;
	}	
	
}
