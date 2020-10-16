package br.com.itaumonback.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.itaumonback.modelo.Feriado;

public interface FeriadoDAO extends CrudRepository<Feriado, Integer>{
	List<Feriado> findByNacional(boolean nacional);
}
