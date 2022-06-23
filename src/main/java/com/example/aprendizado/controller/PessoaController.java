package com.example.aprendizado.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprendizado.domain.Pessoa;
import com.example.aprendizado.repository.PessoaRepository;

@RestController
public class PessoaController {
    /**
     * Pessoa controller, responsavel de realizar o tratamento das actions
     * Metodos
     */

     /*
      * PessoaRepository cria o repository e nele é feito todo o crud
      */
    private final PessoaRepository repository;

    PessoaController(PessoaRepository repository){
        this.repository = repository;
    }

    /*
     * Function find
     */
    @GetMapping("/pessoa")
    Iterable<Pessoa> list(){
        return repository.findAll();
    }


    /*
     * Busca por Id
     */
    @GetMapping("/pessoa/{id}")
    Pessoa buscarPorId(@PathVariable Long id){
        return repository.findById(id).get();
    }

    /*
     * Inclui pessoa
     */
    @PostMapping("/pessoa")
    Pessoa incluir(@RequestBody Pessoa novaPessoa){
        return repository.save(novaPessoa);
    }

  /*
     * Atualiza pessoa
     */
  @PutMapping("/pessoa/{id}")
    Pessoa atualizar(@RequestBody Pessoa pessoaAtualizada, @PathVariable Long id){
        return repository.findById(id)
        .map(pat -> {
            pat.setNome(pessoaAtualizada.getNome());
            pat.setIdade(pessoaAtualizada.getIdade());
            return repository.save(pat);
        })
        .orElseGet(() -> {
            pessoaAtualizada.setId(id);
            return repository.save(pessoaAtualizada);
        });    
    }

    /*
     * Delete Pessoa
     */
    @DeleteMapping("/pessoa/{id}")
    void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }


    /*
     * Repositorio de cola
     */
}
