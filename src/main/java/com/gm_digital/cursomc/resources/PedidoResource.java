package com.gm_digital.cursomc.resources;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.domain.Pedido;
import com.gm_digital.cursomc.dto.CategoriaDTO;
import com.gm_digital.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value ="/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value ="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<?>> find(@PathVariable Integer id){
        Optional<Pedido> obj = service.findById(id);
        if (!obj.isPresent()) {
            return ResponseEntity.status(404).body(Optional.of("O Pedido [ " + id + " ] não foi encontrado!"));
        }
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
     
}
