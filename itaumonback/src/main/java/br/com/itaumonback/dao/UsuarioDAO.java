package br.com.itaumonback.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.itaumonback.modelo.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	
	Usuario findByRacfAndSenha(String racf, String senha);
	Usuario findByFuncionalAndSenha(int funcional, String senha);
	
}
