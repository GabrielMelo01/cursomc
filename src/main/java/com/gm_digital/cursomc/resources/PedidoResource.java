package com.gm_digital.cursomc.resources;

import com.gm_digital.cursomc.domain.Pedido;
import com.gm_digital.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     
}
