package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Pedido;
import com.gm_digital.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Optional<Pedido> findById(Integer id) {
        return repository.findById(id);
    }

}
