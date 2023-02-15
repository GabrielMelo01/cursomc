package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.domain.Produto;
import com.gm_digital.cursomc.repositories.CategoriaRepository;
import com.gm_digital.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Optional<Produto> findById(Integer id){
        return repository.findById(id);
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
