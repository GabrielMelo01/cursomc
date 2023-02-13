package com.gm_digital.cursomc.services;

import java.util.Optional;

import com.gm_digital.cursomc.services.exeptions.ObjectNotFoundExeption;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.gm_digital.cursomc.services.exeptions.DataIntegrityExeption;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Optional<Categoria> findById(Integer id) {
        try {
            Optional<Categoria> obj = repository.findById(id);
            return obj;
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundExeption("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        findById(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityExeption("Não é possível excluir uma categoria que possui produtos");
        }
        repository.deleteById(id);
    }

}
