package br.com.itaumonback.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	
	@Column(name="cd_racf", length=7)
	private String racf;

	@Column(name="nr_funcional")
	private int funcional;
	
	@Column(name="nm_usuario", length=50)
	private String nome;
	
	@Column(name="ds_email", length=50)
	private String email;
	
	@Column(name="nm_foto", length=25)
	private String foto;

	@Column(name="ds_senha", length=15)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING)
	@Column(name="dt_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING)
	@Column(name="dt_ult_alteracao")	
	private Date dataUltimaAlteracao;

	public Usuario() {
		super();
	}
	
	public Usuario(int id, String racf, int funcional, String nome, String email, String foto, String senha,
			Date dataCadastro, Date dataUltimaAlteracao) {
		super();
		this.id = id;
		this.racf = racf;
		this.funcional = funcional;
		this.nome = nome;
		this.email = email;
		this.foto = foto;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRacf() {
		return racf;
	}

	public void setRacf(String racf) {
		this.racf = racf;
	}

	public int getFuncional() {
		return funcional;
	}

	public void setFuncional(int funcional) {
		this.funcional = funcional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
}
