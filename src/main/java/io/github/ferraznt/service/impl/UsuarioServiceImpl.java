package io.github.ferraznt.service.impl;

import io.github.ferraznt.domain.entity.Usuario;
import io.github.ferraznt.domain.repository.UsuarioRepository;
import io.github.ferraznt.exception.SenhaInvalidaException;
import io.github.ferraznt.rest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario salvar(UsuarioDTO dto){
        Usuario usuario = new Usuario();

        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setAdmin(dto.isAdmin());

        return usuarioRepository.save(usuario);

    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), userDetails.getPassword());
        if(senhasBatem){
            return userDetails;
        }
        throw new SenhaInvalidaException();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado no nosso Banco de Dados"));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

        //        // Criando Usuário em Memória
        //        if(!s.equals("ciclano")){
        //            throw new UsernameNotFoundException("Usuário não encontrado...");
        //        }
        //        return User
        //                .builder()
        //                .password(encoder.encode("123"))
        //                .username("ciclano")
        //                .roles("USER","ADMIN")
        //                .build();
    }
}
