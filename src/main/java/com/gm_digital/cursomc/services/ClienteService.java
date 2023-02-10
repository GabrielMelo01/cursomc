package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Cliente;
import com.gm_digital.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Optional<Cliente> findById(Integer id) {
        return repo.findById(id);
    }
}
