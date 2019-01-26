package br.com.lph.adminp2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lph.adminp2.domain.Usuario;
import br.com.lph.adminp2.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Usuario usu = repo.findByNomeUsuario(nomeUsuario);
		if (usu == null) {
			throw new UsernameNotFoundException(nomeUsuario);
		}
		
		return new UserSS(usu.getId(),usu.getNomeUsuario(), usu.getSenha(), usu.getPerfis());
	}

}
