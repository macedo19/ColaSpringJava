package com.example.aprendizado.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {
    public Categoria() {
    }

    public Categoria(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(length = 10000)
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
