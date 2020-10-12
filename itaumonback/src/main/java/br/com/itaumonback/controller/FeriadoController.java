package br.com.itaumonback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumonback.dao.FeriadoDAO;
import br.com.itaumonback.modelo.Feriado;

@RestController
@CrossOrigin("*")
public class FeriadoController {

	@Autowired
	private FeriadoDAO dao;
	
	@GetMapping("/feriado/listar")
	public ResponseEntity<List<Feriado>> listarFeriados()
	{
		List<Feriado> lista = (List<Feriado>)dao.findAll();
		
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/feriado/incluir")
	public ResponseEntity<Feriado> incluirFeriado(@RequestBody Feriado feriado)
	{
		try  
		{
			dao.save(feriado);
			return ResponseEntity.ok(feriado);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
}
