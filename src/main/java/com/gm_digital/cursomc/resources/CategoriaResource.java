package com.gm_digital.cursomc.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value ="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value ="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<?>> find(@PathVariable Integer id){
        Optional<Categoria> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(404).body(Optional.of("A Categoria [ " + id + " ] não foi encontrada!"));
        }
        return ResponseEntity.ok().body(obj);
    }
     
}
