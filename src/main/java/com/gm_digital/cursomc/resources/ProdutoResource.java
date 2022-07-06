package com.gm_digital.cursomc.resources;

import com.gm_digital.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<?>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value ="/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<?>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
