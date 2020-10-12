package br.com.itaumonback.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_feriado")
public class Feriado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	

	@Column(name="nm_feriado", length=50)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING)
	@Column(name="dt_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING)
	@Column(name="dt_fim")
	private Date dataFim;
	
	@ManyToOne
	@JsonIgnoreProperties("feriados")
	private Agencia agencia;

	
	public Feriado() {
		super();
	}

	public Feriado(int id, String nome, Date dataInicio, Date dataFim, Agencia agencia) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.agencia = agencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	
}
