package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Produto;
import com.gm_digital.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Optional<Produto> findById(Integer id){
        return repository.findById(id);
    }
}
