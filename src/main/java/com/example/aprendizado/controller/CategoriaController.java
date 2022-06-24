package com.example.aprendizado.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprendizado.domain.Categoria;
import com.example.aprendizado.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    /**
     * Criando repositori
     */
    private final CategoriaRepository _categoriaRepository;
    private final String baseUrl = "/categoria";

    public CategoriaController(CategoriaRepository categoriaRepository) {
        _categoriaRepository = categoriaRepository;
    }

    @GetMapping(baseUrl + "/init")
    ResponseEntity<Iterable<Categoria>> initialize() {
        try {

            /*
             * Responsavel de pegar api externa
             */
            HttpClient client = HttpClient.newHttpClient();

            /*
             * Pegar requisições
             */
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.escuelajs.co/api/v1/categories"))
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            /*
             * Tratativa de transformar o o body em Json
             */
            JSONArray obj = new JSONArray(response.body());

            List<Categoria> cateList = new ArrayList<Categoria>();

            /*
             * Loop para incluir na interface (Construtor que esta na domain)
             */
            for (int i = 0; i < obj.length(); i++) {
                Categoria categ = new Categoria();
                categ.setId((long) i);
                categ.setName(obj.getJSONObject(i).getString("name"));
                categ.setImage(obj.getJSONObject(i).getString("image"));

                cateList.add(categ);
            }

            /*
             * Salva na interface
             */
            var repoSave = _categoriaRepository.saveAll(cateList);

            /*
             * Response (Sempre colocar esse cara)
             */
            return ResponseEntity.status(HttpStatus.CREATED).body(repoSave);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Metodo de incluir
     */
    @PostMapping(baseUrl + "/incluir")
    ResponseEntity<Iterable<Categoria>> incluir(@RequestBody Categoria novaCategoria) {
        try {

            /*
             * Salva na interface
             */
            _categoriaRepository.save(novaCategoria);

            return ResponseEntity.status(HttpStatus.CREATED).body(_categoriaRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
