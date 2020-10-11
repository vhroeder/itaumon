package br.com.itaumonback.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaumonback.dao.UsuarioDAO;
import br.com.itaumonback.modelo.Usuario;

@RestController
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@GetMapping("/usuario/consultar/{codigo}")
	public ResponseEntity<Usuario> consultarUsuario(@PathVariable int codigo)
	{
		Usuario retorno = dao.findById(codigo).orElse(null);

		if (retorno == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(retorno);
	}

	@GetMapping("/usuario/listar")
	public ResponseEntity<List<Usuario>> listarUsuarios()
	{
		List<Usuario> lista = (List<Usuario>)dao.findAll();

		if (lista.size() == 0) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(lista);
	}

	@PostMapping("/usuario/incluir")
	public ResponseEntity<Usuario> incluirUsuario(@RequestBody Usuario usuario)
	{
		try  
		{
			dao.save(usuario);
			return ResponseEntity.ok(usuario);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}

	@PostMapping("/usuario/alterar")
	public ResponseEntity<Usuario> alterarUsuario(@RequestBody Usuario usuario)
	{
		Usuario cadastro = new Usuario();

		cadastro = dao.findById(usuario.getId()).orElse(null);

		if (cadastro == null)
			return ResponseEntity.status(404).build();

		try  
		{
			if (usuario.getNome() !=null && !usuario.getNome().isEmpty())
				cadastro.setNome(usuario.getNome());

			if (usuario.getEmail() !=null && !usuario.getEmail().isEmpty())
				cadastro.setEmail(usuario.getEmail());

			if (usuario.getFoto() !=null && !usuario.getFoto().isEmpty())
				cadastro.setFoto(usuario.getFoto());

			if (usuario.getSenha() !=null && !usuario.getSenha().isEmpty())
				cadastro.setSenha(usuario.getSenha());

			cadastro.setDataUltimaAlteracao(new Date());

			dao.save(cadastro);
			return ResponseEntity.ok(cadastro);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}

	@PostMapping("/usuario/login")
	public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario)
	{
		Usuario logado = new Usuario();

		if (usuario.getRacf() != null && !usuario.getRacf().isEmpty())
			logado = dao.findByRacfAndSenha(usuario.getRacf(), usuario.getSenha());
		else
			logado = dao.findByFuncionalAndSenha(usuario.getFuncional(), usuario.getSenha());

		if (logado == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(logado);
	}

	@GetMapping("/usuario/excluir/{codigo}")
	public ResponseEntity<Usuario> excluirUsuario(@PathVariable int codigo)
	{
		Usuario retorno = dao.findById(codigo).orElse(null);

		if (retorno == null) {
			return ResponseEntity.status(404).build();
		}
		else
			dao.deleteById(codigo);

		return ResponseEntity.ok(retorno);
	}
}
