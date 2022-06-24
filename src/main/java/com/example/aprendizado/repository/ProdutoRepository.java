package com.example.aprendizado.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.aprendizado.domain.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
