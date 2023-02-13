package com.gm_digital.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.gm_digital.cursomc.domain.Cliente;
import com.gm_digital.cursomc.dto.CategoriaDTO;
import com.gm_digital.cursomc.services.exeptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import com.gm_digital.cursomc.services.exeptions.DataIntegrityExeption;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gm_digital.cursomc.domain.Categoria;
import com.gm_digital.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Optional<Categoria> findById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        if (!obj.isPresent()) {
            throw new ObjectNotFoundExeption("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
        return obj;
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = findById(obj.getId()).get();
        updateData(newObj, obj);
        return repository.save(newObj);
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

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }

}
