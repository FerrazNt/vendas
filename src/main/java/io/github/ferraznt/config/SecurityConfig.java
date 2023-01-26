package io.github.ferraznt.config;

import io.github.ferraznt.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    // Serve para Criptografar e descriptografar a senha do usuário
    // No caso estou usando a BCrypte, implementação do Próprio Spring Security
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Trazer os Objetos que vão fazer a autenticação dos Usuários (autentica o usuário
    // para ele funcionar, precisamos definir o tipo de Encode das Senhas pra isso eu vou criar um Bean de encoder de
    // senha PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());

        //        Configuração em Memória só pra fazer um Teste
        //        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
        //                .withUser("fulano")
        //                .password(passwordEncoder().encode("123"))
        //                .roles("USER","ADMIN");
    }

    // A Parte de Autorização: Tipo - Pegue o Usuário Autenticado e Verifique se ele tem autorização a uma determinada
    //                                página, considerando as Roles e Autorities.
    // --- Configuração Básica de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/clientes/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/pedidos/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produtos/**")
                .hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()

        .and()
                .httpBasic();

        // Linha para Habilitar o Console H2 em Uso de Frames
        http.headers().frameOptions().disable();
        // Para Permitir Tudo, usar o .permitAll()
    }

    /**
     * // Exemplo de Formulário HTML para ser usado como página de Login alternativa no Método .formLongin()
     * <form method="post">
     *         <input type="text" name="username" />
     *         <input type="secret" name="password" />
     *         <button type="submit">Enviar</button>
     *  </form>
     */
}
