package com.example.aprendizado.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.aprendizado.domain.Pessoa;

@Configuration
public class CargaInicial {
    private static final Logger log = LoggerFactory.getLogger(CargaInicial.class);  

    /*
     * Criando uma carga adicional ja no sistema
     */
    @Bean CommandLineRunner
    iniciarDb(PessoaRepository repository){
        String name = "Gabriel Teixeira";
        Integer idade = 22;

        return args -> {
            log.info("Carregando ..." + repository.save(new Pessoa(name, idade)));
        };
    }

}
