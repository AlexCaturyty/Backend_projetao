package br.com.criandoapi.projeto.service;

import java.util.List;


import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto.dto.UsuarioAtualizacaoDTO;
import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.repository.IUsuario;

@Service
public class UsuarioService {

	private IUsuario repository;

	public UsuarioService(IUsuario repository) {
		this.repository = repository;
	}

	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}

	public Usuario criarUsuario(Usuario usuario) {
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}

	public Usuario editarUsuario(Usuario usuarioExistente, UsuarioAtualizacaoDTO usuarioDTO) {
	    usuarioExistente.setNome(usuarioDTO.getNovoNome());
	    usuarioExistente.setSenha(usuarioDTO.getNovaSenha());
	    return repository.save(usuarioExistente);
	}




	


	public boolean excluirUsuario(String nome, String senha) {
	    Usuario usuario = repository.findByNome(nome);
	    if (usuario != null && usuario.getSenha().equals(senha)) {
	        repository.deleteById(usuario.getId());
	        return true;
	    }
	    return false;
	}



	public Usuario autenticarUsuario(String nome, String senha) {
	    Usuario usuario = repository.findByNome(nome);
	    if (usuario != null && usuario.getSenha().equals(senha)) {
	        return usuario;
	    }
	    return null; 
	}

	public Usuario findByNomeAndSenha(String nome, String senha) {
	    return repository.findByNomeAndSenha(nome, senha);
	}

}