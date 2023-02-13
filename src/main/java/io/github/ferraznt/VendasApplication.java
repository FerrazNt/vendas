package io.github.ferraznt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//Para que seja possível compilar um WAR é necessário Extender a SpringBootServletInitializer
public class VendasApplication extends SpringBootServletInitializer {

    // Método para salvar um Cliente em Tempo de Execussão.
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
