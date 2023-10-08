package com.galley.cosplayers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "TB_GALLERY")
public class Gallery extends RepresentationModel<Gallery> implements Serializable{

    private static final long serialVersionUID = 1L;

    public Gallery() {
    }

    public Gallery(UUID id, String nome, String description, String imageurl, String thumbnailurl, String datacriacao, String users) {
        this.id = id;
        this.nome = nome;
        this.description = description;
        this.imageurl = imageurl;
        this.thumbnailurl = thumbnailurl;
        this.datacriacao = datacriacao;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Gallery [datacriacao=" + datacriacao + ", description=" + description + ", id=" + id + ", imageurl="
                + imageurl + ", nome=" + nome + ", thumbnailurl=" + thumbnailurl + ", users=" + users + "]";
    }


//criar getters e setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;


    private String description;


    private String imageurl;

 
    private String thumbnailurl;


    private String datacriacao;


    private String users;

    public UUID getId() {
        return id;
    }

    public String getnome() {
        return nome;
    }

    public String getDescription() {
        return description;
    }

    public String getimageurl() {
        return imageurl;
    }

    public String getthumbnailurl() {
        return thumbnailurl;
    }

    public String getdatacriacao() {
        return datacriacao;
    }

    public String getusers() {
        return users;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public void setnome(String nome) {
        this.nome = nome;
    }
    
    public void setDescription(String description) {
        this.description= description;
    }
    
    public void setimageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    
    public void setthumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }
    
    public void setdatacriacao(String datacriacao) {
        this.datacriacao = datacriacao;
    }
    
    public void setusers(String users) {
        this.users = users;
    }
    
}
