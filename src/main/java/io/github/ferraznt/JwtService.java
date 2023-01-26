package io.github.ferraznt;

import io.github.ferraznt.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${sercurity.jwt.expiracao}")
    private String expiracao;

    @Value("${sercurity.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataOraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(dataOraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);

        JwtService service = contexto.getBean(JwtService.class);

        Usuario usuario = Usuario.builder().login("fulano").build();
        String token = service.gerarToken(usuario);

        System.out.println(token);
    }
}


