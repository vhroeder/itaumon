package br.com.itaumonback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumonback.dao.AgenciaDAO;
import br.com.itaumonback.modelo.Agencia;

@RestController
@CrossOrigin("*")
public class AgenciaController {
 
	@Autowired
	private AgenciaDAO dao;
	
	@GetMapping("/agencia/consultar/{codigo}")
	public ResponseEntity<Agencia> consultarAgencia(@PathVariable int codigo)
	{
		Agencia retorno = dao.findById(codigo).orElse(null);

		if (retorno == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("/agencia/listar")
	public ResponseEntity<List<Agencia>> listarAgencias()
	{
		List<Agencia> lista = (List<Agencia>)dao.findAll();
		
		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/agencia/incluir")
	public ResponseEntity<Agencia> incluirAgencia(@RequestBody Agencia agencia)
	{
		try  
		{
			dao.save(agencia);
			return ResponseEntity.ok(agencia);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@PostMapping("/agencia/alterar")
	public ResponseEntity<Agencia> alterarAgencia(@RequestBody Agencia agencia)
	{
		Agencia cadastro = new Agencia();

		cadastro = dao.findById(agencia.getId()).orElse(null);

		if (cadastro == null)
			return ResponseEntity.status(404).build();

		try  
		{
		
			if (agencia.getNome() !=null && !agencia.getNome().isEmpty())
				cadastro.setNome(agencia.getNome());

			dao.save(cadastro);
			return ResponseEntity.ok(cadastro);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@GetMapping("/agencia/excluir/{codigo}")
	public ResponseEntity<Agencia> excluirAgencia(@PathVariable int codigo)
	{
		Agencia retorno = dao.findById(codigo).orElse(null);

		if (retorno == null) {
			return ResponseEntity.status(404).build();
		}
		else
			dao.deleteById(codigo);

		return ResponseEntity.ok(retorno);
	}

}
