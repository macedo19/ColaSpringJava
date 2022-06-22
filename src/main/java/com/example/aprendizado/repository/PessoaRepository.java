package com.example.aprendizado.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.aprendizado.domain.Pessoa;
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
    
}
