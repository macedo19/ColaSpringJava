package com.example.aprendizado.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.aprendizado.domain.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
