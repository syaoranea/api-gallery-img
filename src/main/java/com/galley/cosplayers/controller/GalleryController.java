
package com.galley.cosplayers.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.galley.cosplayers.dtos.GalleryRecort;
import com.galley.cosplayers.model.Gallery;
import com.galley.cosplayers.repository.GalleryRepository;

import jakarta.validation.Valid;

import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Validated
@RestController
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepository;

    @GetMapping("/api/gallery")
	public ResponseEntity<List<Gallery>> getAllGallery(){
		List<Gallery> gallerysList = galleryRepository.findAll();
		if(!gallerysList.isEmpty()) {
			for(Gallery gallery : gallerysList) {
				UUID id = gallery.getId();
				gallery.add(linkTo(methodOn(GalleryController.class).getOneProduct(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(gallerysList);
	}

	@GetMapping("/api/gallery/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
		Optional<Gallery> productO = galleryRepository.findById(id);
		if(productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("imagem not found");
		}
		productO.get().add(linkTo(methodOn(GalleryController.class).getAllGallery()).withRel("imagem List"));
		return ResponseEntity.status(HttpStatus.OK).body(productO.get());
	}
	
	@PostMapping("/api/gallery")
	public ResponseEntity<Gallery> saveProduct(@RequestBody @Valid GalleryRecort galleyRecordDto) {
		var galleryModel = new Gallery();
		BeanUtils.copyProperties(galleyRecordDto, galleryModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(galleryRepository.save(galleryModel));
	}
	
	@DeleteMapping("/api/gallery/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
		Optional<Gallery> productO = galleryRepository.findById(id);
		if(productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		galleryRepository.delete(productO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
	}
	
	@PutMapping("/api/gallery/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
													  @RequestBody @Valid GalleryRecort galleryRecortDto) {
		Optional<Gallery> gallery0 = galleryRepository.findById(id);
		if(gallery0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		var galleryModel = gallery0.get();
		BeanUtils.copyProperties(galleryRecortDto, galleryModel);
		return ResponseEntity.status(HttpStatus.OK).body(galleryRepository.save(galleryModel));
	}
    
}