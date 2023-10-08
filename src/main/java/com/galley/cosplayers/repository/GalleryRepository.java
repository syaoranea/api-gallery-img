package com.galley.cosplayers.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galley.cosplayers.model.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, UUID>  {
    
}
