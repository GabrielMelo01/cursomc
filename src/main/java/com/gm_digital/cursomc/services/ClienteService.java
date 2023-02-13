package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.Cliente;
import com.gm_digital.cursomc.domain.Cliente;
import com.gm_digital.cursomc.dto.ClienteDTO;
import com.gm_digital.cursomc.repositories.ClienteRepository;
import com.gm_digital.cursomc.services.exeptions.DataIntegrityExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Optional<Cliente> findById(Integer id) {
        return repository.findById(id);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId()).get();
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityExeption("Não é possível excluir porque há pedidos relacionados");
        }
        repository.deleteById(id);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
