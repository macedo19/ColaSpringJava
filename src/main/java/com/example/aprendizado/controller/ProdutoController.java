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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprendizado.domain.Produto;
import com.example.aprendizado.repository.ProdutoRepository;

@RestController
public class ProdutoController {

    private final ProdutoRepository _prodRepository;
    private final String baseUrl = "/produto";

    public ProdutoController(ProdutoRepository produtoRepository) {
        _prodRepository = produtoRepository;
    }

    @GetMapping(baseUrl + "/init")
    ResponseEntity<Iterable<Produto>> initialize() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fakestoreapi.com/products?limit=10"))
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONArray obj = new JSONArray(response.body());

            List<Produto> productList = new ArrayList<Produto>();

            for (int i = 0; i < obj.length(); i++) {
                Produto produto = new Produto();
                produto.setId((long) i);
                produto.setName(obj.getJSONObject(i).getString("title"));
                produto.setDescription(obj.getJSONObject(i).getString("description"));
                produto.setPrice(obj.getJSONObject(i).getDouble("price"));

                productList.add(produto);
            }

            var repoSave = _prodRepository.saveAll(productList);

            return ResponseEntity.status(HttpStatus.CREATED).body(repoSave);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @GetMapping(baseUrl + "/getAllProducts")
    ResponseEntity<Iterable<Produto>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(_prodRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @DeleteMapping(baseUrl + "/delete/{id}")
    ResponseEntity<Iterable<Produto>> deleteProd(@PathVariable Long id) {
        try {

            _prodRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(_prodRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping(baseUrl + "/produto")
    ResponseEntity<Iterable<Produto>> incluir(@RequestBody Produto produto) {
        _prodRepository.save(produto);
        return ResponseEntity.status(HttpStatus.OK).body(_prodRepository.findAll());

    }
}
